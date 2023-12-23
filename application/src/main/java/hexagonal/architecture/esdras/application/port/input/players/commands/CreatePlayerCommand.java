package hexagonal.architecture.esdras.application.port.input.players.commands;

public record CreatePlayerCommand(
        String name,
        String email,
        String password,
        String nickname

) {
}
