package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.exceptions.NumberPlayersByMatchException;
import hexagonal.architecture.esdras.domain.vo.MatchIdentityDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;

@Data
@Builder
@AllArgsConstructor
public class MatchDomain {
    private final MatchIdentityDomain id;
    private Instant date;
    private String local;
    private HashSet<PlayerDomain> players;
    private int matchWeight;

    public void addPlayer(PlayerDomain player) {
        if (players == null) {
            players = new HashSet<>();
        }
        players.add(player);
    }

    public void removePlayer(PlayerDomain player) {
        players.remove(player);
    }

    public void addPlayers(HashSet<PlayerDomain> players) {
        this.players.addAll(players);
    }

    public void removePlayers(HashSet<PlayerDomain> players) {
        this.players.removeAll(players);
    }

    public void clearPlayers() {
        this.players.clear();
    }

    public void updateDate(Instant date) {
        this.date = date;
    }

    public void validateNumberPlayers() throws NumberPlayersByMatchException {
        if (players.size() < 2) {
            throw new NumberPlayersByMatchException("O numero de jogadores deve ser maior que 2");
        }
    }
}
