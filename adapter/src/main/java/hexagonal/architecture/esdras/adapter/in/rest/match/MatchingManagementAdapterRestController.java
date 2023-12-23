package hexagonal.architecture.esdras.adapter.in.rest.match;

import hexagonal.architecture.esdras.adapter.in.rest.common.ResultValidator;
import hexagonal.architecture.esdras.adapter.in.rest.match.dto.MatchCreateDto;
import hexagonal.architecture.esdras.adapter.in.rest.match.mapper.MatchMapper;
import hexagonal.architecture.esdras.adapter.in.rest.webmodel.MatchWebModel;
import hexagonal.architecture.esdras.application.port.input.matches.InputPortCreateMatchUseCase;
import hexagonal.architecture.esdras.application.port.input.matches.exceptions.PlayersNotFoundException;
import hexagonal.architecture.esdras.domain.exceptions.NumberPlayersByMatchException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Set;

@Path("/match")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Match Operations", description = "Match Operations ")
public class MatchingManagementAdapterRestController {

    private final InputPortCreateMatchUseCase createMatchUseCase;
    @Inject
    Validator validator;

    public MatchingManagementAdapterRestController(InputPortCreateMatchUseCase createMatchUseCase) {
        this.createMatchUseCase = createMatchUseCase;
    }

    @POST
    @Transactional
    @Path("/create-match")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Cria uma nova partida",
            description = "Cria um nova partida com base nos valores fornecidos."
    )
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "201",
                    description = "Match criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchWebModel.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content(mediaType = "application/json")
            ),

            @APIResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json")
            )
    })
    public Response createMatch(@RequestBody(
            description = "Dados da partida a ser criado",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchCreateDto.class))
    ) MatchCreateDto request) throws PlayersNotFoundException, NumberPlayersByMatchException {

        Set<ConstraintViolation<MatchCreateDto>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity(new ResultValidator(violations))
                            .build();
        }

        return Response.status(Response.Status.CREATED)
                .entity(MatchWebModel.fromDomainModel(createMatchUseCase.createMatch(MatchMapper.toCreateMatchCommand((request)))))
                .build();

    }

}
