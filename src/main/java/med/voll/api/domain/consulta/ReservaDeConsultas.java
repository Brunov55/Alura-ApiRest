package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.validaciones.cancelacion.IValidadorCancelamientoDeConsulta;
import med.voll.api.domain.consulta.validaciones.reserva.IValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<IValidadorDeConsultas> validadores;

    @Autowired
    private List<IValidadorCancelamientoDeConsulta> validadoresCancelacion;

    public DatosDetalleConsulta reservar(DatosReservaConsulta datos){

        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe un paciente con el id: " + datos.idPaciente());
        }

        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe un medico con el id: " + datos.idPaciente());
        }

        validadores.forEach(validador -> validador.validar(datos));

        var medico = elegirMedico(datos);
        if(medico == null){
            throw new ValidacionException("No hay medicos disponibles en la fecha seleccionada.");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    public void cancelar(DatosCancelacionConsulta datos){
        if(!consultaRepository.existsById(datos.idConsulta())) throw new ValidacionException("No existe una consulta con el id: " + datos.idConsulta());

        validadoresCancelacion.forEach(validador -> validador.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if(datos.idMedico() != null) return medicoRepository.getReferenceById(datos.idMedico());
        if(datos.especialidad() == null){
            throw new ValidacionException("Debe especificar una especialidad o un medico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }

}
