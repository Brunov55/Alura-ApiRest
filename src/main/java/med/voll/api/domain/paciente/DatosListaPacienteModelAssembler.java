package med.voll.api.domain.paciente;

import jakarta.annotation.Nonnull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DatosListaPacienteModelAssembler implements RepresentationModelAssembler<DatosListaPaciente, EntityModel<DatosListaPaciente>> {
    @Override
    @Nonnull
    public EntityModel<DatosListaPaciente> toModel(DatosListaPaciente medico) {
        return EntityModel.of(medico);
    }
}
