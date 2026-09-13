-- Enfermeras
INSERT INTO enfermera (nombre, apellido, email, servicio) VALUES ('Ana', 'Torres', 'ana.torres@hospital.com', 'Medicina Interna');
INSERT INTO enfermera (nombre, apellido, email, servicio) VALUES ('Luis', 'Ramirez', 'luis.ramirez@hospital.com', 'Emergencia');

-- Pacientes (version simplificada de este modulo)
INSERT INTO paciente (nombre, cama, diagnostico) VALUES ('Juan Perez', '201-A', 'Neumonia');
INSERT INTO paciente (nombre, cama, diagnostico) VALUES ('Maria Lopez', '201-B', 'Infeccion');
INSERT INTO paciente (nombre, cama, diagnostico) VALUES ('Pedro Diaz', '202-A', 'Fractura');
INSERT INTO paciente (nombre, cama, diagnostico) VALUES ('Rosa Fernandez', '203-B', 'Deshidratacion');

-- Turno de Ana Torres el 2026-09-06 en la manana
INSERT INTO turno_enfermeria (enfermera_id, fecha, turno, servicio) VALUES (1, '2026-09-06', 'MANANA', 'Medicina Interna');

-- Asignaciones de pacientes a Ana Torres para ese turno (RF-ENF-06)
INSERT INTO asignacion_enfermeria (turno_enfermeria_id, paciente_id, fecha_asignacion, activo) VALUES (1, 1, '2026-09-06 07:00:00', TRUE);
INSERT INTO asignacion_enfermeria (turno_enfermeria_id, paciente_id, fecha_asignacion, activo) VALUES (1, 2, '2026-09-06 07:00:00', TRUE);
INSERT INTO asignacion_enfermeria (turno_enfermeria_id, paciente_id, fecha_asignacion, activo) VALUES (1, 3, '2026-09-06 07:00:00', TRUE);
INSERT INTO asignacion_enfermeria (turno_enfermeria_id, paciente_id, fecha_asignacion, activo) VALUES (1, 4, '2026-09-06 07:00:00', TRUE);

-- Una nota de enfermeria de ejemplo (RF-ENF-31)
INSERT INTO nota_enfermeria (paciente_id, enfermera_id, fecha_hora, contenido) VALUES
    (1, 1, '2026-09-06 14:30:00', 'Paciente consciente y orientado. Se realiza control de signos vitales. Tolera alimentacion via oral.');