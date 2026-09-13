package com.hospital.enfermeria_backend.service;

import com.hospital.enfermeria_backend.dto.NotaEnfermeriaRequestDTO;
import com.hospital.enfermeria_backend.dto.NotaEnfermeriaResponseDTO;
import com.hospital.enfermeria_backend.entity.Enfermera;
import com.hospital.enfermeria_backend.entity.NotaEnfermeria;
import com.hospital.enfermeria_backend.entity.Paciente;
import com.hospital.enfermeria_backend.exception.ResourceNotFoundException;
import com.hospital.enfermeria_backend.repository.EnfermeraRepository;
import com.hospital.enfermeria_backend.repository.NotaEnfermeriaRepository;
import com.hospital.enfermeria_backend.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * RF-ENF-31: Registrar notas de enfermeria (CRUD completo).
 * RF-ENF-32: Cada nota registra fecha, hora y profesional.
 * RF-ENF-33: Las notas se conservan como parte de la historia clinica.
 */
@Service
public class NotaEnfermeriaService {

    private final NotaEnfermeriaRepository notaRepository;
    private final PacienteRepository pacienteRepository;
    private final EnfermeraRepository enfermeraRepository;

    public NotaEnfermeriaService(NotaEnfermeriaRepository notaRepository,
                                 PacienteRepository pacienteRepository,
                                 EnfermeraRepository enfermeraRepository) {
        this.notaRepository = notaRepository;
        this.pacienteRepository = pacienteRepository;
        this.enfermeraRepository = enfermeraRepository;
    }

    @Transactional
    public NotaEnfermeriaResponseDTO crear(NotaEnfermeriaRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe un paciente con id " + request.getPacienteId()));

        Enfermera enfermera = enfermeraRepository.findById(request.getEnfermeraId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe una enfermera con id " + request.getEnfermeraId()));

        NotaEnfermeria nota = new NotaEnfermeria();
        nota.setPaciente(paciente);
        nota.setEnfermera(enfermera);
        nota.setContenido(request.getContenido());
        nota.setFechaHora(LocalDateTime.now());

        NotaEnfermeria guardada = notaRepository.save(nota);
        return toResponseDTO(guardada);
    }

    @Transactional(readOnly = true)
    public NotaEnfermeriaResponseDTO obtenerPorId(Long id) {
        NotaEnfermeria nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe una nota con id " + id));
        return toResponseDTO(nota);
    }

    @Transactional(readOnly = true)
    public List<NotaEnfermeriaResponseDTO> listarTodas() {
        return notaRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<NotaEnfermeriaResponseDTO> listarPorPaciente(Long pacienteId) {
        if (!pacienteRepository.existsById(pacienteId)) {
            throw new ResourceNotFoundException("No existe un paciente con id " + pacienteId);
        }
        return notaRepository.findByPacienteIdOrderByFechaHoraDesc(pacienteId).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public NotaEnfermeriaResponseDTO actualizar(Long id, NotaEnfermeriaRequestDTO request) {
        NotaEnfermeria nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe una nota con id " + id));

        // Solo se permite editar el contenido; paciente, enfermera y fecha
        // original no deben alterarse, por trazabilidad clinica.
        nota.setContenido(request.getContenido());

        NotaEnfermeria actualizada = notaRepository.save(nota);
        return toResponseDTO(actualizada);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe una nota con id " + id);
        }
        notaRepository.deleteById(id);
    }

    private NotaEnfermeriaResponseDTO toResponseDTO(NotaEnfermeria nota) {
        return new NotaEnfermeriaResponseDTO(
                nota.getId(),
                nota.getPaciente().getId(),
                nota.getPaciente().getNombre(),
                nota.getEnfermera().getId(),
                nota.getEnfermera().getNombre() + " " + nota.getEnfermera().getApellido(),
                nota.getFechaHora(),
                nota.getContenido()
        );
    }
}
