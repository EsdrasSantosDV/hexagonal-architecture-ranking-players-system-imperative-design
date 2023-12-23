package hexagonal.architecture.esdras.application.port.output.players.persistence;

import hexagonal.architecture.esdras.application.port.input.players.commands.ResultPaginationPlayers;
import hexagonal.architecture.esdras.domain.entity.PlayerDomain;

import java.util.Optional;

public interface OutputPortPlayer {
    PlayerDomain save(PlayerDomain playerDomain);

    Optional<PlayerDomain> findByEmail(String email);

    Optional<PlayerDomain> findById(String id);


    ResultPaginationPlayers paginationPlayers(int pageNumber, int pageSize);


}
