package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.math3.exception.NullArgumentException;

public class Citas {

	private int capacidad;
	private int tamano;

	Cita[] coleccionCitas;

	// Construtor con parametros
	public Citas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}

		this.capacidad = capacidad;
		coleccionCitas = new Cita[capacidad];
		tamano = 0;
	}

	// Getters y Setters
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	public Cita[] getCitas() {
		return coleccionCitas;
	}

	public Cita[] getCitas(LocalDate fecha) {

		if (fecha == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");
		}
		Cita[] citasFecha = new Cita[tamano];
		int citas = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getFechaHora().toLocalDate().equals(fecha)) {
				citasFecha[citas++] = new Cita(coleccionCitas[i]);
			}
		}
		return citasFecha;
	}

	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	public int buscarIndice(Cita cita) {
		int indice = 0;
		boolean citaEncontrada = false;
		while (!tamanoSuperado(indice) && !citaEncontrada) {
			if (coleccionCitas[indice].equals(cita)) {
				citaEncontrada = true;
			} else {
				indice++;
			}

		}
		return indice;
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {

		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		int indice = buscarIndice(cita);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");

		}

		if (tamanoSuperado(indice)) {
			coleccionCitas[indice] = new Cita(cita);
			tamano += 1;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		}

	}

	public Cita buscar(Cita cita) {
		int indice = buscarIndice(cita);
		if (!tamanoSuperado(indice)) {
			return new Cita(coleccionCitas[indice]);
		} else {
			return null;
		}

	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		for (int i = indice; !tamanoSuperado(i); i++) {
			coleccionCitas[i] = coleccionCitas[i + 1];
			
		}
		tamano--;

	}

	public void borrar(Cita cita) throws OperationNotSupportedException {

		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}

		int indice = buscarIndice(cita);

		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
		}
	}

}
