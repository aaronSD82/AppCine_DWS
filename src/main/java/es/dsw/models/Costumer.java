package es.dsw.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;



public class Costumer {
	
	@NotEmpty(message = "El nombre se debe rellenar")
	private String nombre;
	
	private String apellido;
	
	@NotEmpty(message = "El email se debe rellenar")
	@Email(message = "introduza un email válido")
	private String email;
	
	@NotEmpty(message = "Rellene la fecha")
	private String dateString;
	
	@NotEmpty(message = "Escoja una hora")
	private String hourString;
	
	private String repEmailString;
	
	private Pelicula peliculaChosen;
	
	@Min(value = 1, message = "Debe seleccionar al menos un adulto")
	private int numAdultos;
	
	@Min(value = 0)
	private int numNinios;
	
	
	public Costumer() {
		super();
		
	}
	
	

	public int getNumAdultos() {
		return numAdultos;
	}



	public void setNumAdultos(int numAdultos) {
		this.numAdultos = numAdultos;
	}



	public int getNumNinios() {
		return numNinios;
	}



	public void setNumNinios(int numNinios) {
		this.numNinios = numNinios;
	}



	public String getDateString() {
		return dateString;
	}



	public void setDateString(String dateString) {
		this.dateString = dateString;
	}



	public String getHourString() {
		return hourString;
	}



	public void setHourString(String hourString) {
		this.hourString = hourString;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getRepEmailString() {
		return repEmailString;
	}

	public void setRepEmailString(String repEmailString) {
		this.repEmailString = repEmailString;
	}

	public Pelicula getPeliculaChosen() {
		return peliculaChosen;
	}

	public void setPeliculaChosen(Pelicula peliculaChosen) {
		this.peliculaChosen = peliculaChosen;
	}

	
	
}
