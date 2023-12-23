package hexagonal.architecture.esdras.application.port.input.players.commands;

import hexagonal.architecture.esdras.domain.entity.PlayerDomain;

import java.util.List;

public record ResultPaginationPlayers(List<PlayerDomain> items, long totalItems, long totalPages) {
}
