package hexagonal.architecture.esdras.application.port.input.players;

import hexagonal.architecture.esdras.application.port.input.players.commands.CreatePlayerCommand;
import hexagonal.architecture.esdras.application.port.input.players.exceptions.EmailIsAlreadyUsedException;
import hexagonal.architecture.esdras.domain.entity.PlayerDomain;

public interface InputPortCreatePlayerUseCase {
    PlayerDomain createPlayer(CreatePlayerCommand command) throws EmailIsAlreadyUsedException;
}
