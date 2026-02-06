package med.voll.api.medico;

import jakarta.annotation.Nonnull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DatosListaMedicoModelAssembler implements RepresentationModelAssembler<DatosListaMedico, EntityModel<DatosListaMedico>> {
    @Override
    @Nonnull
    public EntityModel<DatosListaMedico> toModel(DatosListaMedico medico) {
        return EntityModel.of(medico);
    }
}
