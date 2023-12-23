package hexagonal.architecture.esdras.application.port.input.matches.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerMatchCommand {
    private String email;
    private String id;
}
