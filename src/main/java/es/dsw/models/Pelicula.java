package es.dsw.models;

import java.util.Objects;



public class Pelicula {
	
	private String titulo;
	private String nombreImagenString;
	private int sala;
	private int id;
	private double precio;
	private int sesion;

	public Pelicula(String titulo, String nombreImagen, int sala, double precio) {
		super();
		this.titulo = titulo;
		this.nombreImagenString = nombreImagen;
		this.sala = sala;
		this.precio = precio;
	}
	
	public Pelicula() {
		super();
	}

	public String getNombreImagenString() {
		return nombreImagenString;
	}
	
	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setNombreImagenString(String nombreImagenString) {
		this.nombreImagenString = nombreImagenString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public int getSesion() {
		return sesion;
	}

	public void setSesion(int sesion) {
		this.sesion = sesion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreImagenString);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return Objects.equals(nombreImagenString, other.nombreImagenString);
	}
	
	
	
}
