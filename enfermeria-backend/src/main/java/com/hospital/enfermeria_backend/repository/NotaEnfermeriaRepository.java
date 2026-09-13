package com.hospital.enfermeria_backend.repository;

import com.hospital.enfermeria_backend.entity.NotaEnfermeria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaEnfermeriaRepository extends JpaRepository<NotaEnfermeria, Long> {

    // RF-ENF-33: recupera el historial de notas de un paciente
    // (parte de su historia clinica), de la mas reciente a la mas antigua.
    List<NotaEnfermeria> findByPacienteIdOrderByFechaHoraDesc(Long pacienteId);
}
