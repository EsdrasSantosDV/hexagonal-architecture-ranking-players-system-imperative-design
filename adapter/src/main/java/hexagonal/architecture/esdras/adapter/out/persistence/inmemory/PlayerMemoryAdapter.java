package hexagonal.architecture.esdras.adapter.out.persistence.inmemory;

import hexagonal.architecture.esdras.application.port.input.players.commands.ResultPaginationPlayers;
import hexagonal.architecture.esdras.application.port.output.players.persistence.OutputPortPlayer;
import hexagonal.architecture.esdras.domain.entity.PlayerDomain;
import hexagonal.architecture.esdras.domain.vo.PlayerIdentityDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@LookupIfProperty(name = "persistence", stringValue = "inmemory", lookupIfMissing = true)
@ApplicationScoped
public class PlayerMemoryAdapter implements OutputPortPlayer {

    private final Map<PlayerIdentityDomain, PlayerDomain> players = new ConcurrentHashMap<>();

    public PlayerMemoryAdapter() {
    }

    @Override
    public PlayerDomain save(PlayerDomain playerDomain) {
        players.put(playerDomain.getId(), playerDomain);
        return players.get(playerDomain.getId());
    }

    @Override
    public Optional<PlayerDomain> findByEmail(String email) {
        return players.values().stream()
                .filter(player -> email.equals(player.getEmail()))
                .findFirst();
    }

    @Override
    public Optional<PlayerDomain> findById(String id) {
        return Optional.ofNullable(players.get(id));
    }

    @Override
    public ResultPaginationPlayers paginationPlayers(int pageNumber, int pageSize) {
        int skip = pageNumber * pageSize;
        List<PlayerDomain> paginatedPlayers = players.values().stream()
                .skip(skip)
                .limit(pageSize)
                .collect(Collectors.toList());

        long totalItems = players.size();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        return new ResultPaginationPlayers(paginatedPlayers, totalItems, totalPages);
    }


}
