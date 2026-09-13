package com.hospital.enfermeria_backend.repository;

import com.hospital.enfermeria_backend.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}