package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;

public class Paciente {

	private final String ER_DNI = "[0-9]{8,8}[a-zA-Z]$";
	private final String ER_TELEFONO = "^[9|6][0-9]{8}$";
	private String nombre;
	private String dni;
	private String telefono;

	// Constructor con parámetros
	public Paciente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Paciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		}

		this.nombre = paciente.getNombre();
		this.dni = paciente.getDni();
		this.telefono = paciente.getTelefono();

	}

	// Getters y Setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre == "" || nombre == "  " || nombre == "   ") {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}
		this.nombre = formateaNombre(nombre);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (dni == null || dni == "" || dni == "  " || dni == "   ") {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");

		} else if (dni.matches(ER_DNI)) {
			if (!(comprobarLetraDNI(dni) == false)) {
				this.dni = dni;
			} else {

				throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
			}
		} else {
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}

	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono == "" || telefono == "  " || telefono == "   ") {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");

		} else if (telefono.matches(ER_TELEFONO)) {
			this.telefono = telefono;

		} else {
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
	}

	private String formateaNombre(String nombre) {

		String nuevoNombre;
		// Pasamos todo el nombre a misnuscula
		nuevoNombre = nombre.toLowerCase();
		// Recorremos la cadena y comprobamos si hay puntos,espacios o comas
		// para poner la palabra que le sigue a mayuscula y las demas quedarian en
		// minuscula
		char[] caracteres = nuevoNombre.toCharArray();
		caracteres[0] = Character.toUpperCase(caracteres[0]);

		for (int i = 0; i < nuevoNombre.length() - 2; i++)
			// Es 'palabra'
			if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
				// Reemplazamos
				caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);

		// Para finalizar Pasamos a String caracteres para que así se nos quede guardada
		// la cadena cambiada y utilizamos trim para quitarar los espacios del principio
		// y final y
		// replace que remplaza los espacios en blanco
		// para no dejar espacios.
		nuevoNombre = String.valueOf(caracteres).trim().replace("  ", "");

		return nuevoNombre;
	}

	private boolean comprobarLetraDNI(String dni) {

		// setDni(dni);

		// Separamos los numeros de la letra del DNI
		String numerosDni = dni.substring(0, dni.length() - 1);
		String letraDni = dni.substring(dni.length() - 1);

		// Pasamos a entero la cadena de numeros del dni para poder dividir
		int enteroDni = Integer.parseInt(numerosDni);

		// Realizamos la operacion para saber el numero de letra
		int numero = enteroDni % 23;

		char[] caracteres = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };

		// Pasamos a char letraDni para poder comprarlo con el array de caracteres
		char letraDniCaracter = letraDni.charAt(0);

		// Comprobamos la letra que ha introducido el usuario con la letra que hemos
		// obtenido nosotros
		if (caracteres[numero] == letraDniCaracter) {
			return true;
		} else {

			return false;
		}
	}

	// Métodos hasCode y equals
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Paciente)) {
			return false;
		}
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni);/* && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);*/
	}

	@Override
	public String toString() {
		return "nombre=" + nombre +" (" + getIniciales() + ")" + ", DNI=" + dni + ", teléfono=" + telefono;
	}

	private String getIniciales() {
		
		String inicialesNombre = "";
		// Vamos dividiendo el nombre conforme se encuentra un espacio
		String[] nombreCompleto = this.nombre.split(" ");
		
		// Recorremos el nombre ya dividido
		for (int i = 0; i < nombreCompleto.length ;i++) {
		
			// Si no se encuentra un espacio vacio
			if (!nombreCompleto[i].equals("")) {
				// Se va almacenando la primera posicion de la cadena dividida
				inicialesNombre +=nombreCompleto[i].charAt(0);
			}
		}
		return inicialesNombre;
	}

}
