package com.hospital.enfermeria_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotaEnfermeriaRequestDTO {

    @NotNull(message = "El id del paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El id de la enfermera es obligatorio")
    private Long enfermeraId;

    @NotBlank(message = "El contenido de la nota no puede estar vacio")
    private String contenido;

    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }

    public Long getEnfermeraId() { return enfermeraId; }
    public void setEnfermeraId(Long enfermeraId) { this.enfermeraId = enfermeraId; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}
