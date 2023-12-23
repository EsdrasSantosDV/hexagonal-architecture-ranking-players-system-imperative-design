package hexagonal.architecture.esdras.adapter.out.persistence.jpa.mappers;


import hexagonal.architecture.esdras.adapter.out.persistence.jpa.entities.PlayerEntityJpa;
import hexagonal.architecture.esdras.domain.entity.PlayerDomain;
import hexagonal.architecture.esdras.domain.vo.PlayerIdentityDomain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerMapperJpa {

    private PlayerMapperJpa() {
    }

    public static PlayerEntityJpa toJpaEntity(PlayerDomain playerDomain) {
        if (playerDomain == null) {
            return null;
        }

        PlayerEntityJpa entityJpa = new PlayerEntityJpa();
        entityJpa.setId(playerDomain.getId().value());
        entityJpa.setName(playerDomain.getName());
        entityJpa.setEmail(playerDomain.getEmail());
        entityJpa.setPassword(playerDomain.getPassword());
        entityJpa.setNickname(playerDomain.getNickname());
        entityJpa.setScore(playerDomain.getScore());

        return entityJpa;
    }

    public static PlayerDomain toDomainEntity(PlayerEntityJpa entityJpa) {
        if (entityJpa == null) {
            return null;
        }

        return PlayerDomain.builder()
                .id(new PlayerIdentityDomain(entityJpa.getId()))
                .name(entityJpa.getName())
                .email(entityJpa.getEmail())
                .password(entityJpa.getPassword())
                .nickname(entityJpa.getNickname())
                .score(entityJpa.getScore())
                .build();
    }

    public static Optional<PlayerDomain> toDomainEntityOptional(PlayerEntityJpa entityJpa) {
        return Optional.ofNullable(toDomainEntity(entityJpa));
    }

    public static List<PlayerDomain> toDomainEntities(List<PlayerEntityJpa> jpaEntities) {
        return jpaEntities.stream().map(PlayerMapperJpa::toDomainEntity).collect(Collectors.toList());
    }
}