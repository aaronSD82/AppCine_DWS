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
	
	private String numTarjeta;
	
	private String anioCaducaTarjeta;
	
	private String mesCaducaTarjeta;
	
	private String titularTarjeta;
	
	private String cvvTarjeta;
	
	private String butacas;
	
	
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



	public String getNumTarjeta() {
		return numTarjeta;
	}



	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}



	public String getAnioCaducaTarjeta() {
		return anioCaducaTarjeta;
	}



	public void setAnioCaducaTarjeta(String anioCaducaTarjeta) {
		this.anioCaducaTarjeta = anioCaducaTarjeta;
	}



	public String getMesCaducaTarjeta() {
		return mesCaducaTarjeta;
	}



	public void setMesCaducaTarjeta(String mesCaducaTarjeta) {
		this.mesCaducaTarjeta = mesCaducaTarjeta;
	}



	public String getTitularTarjeta() {
		return titularTarjeta;
	}



	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}



	public String getCvvTarjeta() {
		return cvvTarjeta;
	}



	public void setCvvTarjeta(String cvvTarjeta) {
		this.cvvTarjeta = cvvTarjeta;
	}



	public String getButacas() {
		return butacas;
	}



	public void setButacas(String butacas) {
		this.butacas = butacas;
	}

	
	
}
