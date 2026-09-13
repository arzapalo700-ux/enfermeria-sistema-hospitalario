package com.hospital.enfermeria_backend.controller;

import com.hospital.enfermeria_backend.dto.PacienteAsignadoDTO;
import com.hospital.enfermeria_backend.service.AsignacionEnfermeriaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * RF-ENF-06: Consultar los pacientes asignados a cada enfermera.
 *
 * GET /api/enfermeras/{enfermeraId}/pacientes
 * GET /api/enfermeras/{enfermeraId}/pacientes?fecha=2026-09-06
 */
@RestController
@RequestMapping("/api/enfermeras")
public class AsignacionEnfermeriaController {

    private final AsignacionEnfermeriaService asignacionService;

    public AsignacionEnfermeriaController(AsignacionEnfermeriaService asignacionService) {
        this.asignacionService = asignacionService;
    }

    @GetMapping("/{enfermeraId}/pacientes")
    public List<PacienteAsignadoDTO> obtenerPacientesAsignados(
            @PathVariable Long enfermeraId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return asignacionService.obtenerPacientesAsignados(enfermeraId, fecha);
    }
}