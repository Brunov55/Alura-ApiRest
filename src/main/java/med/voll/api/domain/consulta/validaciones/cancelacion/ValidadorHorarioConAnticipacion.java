package med.voll.api.domain.consulta.validaciones.cancelacion;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelacionConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorConsultaConAnticipacionCancelacion")
public class ValidadorHorarioConAnticipacion implements IValidadorCancelamientoDeConsulta{
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelacionConsulta datos) {
        var consulta = repository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if(diferenciaEnHoras < 24) throw new ValidacionException("Las consultas solo pueden ser canceladas con 24 horas de anticipacion.");
    }
}
