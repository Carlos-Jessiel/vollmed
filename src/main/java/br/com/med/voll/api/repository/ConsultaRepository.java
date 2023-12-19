package br.com.med.voll.api.repository;

import br.com.med.voll.api.model.entities.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    @Query(nativeQuery = true, value = """
            select * from consultas c
                 where
                 c.medico_id = :id
                 and
                 c.data >= (CURRENT_DATE)
                 and
                 c.motivo_cancelamento is null
            """)
    Page<Consulta> findAllConsultaByMedicoId(Long id, Pageable paginacao);

    @Query(nativeQuery = true, value = """
            select * from consultas c
                 where
                 c.paciente_id = :id
                 and
                 c.data >= (CURRENT_DATE)
                 and
                 c.motivo_cancelamento is null
            """)
    Page<Consulta> findAllConsultaByPacienteId(Long id, Pageable paginacao);
}
