package hexagonal.architecture.esdras.application.port.input.matches;

import hexagonal.architecture.esdras.application.port.input.matches.commands.CreateMatchCommand;
import hexagonal.architecture.esdras.application.port.input.matches.exceptions.PlayersNotFoundException;
import hexagonal.architecture.esdras.domain.entity.MatchDomain;
import hexagonal.architecture.esdras.domain.exceptions.NumberPlayersByMatchException;

public interface InputPortCreateMatchUseCase {
    MatchDomain createMatch(CreateMatchCommand createMatchCommand) throws PlayersNotFoundException, NumberPlayersByMatchException;
}
