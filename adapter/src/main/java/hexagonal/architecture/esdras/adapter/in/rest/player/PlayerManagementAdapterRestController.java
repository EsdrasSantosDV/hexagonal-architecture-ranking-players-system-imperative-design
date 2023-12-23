package hexagonal.architecture.esdras.adapter.in.rest.player;

import hexagonal.architecture.esdras.adapter.in.rest.common.PaginationResponse;
import hexagonal.architecture.esdras.adapter.in.rest.common.ResultValidator;
import hexagonal.architecture.esdras.adapter.in.rest.player.dto.PlayerCreateDto;
import hexagonal.architecture.esdras.adapter.in.rest.player.mapper.PlayerMapper;
import hexagonal.architecture.esdras.adapter.in.rest.webmodel.PlayerWebModel;
import hexagonal.architecture.esdras.application.port.input.players.InputPortCreatePlayerUseCase;
import hexagonal.architecture.esdras.application.port.input.players.InputPortPaginationPlayerUseCase;
import hexagonal.architecture.esdras.application.port.input.players.commands.ResultPaginationPlayers;
import hexagonal.architecture.esdras.application.port.input.players.exceptions.EmailIsAlreadyUsedException;
import hexagonal.architecture.esdras.application.port.input.players.exceptions.PaginationIsInvalidException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Players Operations", description = "Players management operations")
public class PlayerManagementAdapterRestController {

    private final InputPortCreatePlayerUseCase createPlayerPortUseCase;
    private final InputPortPaginationPlayerUseCase paginationPlayerUseCase;

    @Inject
    Validator validator;

    public PlayerManagementAdapterRestController(InputPortCreatePlayerUseCase createPlayerPortUseCase,
                                                 InputPortPaginationPlayerUseCase paginationPlayerUseCase) {
        this.createPlayerPortUseCase = createPlayerPortUseCase;
        this.paginationPlayerUseCase = paginationPlayerUseCase;
    }


    @POST
    @Transactional
    @Path("/create-player")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Cria um novo player",
            description = "Cria um novo player com base nos valores fornecidos."
    )
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "201",
                    description = "Player criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlayerWebModel.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Dados inválidos fornecidos",
                    content = @Content(mediaType = "application/json")
            ),
            @APIResponse(
                    responseCode = "409",
                    description = "Conflito, por exemplo, email já utilizado",
                    content = @Content(mediaType = "application/json")
            ),
            @APIResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json")
            )
    })
    public Response createPlayer(@RequestBody(
            description = "Dados do player a ser criado",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlayerCreateDto.class))
    ) PlayerCreateDto request) throws EmailIsAlreadyUsedException {
        Set<ConstraintViolation<PlayerCreateDto>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity(new ResultValidator(violations))
                            .build();
        }
        return Response.status(Response.Status.CREATED)
                .entity(PlayerWebModel.fromDomainModel(createPlayerPortUseCase.createPlayer(PlayerMapper.toCreatePlayerCommand(request))))
                .build();
    }


    @GET
    @Path("/pagination")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Lista todos os players paginados",
            description = "Lista todos os players paginados com base nos valores fornecidos."
    )
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Player criado com sucesso",
                    content = @Content(mediaType = "application/json")
            ),
            @APIResponse(
                    responseCode = "500",
                    description = "Erro interno no servidor",
                    content = @Content(mediaType = "application/json")
            )
    })
    public Response paginationPlayers(@Valid @Parameter(description = "Número da página", example = "0")
                                      @QueryParam("page") @Min(0) int page,
                                      @Valid @Parameter(description = "Tamanho da página", example = "10")
                                      @QueryParam("size") @Min(1) @NotNull int size) throws PaginationIsInvalidException {

        ResultPaginationPlayers result = paginationPlayerUseCase.paginationPlayers(PlayerMapper.toPaginationCommand(page, size));

        List<PlayerWebModel> playerWebModels = result.items().stream()
                .map(PlayerWebModel::fromDomainModel)
                .collect(Collectors.toList());

        PaginationResponse<PlayerWebModel> response = PaginationResponse.<PlayerWebModel>builder()
                .items(playerWebModels)
                .totalItems(result.totalItems())
                .totalPages(result.totalPages())
                .build();

        return Response.ok(response).build();
    }


}
