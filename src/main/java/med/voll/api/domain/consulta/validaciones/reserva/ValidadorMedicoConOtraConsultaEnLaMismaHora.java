package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnLaMismaHora implements IValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos) {
        var primerHorario = datos.fecha().minusHours(1);
        var ultimoHorario = datos.fecha().plusHours(1);
        var medicoTieneOtraConsultaEnElMismoHorario = repository.existsByMedicoIdAndFechaBetweenAndMotivoIsNull(datos.idMedico(), primerHorario, ultimoHorario);
        if(medicoTieneOtraConsultaEnElMismoHorario) throw new ValidacionException("El medico ya tiene una consulta en ese horario.");
    }
}
