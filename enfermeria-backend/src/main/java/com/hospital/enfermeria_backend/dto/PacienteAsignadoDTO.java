package com.hospital.enfermeria_backend.dto;

import java.time.LocalDateTime;

public class PacienteAsignadoDTO {

    private Long asignacionId;
    private Long pacienteId;
    private String nombrePaciente;
    private String cama;
    private String diagnostico;
    private String turno;
    private LocalDateTime fechaAsignacion;

    public PacienteAsignadoDTO() {}

    public PacienteAsignadoDTO(Long asignacionId, Long pacienteId, String nombrePaciente,
                               String cama, String diagnostico, String turno,
                               LocalDateTime fechaAsignacion) {
        this.asignacionId = asignacionId;
        this.pacienteId = pacienteId;
        this.nombrePaciente = nombrePaciente;
        this.cama = cama;
        this.diagnostico = diagnostico;
        this.turno = turno;
        this.fechaAsignacion = fechaAsignacion;
    }

    public Long getAsignacionId() { return asignacionId; }
    public void setAsignacionId(Long asignacionId) { this.asignacionId = asignacionId; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }

    public String getCama() { return cama; }
    public void setCama(String cama) { this.cama = cama; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public LocalDateTime getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDateTime fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
}