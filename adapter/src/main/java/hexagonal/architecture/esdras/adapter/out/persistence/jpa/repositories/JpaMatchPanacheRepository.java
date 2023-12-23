package hexagonal.architecture.esdras.adapter.out.persistence.jpa.repositories;


import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.MatchEntityJpa;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JpaMatchPanacheRepository implements PanacheRepositoryBase<MatchEntityJpa, String> {

}
