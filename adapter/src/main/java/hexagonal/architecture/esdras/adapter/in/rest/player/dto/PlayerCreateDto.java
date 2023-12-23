package hexagonal.architecture.esdras.adapter.in.rest.player.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
@Schema(
        description = "DTO que representa um player a ser criado"
)
public class PlayerCreateDto {

    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 255, message = "Nome não pode exceder 255 caracteres.")
    @Schema(description = "Nome do player.", example = "Esdras Santos de Oliveira")
    private String name;

    @Email(message = "Email inválido.")
    @NotBlank(message = "Email é obrigatório.")
    @Size(max = 255, message = "Email não pode exceder 255 caracteres.")
    @Schema(description = "Email do player.", example = "gmesdras2015@gmail.com")
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(max = 255, message = "Senha não pode exceder 255 caracteres.")
    @Schema(description = "Senha do player.", example = "123456@1354sax")
    private String password;

    @NotNull(message = "Nickname é obrigatório.")
    @Size(max = 255, message = "Nickname não pode exceder 255 caracteres.")
    @Schema(description = "Nickname do player.", example = "esdrasKhan")
    private String nickname;

}
