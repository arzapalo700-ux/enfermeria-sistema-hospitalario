package com.hospital.enfermeria_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * RF-ENF-31: El sistema debera permitir registrar notas de enfermeria.
 * RF-ENF-32: Cada nota debe registrar fecha, hora y profesional
 *            (cubierto con fechaHora + enfermera).
 * RF-ENF-33: Las notas forman parte de la historia clinica del paciente
 *            (relacion ManyToOne con Paciente).
 *
 * Relaciones:
 *   NotaEnfermeria (N) --> (1) Paciente
 *   NotaEnfermeria (N) --> (1) Enfermera
 */
@Entity
@Table(name = "nota_enfermeria")
public class NotaEnfermeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enfermera_id", nullable = false)
    private Enfermera enfermera;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    public NotaEnfermeria() {}

    @PrePersist
    public void prePersist() {
        if (fechaHora == null) {
            fechaHora = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Enfermera getEnfermera() { return enfermera; }
    public void setEnfermera(Enfermera enfermera) { this.enfermera = enfermera; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}