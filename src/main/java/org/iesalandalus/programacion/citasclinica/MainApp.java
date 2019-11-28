package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {

	public static final int NUM_MAX_CITAS = 5;
	private static Citas citasClinica = new Citas(NUM_MAX_CITAS);

	public static void main(String[] args) {
		System.out.println("Programa para gestionar las citas de la Clínica.");
		Opciones opcion;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			System.out.println(" ");
			ejecutarOpcion(opcion);
		} while (opcion != Opciones.SALIR);
	}

	private static void insertarCita() {
		try {
			Cita cita = Consola.leerCita();
			citasClinica.insertar(cita);
			System.out.println("La cita ha sido insertada correctamente");

		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException oin) {
			System.out.println(oin.getMessage());
		}

	}

	private static void buscarCita() {

		LocalDateTime fecha = Consola.leerFechaHora();
		Paciente paciente = new Paciente("Nombre", "77554223V", "654789321");
		Cita cita = new Cita(paciente, fecha);
		try {

			if ((cita = citasClinica.buscar(cita)) == null) {
				System.out.println("No hay citas para esa fecha.");
			} else {
				System.out.println(cita);
			}

		} catch (NullPointerException | IllegalArgumentException ni) {
			System.out.println(ni.getMessage());
		}
	}

	private static void borrarCita() {
		LocalDateTime fecha = Consola.leerFechaHora();
		Paciente paciente = new Paciente("Nombre", "77554223V", "654789321");
		Cita cita = new Cita(paciente, fecha);
		try {
			citasClinica.borrar(cita);
			System.out.println("La cita ha sido borrada correctamente");
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException nio) {
			System.out.println(nio.getMessage());
		}
	}

	private static void mostrarCitas() {

		boolean hayCitas = false;

		try {

			for (int i = 0; i < citasClinica.getTamano(); i++) {

				System.out.println(citasClinica.getCitas()[i]);
				hayCitas = true;
			}

			if (hayCitas == false) {
				System.out.println("No hay citas para mostrar");
			}

		} catch (NullPointerException ni) {
			System.out.println(ni.getMessage());
		}

	}

	private static void mostrarCitasDia() {

		LocalDate fecha = Consola.leerFecha();
		try {

			boolean hayCitas = false;

			for (int i = 0; i < citasClinica.getTamano(); i++) {
				if (citasClinica.getCitas()[i].getFechaHora().toLocalDate().equals(fecha)) {
					System.out.println(citasClinica.getCitas()[i]);
					hayCitas = true;
				}
			}

			if (hayCitas == false) {
				System.out.println("No hay citas para mostrar ese día.");
			}

		} catch (NullPointerException ni) {
			System.out.println(ni.getMessage());
		}
	}

	private static void ejecutarOpcion(Opciones opcion) {

		switch (opcion) {

		case INSERTAR_CITA:
			System.out.println("**** INSERTAR CITA ****");
			insertarCita();
			System.out.println(" ");
			break;
		case BUSCAR_CITA:
			System.out.println("**** BUSCAR CITA ****");
			buscarCita();
			System.out.println(" ");
			break;
		case BORRAR_CITA:
			System.out.println("**** BORRAR CITA ****");
			borrarCita();
			System.out.println(" ");
			break;
		case MOSTRAR_CITAS:
			System.out.println("**** MOSTRAR CITA ****");
			mostrarCitas();
			System.out.println(" ");
			break;
		case MOSTRAR_CITAS_DIA:
			System.out.println("**** MOSTRAR CITA DIA ****");
			mostrarCitasDia();
			System.out.println(" ");
			break;

		case SALIR:
			break;

		}

	}

}
