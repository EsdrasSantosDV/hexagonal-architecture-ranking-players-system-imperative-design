package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.vo.PlayerIdentityDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerDomainTest {
    @Test
    @DisplayName("Aumentar e diminuir a pontuação do jogador")
    void testIncreaseAndDecreaseScore() {
        PlayerDomain player = new PlayerDomain(PlayerIdentityDomain.generateRandomPart(), "Jogador Teste", "email@test.com", "senha123", "nicknameTeste", 10);

        player.increaseScoreBy(5);
        assertEquals(15, player.getScore());

        player.decreaseScoreBy(3);
        assertEquals(12, player.getScore());
    }

    @Test
    @DisplayName("Inicializar a pontuação do jogador")
    void testInitialScore() {
        PlayerDomain player = new PlayerDomain(PlayerIdentityDomain.generateRandomPart(), "Jogador Teste", "email@test.com", "senha123", "nicknameTeste", 10);

        player.initialScore();
        assertEquals(0, player.getScore());
    }
}