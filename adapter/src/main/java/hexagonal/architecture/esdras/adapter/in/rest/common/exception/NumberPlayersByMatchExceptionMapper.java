package hexagonal.architecture.esdras.adapter.in.rest.common.exception;

import hexagonal.architecture.esdras.adapter.in.rest.common.ErrorEntity;
import hexagonal.architecture.esdras.domain.exceptions.NumberPlayersByMatchException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NumberPlayersByMatchExceptionMapper implements ExceptionMapper<NumberPlayersByMatchException> {

    @Override
    public Response toResponse(NumberPlayersByMatchException exception) {
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