package es.dsw.services;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.dsw.models.Pelicula;

@Service
public class ServicePelicula {
	
	
	private String[] arrImage;
	private List<Pelicula> listaPeliculas;
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
			
			e.printStackTrace();
		}
	}
	
	private void generateListOfPeliculas(){
		
		listaPeliculas = new ArrayList<>();
		int day = serviceDate.getDiaHoy();
		for(String img:arrImage) {
			
			listaPeliculas.add(new Pelicula(img));
			
		}
		
		Collections.shuffle(listaPeliculas);
		
		if(day == 1 || day == 2 || day == 4){
			
			listaPeliculas = listaPeliculas.subList(0, 4);
			
		} 
		else { 
			
			listaPeliculas = listaPeliculas.subList(0, 7);
		}
		
	}
	

	public List<Pelicula> getListaPeliculas() {
		return listaPeliculas; 
	}

	

}
