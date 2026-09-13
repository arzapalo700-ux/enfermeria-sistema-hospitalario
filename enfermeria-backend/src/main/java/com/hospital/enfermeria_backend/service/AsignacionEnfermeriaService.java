package com.hospital.enfermeria_backend.service;

import com.hospital.enfermeria_backend.dto.PacienteAsignadoDTO;
import com.hospital.enfermeria_backend.entity.AsignacionEnfermeria;
import com.hospital.enfermeria_backend.exception.ResourceNotFoundException;
import com.hospital.enfermeria_backend.repository.AsignacionEnfermeriaRepository;
import com.hospital.enfermeria_backend.repository.EnfermeraRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * RF-ENF-06: El sistema debera permitir consultar los pacientes
 * asignados a cada enfermera.
 */
@Service
public class AsignacionEnfermeriaService {

    private final AsignacionEnfermeriaRepository asignacionRepository;
    private final EnfermeraRepository enfermeraRepository;

    public AsignacionEnfermeriaService(AsignacionEnfermeriaRepository asignacionRepository,
                                       EnfermeraRepository enfermeraRepository) {
        this.asignacionRepository = asignacionRepository;
        this.enfermeraRepository = enfermeraRepository;
    }

    public List<PacienteAsignadoDTO> obtenerPacientesAsignados(Long enfermeraId, LocalDate fecha) {
        // Validamos que la enfermera exista antes de consultar sus asignaciones
        if (!enfermeraRepository.existsById(enfermeraId)) {
            throw new ResourceNotFoundException("No existe una enfermera con id " + enfermeraId);
        }

        List<AsignacionEnfermeria> asignaciones = asignacionRepository.findPacientesAsignados(enfermeraId, fecha);

        return asignaciones.stream()
                .map(a -> new PacienteAsignadoDTO(
                        a.getId(),
                        a.getPaciente().getId(),
                        a.getPaciente().getNombre(),
                        a.getPaciente().getCama(),
                        a.getPaciente().getDiagnostico(),
                        a.getTurnoEnfermeria().getTurno().name(),
                        a.getFechaAsignacion()
                ))
                .toList();
    }
}