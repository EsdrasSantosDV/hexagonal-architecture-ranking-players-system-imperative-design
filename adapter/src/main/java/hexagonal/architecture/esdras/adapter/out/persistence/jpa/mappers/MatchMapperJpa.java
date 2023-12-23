package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;

import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.MatchEntityJpa;
import hexagonal.architecture.esdras.domain.entity.MatchDomain;
import hexagonal.architecture.esdras.domain.vo.MatchIdentityDomain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatchMapperJpa {

    private MatchMapperJpa() {
    }

    public static MatchEntityJpa toJpaEntity(MatchDomain matchDomain) {
        if (matchDomain == null) {
            return null;
        }

        MatchEntityJpa entityJpa = new MatchEntityJpa();
        entityJpa.setId(matchDomain.getId().value());
        entityJpa.setDate(matchDomain.getDate());
        entityJpa.setLocal(matchDomain.getLocal());
        entityJpa.setMatchWeight(matchDomain.getMatchWeight());

        if (matchDomain.getPlayers() != null) {
            entityJpa.setPlayers(matchDomain.getPlayers().stream()
                    .map(PlayerMapperJpa::toJpaEntity)
                    .collect(Collectors.toSet()));
        }


        return entityJpa;
    }


    public static MatchDomain toDomainEntity(MatchEntityJpa entityJpa) {
        if (entityJpa == null) {
            return null;
        }

        MatchDomain matchDomain = MatchDomain.builder()
                .id(new MatchIdentityDomain(entityJpa.getId()))
                .date(entityJpa.getDate())
                .local(entityJpa.getLocal())
                .matchWeight(entityJpa.getMatchWeight())
                .build();

        if (entityJpa.getPlayers() != null) {
            matchDomain.setPlayers(entityJpa.getPlayers().stream()
                    .map(PlayerMapperJpa::toDomainEntity)
                    .collect(Collectors.toCollection(HashSet::new)));
        }

        return matchDomain;
    }

    public static Optional<MatchDomain> toDomainEntityOptional(MatchEntityJpa entityJpa) {
        return Optional.ofNullable(toDomainEntity(entityJpa));
    }

    public static List<MatchDomain> toDomainEntities(List<MatchEntityJpa> jpaEntities) {
        return jpaEntities.stream().map(MatchMapperJpa::toDomainEntity).collect(Collectors.toList());
    }


}
