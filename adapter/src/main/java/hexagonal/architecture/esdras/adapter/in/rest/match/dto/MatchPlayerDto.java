package hexagonal.architecture.esdras.adapter.in.rest.match.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@Schema(
        description = "DTO que representa uma player da partida a ser criado"
)
public class MatchPlayerDto {


    @Schema(description = "lrussell@gmail.com", example = "gmesdras2015@gmail.com")
    private String email;


    @Schema(description = "Id do player.", example = "PLAYER37KV33CUCP9Y3GB")
    private String id;
}
