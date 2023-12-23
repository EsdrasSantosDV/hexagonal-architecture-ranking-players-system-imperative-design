package hexagonal.architecture.esdras.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerIdentityDomainTest {
    @Test
    @DisplayName("Verificar se exceção é lançada quando o valor é null")
    void testPlayerIdentityDomainWithNullValue() {
        assertThrows(
                NullPointerException.class,
                () -> new PlayerIdentityDomain(null),
                "Esperado lançamento de exceção quando valor é null"
        );
    }

    @Test
    @DisplayName("Verificar se exceção é lançada quando o valor é vazio")
    void testPlayerIdentityDomainWithEmptyValue() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new PlayerIdentityDomain(""),
                "Esperado lançamento de exceção quando valor é vazio"
        );
    }

    @Test
    @DisplayName("Verificar geração de PlayerIdentityDomain aleatório")
    void testRandomPlayerIdentityDomainGeneration() {
        PlayerIdentityDomain randomId = PlayerIdentityDomain.generateRandomPart();

        assertNotNull(randomId, "Random ID não deve ser null");
        assertNotNull(randomId.value(), "O valor do random ID não deve ser null");
        assertFalse(randomId.value().isBlank(), "O valor do random ID não deve ser branco ou vazio");

        assertTrue(randomId.value().startsWith(PlayerIdentityDomain.GAMER_FLAG),
                "O valor do random ID deve começar com o prefixo definido");

        assertEquals(PlayerIdentityDomain.GAMER_FLAG.length() + PlayerIdentityDomain.LENGTH_OF_RANDOM_PART,
                randomId.value().length(),
                "O comprimento do ID deve ser igual ao comprimento do prefixo mais o comprimento da parte aleatória");

        assertTrue(randomId.value().substring(PlayerIdentityDomain.GAMER_FLAG.length()).chars()
                        .allMatch(c -> PlayerIdentityDomain.ALPHABET.indexOf(c) != -1),
                "Todos os caracteres da parte aleatória do ID devem estar dentro dos caracteres permitidos"
        );
    }
}