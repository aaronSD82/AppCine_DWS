package es.dsw.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.dsw.models.Costumer;
import es.dsw.models.Pelicula;
import es.dsw.services.DateService;
import es.dsw.services.ServicePeliculaDAO;
import jakarta.validation.Valid;

@Controller
@SessionAttributes({"cliente"})
public class MainController {
	
	@Autowired
	private DateService myDateService;
	
	@Autowired
	private ServicePeliculaDAO myPeliculaService;
	
	@GetMapping(value = {"/", "/index"})
	public String mappingIndex(Model myModel, SessionStatus status) {
		
		if(myModel.getAttribute("cliente") != null) {status.setComplete();}
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
		
		myPeliculaService.startService(); 
		myModel.addAttribute("peliculas", myPeliculaService);
		
		return "views/step1";
	}
	
	@GetMapping(value = {"/step2"})
	public String mappingStep2(@RequestParam(defaultValue = "0") int sala,
			                   Model myModel) {
		
		Pelicula chosenMovie;
		
		if(sala < 1) {
			return "redirect:/step1"; 
		}
		
		else if(myModel.getAttribute("cliente") == null && sala > 0) {
			
			Costumer cliCostumer = new Costumer();
			cliCostumer.setNumNinios(0);
			cliCostumer.setNumAdultos(1);
			chosenMovie = myPeliculaService.getListaPeliculas().get(sala - 1);
			chosenMovie.setSala(sala);
			cliCostumer.setPeliculaChosen(chosenMovie);
			myModel.addAttribute("cliente", cliCostumer);
		
		}
		
		else {
			Costumer changePelicula = (Costumer) myModel.getAttribute("cliente");
			chosenMovie = myPeliculaService.getListaPeliculas().get(sala - 1);
			chosenMovie.setSala(sala);
			changePelicula.setPeliculaChosen(chosenMovie);
			System.out.println();
		}
		
		return "views/step2"; 
	}
	

	@PostMapping(value = {"/step3"})
	public String mappingStep3(@Valid @ModelAttribute("cliente") Costumer costumer,
								BindingResult bindingResult,
								Model model){
		
		if(bindingResult.hasErrors()) {
			
			return "views/step2";
		}
		
		if(!costumer.getRepEmailString().equalsIgnoreCase(costumer.getEmail())) {
			
			bindingResult.rejectValue("repEmailString", "repEmailString.error", "El email no coincide");
			
			return "views/step2";
		}
		 
		
		return "views/step3";
		
	}
	
	@PostMapping(value = {"/step4"})
	public String mappingStep4(@ModelAttribute("cliente") Costumer costumer, 
								@RequestParam(name="FButacasSelected") String butacas, Model model) {
		
		Double precioTotalNinios = costumer.getNumNinios() * 3.5;
		Double precioTotalAdultos = costumer.getNumAdultos() * costumer.getPeliculaChosen().getPrecio();
		String butacasToShowInView = butacas.replace(';', ',');
		model.addAttribute("precioTotalNinios", precioTotalNinios);
		model.addAttribute("precioTotalAdultos", precioTotalAdultos);
		model.addAttribute("butacas", butacasToShowInView);
		
		return "views/step4";
	}
	
	@GetMapping(value = {"/end"})
	public String mappingEnd() {
		
		
		return "views/end";
	}

}
