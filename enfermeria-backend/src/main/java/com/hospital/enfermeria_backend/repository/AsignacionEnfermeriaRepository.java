package com.hospital.enfermeria_backend.repository;

import com.hospital.enfermeria_backend.entity.AsignacionEnfermeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AsignacionEnfermeriaRepository extends JpaRepository<AsignacionEnfermeria, Long> {

    /**
     * RF-ENF-06: consulta los pacientes asignados a una enfermera.
     * Si se pasa "fecha", filtra ademas por el turno de esa fecha.
     * Solo se consideran asignaciones activas.
     */
    @Query("""
           SELECT a FROM AsignacionEnfermeria a
           JOIN FETCH a.paciente p
           JOIN FETCH a.turnoEnfermeria t
           WHERE t.enfermera.id = :enfermeraId
             AND a.activo = true
             AND (:fecha IS NULL OR t.fecha = :fecha)
           ORDER BY p.nombre ASC
           """)
    List<AsignacionEnfermeria> findPacientesAsignados(@Param("enfermeraId") Long enfermeraId,
                                                      @Param("fecha") LocalDate fecha);
}
