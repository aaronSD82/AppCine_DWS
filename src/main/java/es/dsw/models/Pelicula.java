package es.dsw.models;


public class Pelicula {
	
	private String productora;
	private String genero;
	private String pais;
	private String nombreImagenString;
	
	public Pelicula(String productora, String genero, String pais, String nombreImagen) {
		super();
		this.productora = productora;
		this.genero = genero;
		this.pais = pais;
		this.nombreImagenString = nombreImagen;
	}
	
	
}
