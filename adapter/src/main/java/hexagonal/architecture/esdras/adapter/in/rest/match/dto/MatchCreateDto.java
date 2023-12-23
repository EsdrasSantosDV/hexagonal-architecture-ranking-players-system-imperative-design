package hexagonal.architecture.esdras.adapter.in.rest.match.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Schema(
        description = "DTO que representa uma partida a ser criado"
)
public class MatchCreateDto {

    @Positive(message = "Peso da partida deve ser maior que zero.")
    @Schema(description = "Peso da partida.", example = "10")
    int matchWeight;

    @NotNull(message = "Os players que irão jogar são obrigatorios")
    @Schema(description = "Lista dos players da partida.")
    List<@Valid MatchPlayerDto> players;

    @PastOrPresent(message = "A data da partida deve estar no passado ou no presente.")
    @Schema(description = "Data da partida.", example = "2023-11-01T00:00:00.000Z")
    private Instant date;

    @NotNull(message = "Local da partida é obrigatório.")
    @NotBlank(message = "Local da partida é obrigatório.")
    @Schema(description = "Local da partida.", example = "Casa do Esdras")
    private String local;


}

