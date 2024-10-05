package es.dsw.services;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
		
	}
	
	public void startService() {
		
		loadFilesImages();
		generateListOfPeliculas();
	}
	
	private void loadFilesImages(){
		
		Resource recursoImg = new ClassPathResource("static/img/films/billboard");
		File archivosDeImagen;
		try {
			archivosDeImagen = recursoImg.getFile();
			arrImage = archivosDeImagen.list();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void generateListOfPeliculas(){
		
		listaPeliculas = new HashSet<>();
		int day = serviceDate.getDiaHoy();
		Random imgRandom = new Random();
		imgRandom.setSeed(System.currentTimeMillis());
		
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
	

	public Set<Pelicula> getListaPeliculas() {
		return listaPeliculas;
	}

	

}
