package hexagonal.architecture.esdras.application.service.players;

import hexagonal.architecture.esdras.application.port.input.players.InputPortCreatePlayerUseCase;
import hexagonal.architecture.esdras.application.port.input.players.commands.CreatePlayerCommand;
import hexagonal.architecture.esdras.application.port.input.players.exceptions.EmailIsAlreadyUsedException;
import hexagonal.architecture.esdras.application.port.output.players.persistence.OutputPortPlayer;
import hexagonal.architecture.esdras.domain.entity.PlayerDomain;
import hexagonal.architecture.esdras.domain.vo.PlayerIdentityDomain;

public class CreatePlayerUseCaseService implements InputPortCreatePlayerUseCase {

    private final OutputPortPlayer outputPortPlayer;

    public CreatePlayerUseCaseService(OutputPortPlayer outputPortPlayer) {
        this.outputPortPlayer = outputPortPlayer;
    }

    @Override
    public PlayerDomain createPlayer(CreatePlayerCommand command) throws EmailIsAlreadyUsedException {
        if (emailExists(command.email())) {
            throw new EmailIsAlreadyUsedException("Já existe um jogador com o e-mail: " + command.email());
        }
        PlayerDomain playerDomain = buildPlayerDomain(command);
        return outputPortPlayer.save(playerDomain);
    }

    private boolean emailExists(String email) {
        return outputPortPlayer.findByEmail(email).isPresent();
    }

    private PlayerDomain buildPlayerDomain(CreatePlayerCommand command) {
        PlayerDomain playerDomain = PlayerDomain.builder()
                .id(PlayerIdentityDomain.generateRandomPart())
                .name(command.name())
                .email(command.email())
                .password(command.password())
                .nickname(command.nickname())
                .build();
        playerDomain.initialScore();
        return playerDomain;
    }
}
