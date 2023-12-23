package hexagonal.architecture.esdras.adapter.out.persistence.jpa;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.PlayerEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers.PlayerMapperJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories.JpaPlayerPanacheRepository;
import hexagonal.architecture.esdras.application.port.input.players.commands.ResultPaginationPlayers;
import hexagonal.architecture.esdras.application.port.output.players.persistence.OutputPortPlayer;
import hexagonal.architecture.esdras.domain.entity.PlayerDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@LookupIfProperty(name = "persistence", stringValue = "mysql")
@ApplicationScoped
public class JpaPlayerAdapterRepository implements OutputPortPlayer {

    private final JpaPlayerPanacheRepository panacheRepository;

    public JpaPlayerAdapterRepository(JpaPlayerPanacheRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
    }


    @Override
    public PlayerDomain save(PlayerDomain playerDomain) {
        PlayerEntityJpa jpaEntity = PlayerMapperJpa.toJpaEntity(playerDomain);
        panacheRepository.getEntityManager().persist(jpaEntity);
        return PlayerMapperJpa.toDomainEntity(jpaEntity);
    }


    @Override
    public Optional<PlayerDomain> findByEmail(String email) {
        PlayerEntityJpa playerUni = panacheRepository.find("email", email).firstResult();
        return PlayerMapperJpa.toDomainEntityOptional(playerUni);
    }

    @Override
    public Optional<PlayerDomain> findById(String id) {
        PlayerEntityJpa playerUni = panacheRepository.findById(id);
        return PlayerMapperJpa.toDomainEntityOptional(playerUni);
    }

    @Override
    public ResultPaginationPlayers paginationPlayers(int pageNumber, int pageSize) {
        PanacheQuery<PlayerEntityJpa> query = panacheRepository.findAll();
        query.page(Page.of(pageNumber, pageSize));
        List<PlayerEntityJpa> players = query.list();


        List<PlayerDomain> playerDomains = PlayerMapperJpa.toDomainEntities(players);

        long totalItems = query.count();
        int totalPages = query.pageCount();

        return new ResultPaginationPlayers(playerDomains, totalItems, totalPages);
    }
}
