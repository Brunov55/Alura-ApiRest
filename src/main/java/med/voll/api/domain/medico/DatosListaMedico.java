package med.voll.api.domain.medico;

public record DatosListaMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        String especialidad
) {
    public DatosListaMedico(Medico medico) {
        this(medico.getId(),
            medico.getNombre(),
            medico.getEmail(),
            medico.getDocumento(),
            String.valueOf(medico.getEspecialidad()));
    }
}
