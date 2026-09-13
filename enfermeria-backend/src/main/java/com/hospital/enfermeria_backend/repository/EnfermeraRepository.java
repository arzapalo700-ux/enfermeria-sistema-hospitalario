package com.hospital.enfermeria_backend.repository;

import com.hospital.enfermeria_backend.entity.Enfermera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnfermeraRepository extends JpaRepository<Enfermera, Long> {
}
