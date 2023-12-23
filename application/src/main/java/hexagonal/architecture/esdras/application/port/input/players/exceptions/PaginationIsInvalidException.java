package hexagonal.architecture.esdras.application.port.input.players.exceptions;

public class PaginationIsInvalidException extends Exception {
    public PaginationIsInvalidException(String message) {
        super(message);
    }
}
