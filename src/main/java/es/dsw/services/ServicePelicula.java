package es.dsw.services;


import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import es.dsw.models.Pelicula;

@Service
public class ServicePelicula {
	
	
	private String[] arrImage;
	private Set<Pelicula> listaPeliculas;
	private DateService serviceDate;
	
	public ServicePelicula(DateService serviceDate) {
		super();
		this.serviceDate = serviceDate;
		loadFilesImages();
		generateListOfPeliculas();
	}
	
	private void loadFilesImages(){
		
		File archivosDeImagen = new File("src/main/resources/static/img/films/billboard");
		arrImage = archivosDeImagen.list();
	}
	
	private void generateListOfPeliculas(){
		
		listaPeliculas = new HashSet<>();
		int day = serviceDate.getDiaHoy();
		Random imgRandom = new Random();
		
		if(day == 1 || day == 2 || day == 4){
			
			while(listaPeliculas.size() < 4) {
				 
				listaPeliculas.add(new Pelicula(arrImage[imgRandom.nextInt(0, 13)]));
				
			}
			
		} 
		else { 
			
			while(listaPeliculas.size() < 7) {
				
				listaPeliculas.add(new Pelicula(arrImage[imgRandom.nextInt(0, 13)]));
			}
			
		}
		
	}
	
	public void imprime() {
		
		listaPeliculas.stream().forEach(pel -> System.out.println(pel.getNombreImagenString()));
		
	}
	

}
