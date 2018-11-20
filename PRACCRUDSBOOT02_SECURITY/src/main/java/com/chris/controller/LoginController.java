package com.chris.controller;


import org.springframework.stereotype.Controller;	
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.chris.model.UserCredential;

@Controller
public class LoginController {

	// Este @GetMapping("/") va a entar directo a la raiz para pedir login
	@GetMapping("/")
	// Este metodo hace que desde la raiz te redirige al login
	public String redirectToLogin() {

		return "redirect:login";
	}
	
	//este @GetMapping va a capturar la peticion de arriba osea el "redirect:login"
	@GetMapping("/login")
	public String showLoginForm(Model model, 
			@RequestParam(name="error", required=false) String error, 
					@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		
		model.addAttribute("logout", logout);
		
		model.addAttribute("userCredentials", new UserCredential());
		
		//nos va a devolver a la vista login.html
		return "login";
	}
	
	//este @PostMapping se va a encargar del formulario login para enviar los datos y
	//autemticarse
	
	//para que construya el link de /logincheck
	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential) {
		
		if(userCredential.getUsername().equals("user") &&
					userCredential.getPassword().equals("user")) {			
			//va a retornar a la vista de listemple.html que lista a los empleados
			//return "listemple";
			
			return "redirect:/empleados/listempleados";
		}		
		//si no por defecto te redireccion a la pagina de login con un parametro de error		
		return "redirect:/login?error";
	}
	
	
}
