package com.hospital.enfermeria_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Representa el turno de trabajo de una enfermera (Mañana / Tarde / Noche)
 * en un servicio y fecha determinados.
 *
 * Relacion: TurnoEnfermeria (N) --> (1) Enfermera  => @ManyToOne
 */
@Entity
@Table(name = "turno_enfermeria")
public class TurnoEnfermeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enfermera_id", nullable = false)
    private Enfermera enfermera;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoTurno turno;

    @Column(length = 100)
    private String servicio;

    public TurnoEnfermeria() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Enfermera getEnfermera() { return enfermera; }
    public void setEnfermera(Enfermera enfermera) { this.enfermera = enfermera; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public TipoTurno getTurno() { return turno; }
    public void setTurno(TipoTurno turno) { this.turno = turno; }

    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }
}
