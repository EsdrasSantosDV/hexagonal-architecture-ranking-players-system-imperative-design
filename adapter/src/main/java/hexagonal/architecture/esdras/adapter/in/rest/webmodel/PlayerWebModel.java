package hexagonal.architecture.esdras.adapter.in.rest.webmodel;

import hexagonal.architecture.esdras.domain.entity.PlayerDomain;

public record PlayerWebModel(String id, String name, String email, String nickname) {
    public static PlayerWebModel fromDomainModel(PlayerDomain playerDomain) {
        return new PlayerWebModel(
                playerDomain.getId().value(),
                playerDomain.getName(),
                playerDomain.getEmail(),
                playerDomain.getNickname()
        );
    }


}
