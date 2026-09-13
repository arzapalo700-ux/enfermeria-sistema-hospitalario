package com.hospital.enfermeria_backend.repository;

import com.hospital.enfermeria_backend.entity.TurnoEnfermeria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoEnfermeriaRepository extends JpaRepository<TurnoEnfermeria, Long> {
}