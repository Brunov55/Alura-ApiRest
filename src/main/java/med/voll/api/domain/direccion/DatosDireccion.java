package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosDireccion(
        @NotBlank String calle,
        String numero,
        String complemento,
        @NotBlank String colonia,
        @NotBlank String ciudad,
        @NotBlank @Pattern(regexp = "^\\d{5}$") String codigo_postal,
        @NotBlank String estado
) {
}
