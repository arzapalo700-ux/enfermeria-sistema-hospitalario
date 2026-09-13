package com.hospital.enfermeria_backend.dto;

import java.time.LocalDateTime;

public class NotaEnfermeriaResponseDTO {

    private Long id;
    private Long pacienteId;
    private String nombrePaciente;
    private Long enfermeraId;
    private String nombreEnfermera;
    private LocalDateTime fechaHora;
    private String contenido;

    public NotaEnfermeriaResponseDTO() {}

    public NotaEnfermeriaResponseDTO(Long id, Long pacienteId, String nombrePaciente,
                                     Long enfermeraId, String nombreEnfermera,
                                     LocalDateTime fechaHora, String contenido) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.nombrePaciente = nombrePaciente;
        this.enfermeraId = enfermeraId;
        this.nombreEnfermera = nombreEnfermera;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }

    public Long getEnfermeraId() { return enfermeraId; }
    public void setEnfermeraId(Long enfermeraId) { this.enfermeraId = enfermeraId; }

    public String getNombreEnfermera() { return nombreEnfermera; }
    public void setNombreEnfermera(String nombreEnfermera) { this.nombreEnfermera = nombreEnfermera; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}
