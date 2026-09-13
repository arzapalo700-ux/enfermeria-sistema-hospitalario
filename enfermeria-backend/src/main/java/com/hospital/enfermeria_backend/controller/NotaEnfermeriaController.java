package com.hospital.enfermeria_backend.controller;

import com.hospital.enfermeria_backend.dto.NotaEnfermeriaRequestDTO;
import com.hospital.enfermeria_backend.dto.NotaEnfermeriaResponseDTO;
import com.hospital.enfermeria_backend.service.NotaEnfermeriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas-enfermeria")
public class NotaEnfermeriaController {

    private final NotaEnfermeriaService notaService;

    public NotaEnfermeriaController(NotaEnfermeriaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public ResponseEntity<NotaEnfermeriaResponseDTO> crear(@Valid @RequestBody NotaEnfermeriaRequestDTO request) {
        NotaEnfermeriaResponseDTO creada = notaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @GetMapping
    public List<NotaEnfermeriaResponseDTO> listarTodas() {
        return notaService.listarTodas();
    }

    @GetMapping("/{id}")
    public NotaEnfermeriaResponseDTO obtenerPorId(@PathVariable Long id) {
        return notaService.obtenerPorId(id);
    }

    @GetMapping("/paciente/{pacienteId}")
    public List<NotaEnfermeriaResponseDTO> listarPorPaciente(@PathVariable Long pacienteId) {
        return notaService.listarPorPaciente(pacienteId);
    }

    @PutMapping("/{id}")
    public NotaEnfermeriaResponseDTO actualizar(@PathVariable Long id,
                                                @Valid @RequestBody NotaEnfermeriaRequestDTO request) {
        return notaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
