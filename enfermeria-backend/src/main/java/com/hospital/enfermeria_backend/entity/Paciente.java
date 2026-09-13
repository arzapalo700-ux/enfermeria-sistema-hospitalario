package com.hospital.enfermeria_backend.entity;

import jakarta.persistence.*;

/**
 * NOTA: Esta es una version SIMPLIFICADA de Paciente, solo con los campos
 * que necesita el modulo de Enfermeria. Cuando el equipo integre todos los
 * proyectos individuales, esta clase se reemplaza por la entidad Paciente
 * "oficial" del compañero(a) que desarrolle ese modulo, manteniendo el mismo
 * nombre de tabla ("paciente") para que las relaciones sigan funcionando.
 */
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 20)
    private String cama;

    @Column(length = 150)
    private String diagnostico;

    public Paciente() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCama() { return cama; }
    public void setCama(String cama) { this.cama = cama; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
}