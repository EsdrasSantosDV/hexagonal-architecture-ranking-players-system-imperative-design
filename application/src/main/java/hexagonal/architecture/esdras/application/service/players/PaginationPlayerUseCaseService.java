package hexagonal.architecture.esdras.application.service.players;

import hexagonal.architecture.esdras.application.port.input.players.InputPortPaginationPlayerUseCase;
import hexagonal.architecture.esdras.application.port.input.players.commands.PaginationCommand;
import hexagonal.architecture.esdras.application.port.input.players.commands.ResultPaginationPlayers;
import hexagonal.architecture.esdras.application.port.output.players.persistence.OutputPortPlayer;

public class PaginationPlayerUseCaseService implements InputPortPaginationPlayerUseCase {

    private final OutputPortPlayer outputPortPlayer;

    public PaginationPlayerUseCaseService(OutputPortPlayer outputPortPlayer) {
        this.outputPortPlayer = outputPortPlayer;
    }


    @Override
    public ResultPaginationPlayers paginationPlayers(PaginationCommand paginationCommand) {
        return outputPortPlayer.paginationPlayers(paginationCommand.page(), paginationCommand.size());
    }
}
