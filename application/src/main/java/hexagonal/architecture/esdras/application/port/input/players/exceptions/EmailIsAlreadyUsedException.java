package hexagonal.architecture.esdras.application.port.input.players.exceptions;


public class EmailIsAlreadyUsedException extends Exception {
    public EmailIsAlreadyUsedException(String message) {
        super(message);
    }
}

