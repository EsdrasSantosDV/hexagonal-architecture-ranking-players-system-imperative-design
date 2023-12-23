package hexagonal.architecture.esdras.application.port.input.matches.commands;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CreateMatchCommand {
    List<PlayerMatchCommand> players;
    int matchWeight;
    private Instant date;
    private String local;
}
