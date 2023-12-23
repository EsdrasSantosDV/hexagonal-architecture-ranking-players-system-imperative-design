package hexagonal.architecture.esdras.adapter.in.rest.common.exception;


import hexagonal.architecture.esdras.adapter.in.rest.common.ErrorEntity;
import hexagonal.architecture.esdras.application.port.input.players.exceptions.EmailIsAlreadyUsedException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PaginationIsInvalidMapper implements ExceptionMapper<EmailIsAlreadyUsedException> {

    @Override
    public Response toResponse(EmailIsAlreadyUsedException exception) {
        ErrorEntity errorEntity = new ErrorEntity(
                Response.Status.CONFLICT.getStatusCode(),
                exception.getMessage()
        );
        return Response.status(Response.Status.CONFLICT)
                .entity(errorEntity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}