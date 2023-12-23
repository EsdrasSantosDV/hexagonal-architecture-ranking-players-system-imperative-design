package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.vo.RankingIdentityDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RankingDomain {
    private final RankingIdentityDomain id;
    private Set<PlayerDomain> rankedPlayers;

    public void rankPlayers() {
        Comparator<PlayerDomain> rankingComparator = Comparator
                .comparingInt(PlayerDomain::getScore)
                .reversed();
        this.rankedPlayers = new TreeSet<>(rankingComparator);
    }

    public void addPlayer(PlayerDomain player) {
        rankedPlayers.add(player);
    }

}
