package hexagonal.architecture.esdras.application.port.input.matches.exceptions;

public class PlayersNotFoundException extends Exception {
    public PlayersNotFoundException(String message) {
        super(message);
    }
}

