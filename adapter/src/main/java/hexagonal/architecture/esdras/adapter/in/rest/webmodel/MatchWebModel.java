package hexagonal.architecture.esdras.adapter.in.rest.webmodel;

import hexagonal.architecture.esdras.domain.entity.MatchDomain;

import java.time.Instant;
import java.util.List;

public record MatchWebModel(
        Instant date,
        String local,
        int matchWeight,

        List<PlayerWebModel> players

) {
    public static MatchWebModel fromDomainModel(MatchDomain matchDomain) {
        return new MatchWebModel(
                matchDomain.getDate(),
                matchDomain.getLocal(),
                matchDomain.getMatchWeight(),
                matchDomain.getPlayers().stream().map(PlayerWebModel::fromDomainModel).toList()
        );
    }
}
