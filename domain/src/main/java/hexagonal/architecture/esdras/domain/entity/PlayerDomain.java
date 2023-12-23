package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.vo.PlayerIdentityDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlayerDomain {
    private final PlayerIdentityDomain id;
    private String name;
    private String email;
    private String password;
    private String nickname;
    private Integer score;

    public void increaseScoreBy(int augend) {
        this.score += augend;
    }

    public void decreaseScoreBy(int augend) {
        this.score -= augend;
    }

    public void initialScore() {
        this.score = 0;
    }


}
