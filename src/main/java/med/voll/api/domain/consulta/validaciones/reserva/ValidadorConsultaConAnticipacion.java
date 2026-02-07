package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorConsultaConAnticipacionReserva")
public class ValidadorConsultaConAnticipacion implements IValidadorDeConsultas{

    public void validar(DatosReservaConsulta datos) {
        var ahora = LocalDateTime.now();
        var fechaConsulta = datos.fecha();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();

        if(diferenciaEnMinutos < 30)
            throw new ValidacionException("La consulta debe ser realizada al menos 30 minutos antes de la fecha seleccionada.");
    }
}
