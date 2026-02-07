package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByPacienteIdAndFechaBetween(@NotNull Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndFechaBetweenAndMotivoIsNull(@NotNull Long idMedico, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}
