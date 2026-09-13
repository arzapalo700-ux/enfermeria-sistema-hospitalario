package com.hospital.enfermeria_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "enfermera")
public class Enfermera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String servicio; // Ej: Medicina Interna, Emergencia, UCI

    public Enfermera() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }
}
