package hexagonal.architecture.esdras.application.service.matches;

import hexagonal.architecture.esdras.application.port.input.matches.InputPortCreateMatchUseCase;
import hexagonal.architecture.esdras.application.port.input.matches.commands.CreateMatchCommand;
import hexagonal.architecture.esdras.application.port.input.matches.commands.PlayerMatchCommand;
import hexagonal.architecture.esdras.application.port.input.matches.exceptions.PlayersNotFoundException;
import hexagonal.architecture.esdras.application.port.output.matches.persistence.OutputPortMatch;
import hexagonal.architecture.esdras.application.port.output.players.persistence.OutputPortPlayer;
import hexagonal.architecture.esdras.domain.entity.MatchDomain;
import hexagonal.architecture.esdras.domain.entity.PlayerDomain;
import hexagonal.architecture.esdras.domain.exceptions.NumberPlayersByMatchException;
import hexagonal.architecture.esdras.domain.vo.MatchIdentityDomain;

import java.util.*;
import java.util.stream.Collectors;

public class CreateMatchUseCaseService implements InputPortCreateMatchUseCase {

    private final OutputPortPlayer outputPortPlayer;
    private final OutputPortMatch outputPortMatch;

    public CreateMatchUseCaseService(OutputPortPlayer outputPortPlayer, OutputPortMatch outputPortMatch) {
        this.outputPortPlayer = outputPortPlayer;
        this.outputPortMatch = outputPortMatch;
    }

    @Override
    public MatchDomain createMatch(CreateMatchCommand createMatchCommand) throws PlayersNotFoundException, NumberPlayersByMatchException {
        List<PlayerMatchCommand> missingPlayers = new ArrayList<>();
        Set<PlayerDomain> playerDomains = getPlayersDomains(createMatchCommand, missingPlayers);
        validateMissingPlayers(missingPlayers);

        MatchDomain matchDomain = MatchDomain.builder()
                .id(MatchIdentityDomain.generateRandomPart())
                .date(createMatchCommand.getDate())
                .local(createMatchCommand.getLocal())
                .matchWeight(createMatchCommand.getMatchWeight())
                .build();


        playerDomains.forEach(matchDomain::addPlayer);

        matchDomain.validateNumberPlayers();
        
        return outputPortMatch.save(matchDomain);
    }

    private Set<PlayerDomain> getPlayersDomains(CreateMatchCommand command, List<PlayerMatchCommand> missingPlayers) {
        return command.getPlayers()
                .stream()
                .map(entry -> findPlayersAndValidated(entry, missingPlayers))
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private PlayerDomain findPlayersAndValidated(PlayerMatchCommand entry, List<PlayerMatchCommand> missingPlayers) {
        Optional<PlayerDomain> playerDomain = Optional.empty();

        if (entry.getEmail() == null && entry.getId() == null) {
            missingPlayers.add(entry);
            return null;
        }

        if (entry.getEmail() != null) {
            playerDomain = outputPortPlayer.findByEmail(entry.getEmail());
        } else if (entry.getId() != null) {
            playerDomain = outputPortPlayer.findById(entry.getId());
        }

        if (!playerDomain.isPresent()) {
            missingPlayers.add(entry);
            return null;
        }

        return playerDomain.get();
    }

    private void validateMissingPlayers(List<PlayerMatchCommand> missingProducts) throws PlayersNotFoundException {
        if (!missingProducts.isEmpty()) {
            throw new PlayersNotFoundException("Players não encontrados: " + String.join(", ", missingProducts.stream().map(PlayerMatchCommand::toString).collect(Collectors.toList())));
        }
    }


}
