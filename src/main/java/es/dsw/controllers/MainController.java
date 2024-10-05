package es.dsw.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import es.dsw.services.DateService;
import es.dsw.services.ServicePelicula;

@Controller
public class MainController {
	
	@Autowired
	private DateService myDateService;
	
	@Autowired
	private ServicePelicula myPeliculaService;
	
	@GetMapping(value = {"/", "/index"})
	public String mappingIndex(Model myModel) {
		
		myDateService.startAndRefresh();
		
		String precio = myDateService.getDiaHoy() == 4 ? "Desde 3.5 " : "Desde 6 ";
		
		myModel.addAttribute("hora", myDateService.getHora());
		myModel.addAttribute("mensajeBienvenida", myDateService.getMensajeBienvenidaString());
		myModel.addAttribute("fecha", myDateService.getFechaHoy());
		myModel.addAttribute("precio", precio);
		
		return "index";
	}
	
	@GetMapping(value = {"/step1"})
	public String mappingStep1(Model myModel) {
		
		String precio = myDateService.getDiaHoy() == 4 ? "3.5" : "6";
		
		myPeliculaService.startService(); 
		myModel.addAttribute("peliculas", myPeliculaService);
		myModel.addAttribute("precio", precio);
		return "views/step1";
	}
	
	@GetMapping(value = {"/step2"})
	public String mappingStep2() {
		
		return "views/step2";
	}
	
	@GetMapping(value = {"/step3"})
	public String mappingStep3() {
		
		return "views/step3";
	}
	
	@GetMapping(value = {"/step4"})
	public String mappingStep4() {
		
		return "views/step4";
	}
	
	@GetMapping(value = {"/end"})
	public String mappingEnd() {
		
		return "views/end";
	}

}
