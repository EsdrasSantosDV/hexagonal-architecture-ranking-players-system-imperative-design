package hexagonal.architecture.esdras.domain.entity;

import hexagonal.architecture.esdras.domain.exceptions.NumberPlayersByMatchException;
import hexagonal.architecture.esdras.domain.vo.MatchIdentityDomain;
import hexagonal.architecture.esdras.domain.vo.PlayerIdentityDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MatchDomainTest {
    @Test
    @DisplayName("Adicionar e remover jogadores de uma partida")
    void testAddAndRemovePlayers() {
        MatchDomain match = MatchDomain.builder()
                .id(MatchIdentityDomain.generateRandomPart())
                .date(Instant.now())
                .local("Local Teste")
                .players(new HashSet<>())
                .matchWeight(5)
                .build();

        PlayerDomain player1 = new PlayerDomain(PlayerIdentityDomain.generateRandomPart(), "Jogador 1", "email1@test.com", "senha1", "nickname1", 10);
        PlayerDomain player2 = new PlayerDomain(PlayerIdentityDomain.generateRandomPart(), "Jogador 2", "email2@test.com", "senha2", "nickname2", 20);

        match.addPlayer(player1);
        match.addPlayer(player2);

        assertTrue(match.getPlayers().contains(player1));
        assertTrue(match.getPlayers().contains(player2));

        match.removePlayer(player1);

        assertFalse(match.getPlayers().contains(player1));
        assertTrue(match.getPlayers().contains(player2));
    }

    @Test
    @DisplayName("Validar número mínimo de jogadores em uma partida")
    void testValidateMinimumNumberOfPlayers() {
        MatchDomain match = MatchDomain.builder()
                .id(MatchIdentityDomain.generateRandomPart())
                .date(Instant.now())
                .local("Local Teste")
                .players(new HashSet<>())
                .matchWeight(5)
                .build();

        PlayerDomain player = new PlayerDomain(PlayerIdentityDomain.generateRandomPart(), "Jogador 1", "email1@test.com", "senha1", "nickname1", 10);
        match.addPlayer(player);

        assertThrows(NumberPlayersByMatchException.class, match::validateNumberPlayers);
    }
}