package med.voll.api.domain.medico;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private EntityManager manager;

    @Test
    @DisplayName("Deberia devolver null cuando el medico buscado existe pero no esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario1() {
        // Given or Arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("Juan", "juan@vollmed.com", "GARA850101HDFRRL09", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Pedro", "pedro@correo.com", "PEMS900215HDFDRR08");
        registrarConsulta(medico, paciente, lunesSiguienteALas10);
        // When or Act
        var medicoLibre = repository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);
        // Then or Assert
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Deberia devolver medico cuando el medico buscado esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario2() {
        // Given or Arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("Juan", "juan@vollmed.com", "GARA850101HDFRRL09", Especialidad.CARDIOLOGIA);
        // When or Act
        var medicoLibre = repository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);
        // Then or Assert
        assertThat(medicoLibre).isEqualTo(medico);
    }

    private Consulta registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha){
        var consulta = new Consulta(null, medico, paciente, fecha);
        manager.persist(consulta);
        return consulta;
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad){
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        manager.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento){
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        manager.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad){
        return new DatosRegistroMedico(nombre, email, "123456878", documento, especialidad, datosDireccion());
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento){
        return new DatosRegistroPaciente(
                nombre,
                email,
                "12345678",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "Calle A",
                "26",
                "",
                "San Pedro",
                "Ciudad de Mexico",
                "06600",
                "CDMX"
        );
    }
}