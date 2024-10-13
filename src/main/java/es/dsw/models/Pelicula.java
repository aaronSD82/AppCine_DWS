package es.dsw.models;

import java.util.Objects;


public class Pelicula {
	
	private String productora;
	private String genero;
	private String pais;
	private String nombreImagenString;
	private int sala;
	

	public Pelicula(String productora, String genero, String pais, String nombreImagen) {
		super();
		this.productora = productora;
		this.genero = genero;
		this.pais = pais;
		this.nombreImagenString = nombreImagen;
	}

	public Pelicula(String nombreImagenString) {
		super();
		this.nombreImagenString = nombreImagenString;
	}

	public String getProductora() {
		return productora;
	}

	public String getGenero() {
		return genero;
	}

	public String getPais() {
		return pais;
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
