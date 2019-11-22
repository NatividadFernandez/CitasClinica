package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {

	}

	public static void mostrarMenu() {

		System.out.println("*** MENÚ INICIAL ***");
		System.out.println("1.- Insertar citas " + "\n2.- Buscar citas " + "\n3.- Borrar citas "
				+ "\n4.- Mostrar citas " + "\n5.- Mostrar citas de una fecha " + "n\0.- Salir");
	}

	public static Opciones elegirOpcion() {
		int opcion = 0;
		boolean opcionCorrecta = false;
		do {

			System.out.print("Elige una opción del menú: ");
			opcion = Entrada.entero();

			if (opcion >= 0 && opcion <= Opciones.values().length - 1) {
				opcionCorrecta = true;
			}

		} while (!opcionCorrecta);

		return Opciones.values()[opcion];
	}

	public static Paciente leerPaciente() {
		String dni, nombre, telefono;
		System.out.println("** DATOS PACIENTE **");
		System.out.print("Introduce el dni del paciente: ");
		dni = Entrada.cadena();
		System.out.print("Introduce el nombre del paciente: ");
		nombre = Entrada.cadena();
		System.out.print("Introduce el teléfono del paciente: ");
		telefono = Entrada.cadena();

		return new Paciente(nombre, dni, telefono);
	}

	public static LocalDateTime leerFechaHora() {
		LocalDateTime localDateTime;
		int dia, mes, annio, hora, min;

		System.out.println("** FECHA **");
		do {
			System.out.println("Introduce el año");
			annio = Entrada.entero();
		} while (annio < 2019);

		do {
			System.out.println("Introduce el mes");
			mes = Entrada.entero();
		} while (mes < 1 || mes > 12);

		do {
			System.out.println("Introduce el dia");
			dia = Entrada.entero();
		} while (dia < 1 || dia > 31);

		System.out.println("** HORA **");
		do {
			System.out.println("Introduce la hora");
			hora = Entrada.entero();
		} while ((hora < 0 || hora > 23));

		do {
			System.out.println("Introduce los minutos");
			min = Entrada.entero();
		} while (min < 0 || min > 59);

		return localDateTime = LocalDateTime.of(annio, mes, dia, hora, min);
	}

	public static Cita leerCita() {
		return new Cita(leerPaciente(), leerFechaHora());
	}

	public static LocalDate leerFecha() {
		LocalDate localDate;
		int dia, mes, annio;

		System.out.println("** FECHA **");
		do {
			System.out.println("Introduce el año");
			annio = Entrada.entero();
		} while (annio < 2019);

		do {
			System.out.println("Introduce el mes");
			mes = Entrada.entero();
		} while (mes < 1 || mes > 12);

		do {
			System.out.println("Introduce el dia");
			dia = Entrada.entero();
		} while (dia < 1 || dia > 31);

		return localDate = LocalDate.of(annio, mes, dia);
	}

}
