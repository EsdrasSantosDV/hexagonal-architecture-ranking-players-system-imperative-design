package hexagonal.architecture.esdras.adapter.in.rest.common.exception;


import hexagonal.architecture.esdras.adapter.in.rest.common.ErrorEntity;
import hexagonal.architecture.esdras.application.port.input.players.exceptions.PaginationIsInvalidException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmailAlreadyUsedExceptionMapper implements ExceptionMapper<PaginationIsInvalidException> {

    @Override
    public Response toResponse(PaginationIsInvalidException exception) {
        ErrorEntity errorEntity = new ErrorEntity(
                Response.Status.BAD_REQUEST.getStatusCode(),
                exception.getMessage()
        );
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorEntity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}