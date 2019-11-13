package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {

	static String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	Paciente paciente;
	LocalDateTime fechaHora;

	// Constructor con parámetros
	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		setPaciente(paciente);
		setFechaHora(fechaHora);
	}

	public Cita(Cita cita) {

		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}

		this.paciente = cita.getPaciente();
		this.fechaHora = cita.getFechaHora();
	}

	// Getters y Setters
	public Paciente getPaciente() {
		return paciente;
	}

	private void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}
		this.paciente = new Paciente(paciente);
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		this.fechaHora = fechaHora;
	}

	// COMPROBAR METODO EQUALS
	// Métodos hasCode y equals
	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, paciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cita)) {
			return false;
		}
		Cita other = (Cita) obj;
		
		return Objects.equals(fechaHora, other.fechaHora) && Objects.equals(paciente, other.paciente);
	}

	@Override
	public String toString() {
		return paciente + ", fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA));
	}
	
	

}
