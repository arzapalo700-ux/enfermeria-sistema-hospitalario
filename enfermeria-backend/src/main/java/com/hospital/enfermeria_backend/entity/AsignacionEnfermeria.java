package com.hospital.enfermeria_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * RF-ENF-06: El sistema debera permitir consultar los pacientes
 * asignados a cada enfermera.
 *
 * Es la tabla intermedia entre TurnoEnfermeria y Paciente:
 *   TURNO_ENFERMERIA (1) --- (N) ASIGNACION_ENFERMERIA (N) --- (1) PACIENTE
 *
 * Se modela como entidad propia (y no como @ManyToMany directo) porque
 * necesitamos guardar metadatos de la asignacion (fecha, activo).
 */
@Entity
@Table(name = "asignacion_enfermeria")
public class AsignacionEnfermeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turno_enfermeria_id", nullable = false)
    private TurnoEnfermeria turnoEnfermeria;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    private LocalDateTime fechaAsignacion = LocalDateTime.now();

    @Column(nullable = false)
    private boolean activo = true;

    public AsignacionEnfermeria() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TurnoEnfermeria getTurnoEnfermeria() { return turnoEnfermeria; }
    public void setTurnoEnfermeria(TurnoEnfermeria turnoEnfermeria) { this.turnoEnfermeria = turnoEnfermeria; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public LocalDateTime getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDateTime fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}