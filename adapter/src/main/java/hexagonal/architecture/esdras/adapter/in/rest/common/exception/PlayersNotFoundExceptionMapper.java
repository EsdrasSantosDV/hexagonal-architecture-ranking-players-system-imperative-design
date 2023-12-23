package hexagonal.architecture.esdras.adapter.in.rest.common.exception;

import hexagonal.architecture.esdras.adapter.in.rest.common.ErrorEntity;
import hexagonal.architecture.esdras.application.port.input.matches.exceptions.PlayersNotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PlayersNotFoundExceptionMapper implements ExceptionMapper<PlayersNotFoundException> {

    @Override
    public Response toResponse(PlayersNotFoundException exception) {
        ErrorEntity errorEntity = new ErrorEntity(
                Response.Status.NOT_FOUND.getStatusCode(),
                exception.getMessage()
        );
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorEntity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
