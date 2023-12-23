package hexagonal.architecture.esdras.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankingIdentityDomainTest {
    @Test
    @DisplayName("Verificar se exceção é lançada quando o valor é null")
    void testRankingIdentityDomainWithNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new RankingIdentityDomain(null),
                "Esperado lançamento de exceção quando valor é null"
        );
    }

    @Test
    @DisplayName("Verificar se exceção é lançada quando o valor é vazio")
    void testRankingIdentityDomainWithEmptyValue() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RankingIdentityDomain(""),
                "Esperado lançamento de exceção quando valor é vazio"
        );
    }

    @Test
    @DisplayName("Verificar geração de RankingIdentityDomain aleatório")
    void testRandomRankingIdentityDomainGeneration() {
        RankingIdentityDomain randomId = RankingIdentityDomain.generateRandomPart();

        assertNotNull(randomId, "Random ID não deve ser null");
        assertNotNull(randomId.value(), "O valor do random ID não deve ser null");
        assertFalse(randomId.value().isBlank(), "O valor do random ID não deve ser branco ou vazio");

        assertTrue(randomId.value().startsWith(RankingIdentityDomain.RANKING),
                "O valor do random ID deve começar com o prefixo definido");

        assertEquals(RankingIdentityDomain.RANKING.length() + RankingIdentityDomain.LENGTH_OF_RANDOM_PART,
                randomId.value().length(),
                "O comprimento do ID deve ser igual ao comprimento do prefixo mais o comprimento da parte aleatória");

        assertTrue(randomId.value().substring(RankingIdentityDomain.RANKING.length()).chars()
                        .allMatch(c -> RankingIdentityDomain.ALPHABET.indexOf(c) != -1),
                "Todos os caracteres da parte aleatória do ID devem estar dentro dos caracteres permitidos"
        );
    }
}