package hexagonal.architecture.esdras.application.port.input.players;

import hexagonal.architecture.esdras.application.port.input.players.commands.PaginationCommand;
import hexagonal.architecture.esdras.application.port.input.players.commands.ResultPaginationPlayers;
import hexagonal.architecture.esdras.application.port.input.players.exceptions.PaginationIsInvalidException;

public interface InputPortPaginationPlayerUseCase {

    ResultPaginationPlayers paginationPlayers(PaginationCommand paginationCommand) throws PaginationIsInvalidException;
}
