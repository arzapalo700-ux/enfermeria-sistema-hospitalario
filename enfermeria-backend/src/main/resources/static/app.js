const API_BASE = "/api";
document.getElementById("apiBaseLabel").textContent = window.location.origin + API_BASE;

document.querySelectorAll(".tab-btn").forEach(btn => {
    btn.addEventListener("click", () => {
        document.querySelectorAll(".tab-btn").forEach(b => b.classList.remove("active"));
        document.querySelectorAll(".tab-panel").forEach(p => p.classList.remove("active"));
        btn.classList.add("active");
        document.getElementById("tab-" + btn.dataset.tab).classList.add("active");
    });
});

async function checkServer() {
    const dot = document.getElementById("statusDot");
    const text = document.getElementById("statusText");
    try {
        const res = await fetch(`${API_BASE}/notas-enfermeria`);
        if (res.ok) { dot.className = "dot ok"; text.textContent = "Backend conectado"; }
        else throw new Error();
    } catch {
        dot.className = "dot err";
        text.textContent = "No se pudo conectar al backend";
    }
}
checkServer();

const formPacientes = document.getElementById("formPacientes");
const pacientesGrid = document.getElementById("pacientesGrid");
const pacientesMsg = document.getElementById("pacientesMsg");

formPacientes.addEventListener("submit", async (e) => {
    e.preventDefault();
    const enfermeraId = document.getElementById("enfermeraId").value;
    pacientesMsg.textContent = "Buscando…";
    pacientesMsg.className = "msg";
    pacientesGrid.innerHTML = "";
    try {
        const res = await fetch(`${API_BASE}/enfermeras/${enfermeraId}/pacientes`);
        if (!res.ok) throw new Error(`Error ${res.status}`);
        const pacientes = await res.json();
        if (pacientes.length === 0) {
            pacientesMsg.textContent = "";
            pacientesGrid.innerHTML = `<div class="empty-state">Esta enfermera no tiene pacientes asignados.</div>`;
            return;
        }
        pacientesMsg.textContent = `${pacientes.length} paciente(s) encontrado(s).`;
        pacientesMsg.className = "msg success";
        pacientesGrid.innerHTML = pacientes.map(p => `
      <div class="patient-card">
        <h3>${p.nombrePaciente ?? "Paciente"}</h3>
        <dl>
          <div><dt>Cama</dt><span>${p.cama ?? "—"}</span></div>
          <div><dt>Diagnóstico</dt><span>${p.diagnostico ?? "—"}</span></div>
          <div><dt>Asignado</dt><span>${formatFecha(p.fechaAsignacion)}</span></div>
        </dl>
        ${p.turno ? `<span class="badge-turno">${p.turno}</span>` : ""}
      </div>
    `).join("");
    } catch (err) {
        pacientesMsg.textContent = "No se pudo obtener la lista de pacientes.";
        pacientesMsg.className = "msg error";
    }
});

const formNota = document.getElementById("formNota");
const notasList = document.getElementById("notasList");
const notasMsg = document.getElementById("notasMsg");
const btnCancelarEdicion = document.getElementById("btnCancelarEdicion");
const btnGuardarNota = document.getElementById("btnGuardarNota");

async function cargarNotas() {
    notasList.innerHTML = `<div class="empty-state">Cargando notas…</div>`;
    try {
        const res = await fetch(`${API_BASE}/notas-enfermeria`);
        if (!res.ok) throw new Error(`Error ${res.status}`);
        const notas = await res.json();
        if (notas.length === 0) {
            notasList.innerHTML = `<div class="empty-state">Todavía no hay notas registradas.</div>`;
            return;
        }
        notasList.innerHTML = notas.map(n => `
      <div class="note-card" data-id="${n.id}">
        <div class="note-card-top">
          <strong>${n.nombrePaciente ?? "Paciente #" + n.pacienteId}</strong>
          <time>${formatFecha(n.fechaHora)}</time>
        </div>
        <p class="note-content">${n.contenido}</p>
        <div class="note-actions">
          <span style="color:var(--ink-soft)">Registrada por ${n.nombreEnfermera ?? "Enfermera #" + n.enfermeraId}</span>
          <button class="edit" data-action="edit">Editar</button>
          <button class="delete" data-action="delete">Eliminar</button>
        </div>
      </div>
    `).join("");
    } catch (err) {
        notasList.innerHTML = `<div class="empty-state">No se pudieron cargar las notas.</div>`;
    }
}
cargarNotas();

document.getElementById("btnRefrescarNotas").addEventListener("click", cargarNotas);

formNota.addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("notaId").value;
    const pacienteId = document.getElementById("pacienteId").value;
    const enfermeraId = document.getElementById("notaEnfermeraId").value;
    const contenido = document.getElementById("contenido").value;
    const payload = { pacienteId: Number(pacienteId), enfermeraId: Number(enfermeraId), contenido };
    const esEdicion = Boolean(id);
    try {
        const res = await fetch(
            esEdicion ? `${API_BASE}/notas-enfermeria/${id}` : `${API_BASE}/notas-enfermeria`,
            {
                method: esEdicion ? "PUT" : "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            }
        );
        if (!res.ok) throw new Error(`Error ${res.status}`);
        notasMsg.textContent = esEdicion ? "Nota actualizada correctamente." : "Nota registrada correctamente.";
        notasMsg.className = "msg success";
        resetFormNota();
        cargarNotas();
    } catch (err) {
        notasMsg.textContent = "No se pudo guardar la nota. Revisa los datos e intenta de nuevo.";
        notasMsg.className = "msg error";
    }
});

btnCancelarEdicion.addEventListener("click", resetFormNota);

notasList.addEventListener("click", async (e) => {
    const btn = e.target.closest("button[data-action]");
    if (!btn) return;
    const card = btn.closest(".note-card");
    const id = card.dataset.id;

    if (btn.dataset.action === "delete") {
        if (!confirm("¿Eliminar esta nota? Esta acción no se puede deshacer.")) return;
        try {
            const res = await fetch(`${API_BASE}/notas-enfermeria/${id}`, { method: "DELETE" });
            if (!res.ok && res.status !== 204) throw new Error(`Error ${res.status}`);
            notasMsg.textContent = "Nota eliminada correctamente.";
            notasMsg.className = "msg success";
            cargarNotas();
        } catch {
            notasMsg.textContent = "No se pudo eliminar la nota.";
            notasMsg.className = "msg error";
        }
    }

    if (btn.dataset.action === "edit") {
        try {
            const res = await fetch(`${API_BASE}/notas-enfermeria`);
            const notas = await res.json();
            const nota = notas.find(n => String(n.id) === id);
            if (!nota) return;
            document.getElementById("notaId").value = nota.id;
            document.getElementById("pacienteId").value = nota.pacienteId;
            document.getElementById("notaEnfermeraId").value = nota.enfermeraId;
            document.getElementById("contenido").value = nota.contenido;
            btnGuardarNota.textContent = "Guardar cambios";
            btnCancelarEdicion.hidden = false;
            window.scrollTo({ top: 0, behavior: "smooth" });
        } catch {
            notasMsg.textContent = "No se pudo cargar la nota para editar.";
            notasMsg.className = "msg error";
        }
    }
});

function resetFormNota() {
    formNota.reset();
    document.getElementById("notaId").value = "";
    btnGuardarNota.textContent = "Registrar nota";
    btnCancelarEdicion.hidden = true;
}

function formatFecha(iso) {
    if (!iso) return "—";
    const d = new Date(iso);
    if (isNaN(d)) return iso;
    return d.toLocaleString("es-PE", { day: "2-digit", month: "2-digit", year: "numeric", hour: "2-digit", minute: "2-digit" });
}