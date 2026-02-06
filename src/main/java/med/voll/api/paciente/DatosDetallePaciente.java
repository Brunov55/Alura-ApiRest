package med.voll.api.paciente;

import med.voll.api.direccion.Direccion;

public record DatosDetallePaciente(
        Long id,
        String nombre,
        String telefono,
        String email,
        String documento,
        Direccion direccion
) {
    public DatosDetallePaciente(Paciente paciente) {
        this(
            paciente.getId(),
            paciente.getNombre(),
            paciente.getTelefono(),
            paciente.getEmail(),
            paciente.getDocumento(),
            paciente.getDireccion());
    }
}
