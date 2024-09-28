package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping(value = {"/", "/index"})
	public String mappingIndex() {
		
		return "index";
	}
	
	@GetMapping(value = {"/step1"})
	public String mappingStep1() {
		
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
