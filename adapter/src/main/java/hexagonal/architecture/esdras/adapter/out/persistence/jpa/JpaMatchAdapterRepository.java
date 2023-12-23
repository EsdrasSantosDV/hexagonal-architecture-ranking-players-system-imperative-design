package hexagonal.architecture.esdras.adapter.out.persistence.jpa;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.MatchEntityJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers.MatchMapperJpa;
import hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories.JpaMatchPanacheRepository;
import hexagonal.architecture.esdras.application.port.output.matches.persistence.OutputPortMatch;
import hexagonal.architecture.esdras.domain.entity.MatchDomain;
import io.quarkus.arc.lookup.LookupIfProperty;
import jakarta.enterprise.context.ApplicationScoped;

@LookupIfProperty(name = "persistence", stringValue = "mysql")
@ApplicationScoped
public class JpaMatchAdapterRepository implements OutputPortMatch {


    private final JpaMatchPanacheRepository panacheRepository;

    public JpaMatchAdapterRepository(JpaMatchPanacheRepository panacheRepository) {
        this.panacheRepository = panacheRepository;
    }

    @Override
    public MatchDomain save(MatchDomain matchDomain) {
        MatchEntityJpa jpaEntity = MatchMapperJpa.toJpaEntity(matchDomain);
        panacheRepository.getEntityManager().persist(jpaEntity);
        return MatchMapperJpa.toDomainEntity(jpaEntity);
    }
}
