package com.chris.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chris.entity.Empleado;
import com.chris.service.EmpleadoService;


@Controller
@RequestMapping("/empleados")
public class EmpleadoController {	

	//vistas
	public static final String EMPLEADOS_VIEW="listemple";
	
	public static final String EMPLEADO_EDIT="editemple";
	
	public static final String EMPLEADO_FORM="empleform";
	
	@Autowired
	@Qualifier("empleadoServiceImpl")
	private EmpleadoService empleadoService;
	
	//aca para que transporte los datos :/ despues lo hacemos de una manera mejor
	private Empleado empleadoEste = new Empleado();
	
	private final Log LOG = LogFactory.getLog(EmpleadoController.class);
	
	//redireccionar a un form para agregar un empleado. aca debe cargar 
	//crear un objeto empleado vacio y ya este creado, mandarlo a la vista con un mav.
	@GetMapping("/empleadoform")
	public ModelAndView redirectForm(@ModelAttribute("empleado") Empleado empleado ) {
		
		
		ModelAndView mav = new ModelAndView(EMPLEADO_FORM);
		
		mav.addObject("empleado", empleado);
		
		return mav;           
	}
	
	@GetMapping("/redirectlist")
	public ModelAndView redirectList() {
		
		return listAllEmpleados();
		
	}	
	
	//listar empleados
	@GetMapping("/listempleados")
	public ModelAndView listAllEmpleados() {
		
		System.out.println("ENTRO METODO listAllEmpleados()");
		
		ModelAndView mav = new ModelAndView(EMPLEADOS_VIEW);
		
		// esto es para crear un objeto vacio para el post para
		// que pueda trabajar el thymeleaf en el formulario.
		
		mav.addObject("empleado", new Empleado());
		
		mav.addObject("empleados", empleadoService.listAllEmpleados());		
		
		return mav;
	}
	
	
	//agregar empleado
	
	@PostMapping("/addempleado")
	public String addEmpleado( Empleado empleado) {	
			
			
			System.out.println("Ingreso ADDEMPLEADO!!");			
			
			empleadoService.addEmpleado(empleado);
			
			return "redirect:/empleados/listempleados";
			
			//ModelAndView mav = new ModelAndView(EMPLEADOS_VIEW);
			
			//mav.addObject("empleado", empleadoService.addEmpleado(empleado));
			
			//return mav;
		
	}
	
	//seleccionar empleado por ID
	
	@RequestMapping("/editarempleado/{id}")
	public ModelAndView getEmpleadoXID(@PathVariable("id") String id) {
		
		System.out.println("ENTRAMOS A EMPLEADO XID");
		
		LOG.info("call: " + "getEditEmpleado()" + " -- Param: ");
		
		ModelAndView mav = new ModelAndView(EMPLEADO_EDIT);
		
		mav.addObject("empleado", empleadoService.getEmpleado(Integer.parseInt(id)));
		
		empleadoEste = empleadoService.getEmpleado(Integer.parseInt(id));
		
		System.out.println("LA VARIABLE EMPLEADOESTE ES: " + empleadoEste);
		
		return mav;
	}
	
	
	//actualizar empleado	
	@PostMapping("/actualizarempleado")
	public String actualizarEmpleado(@ModelAttribute("empleado") Empleado empleado
				, BindingResult bindingResult) {
		
		LOG.info("call: " + "updateEmpleado()" + " -- Param: ");
		
		System.out.println("ENTRAMOS OJALA!! AL METODO ACTUALIZAR EMPLEADO");
		
		//estos datos que estan aca en empleadoEste solo actualizamos
		//el nombre, apellido,fechanac,dni e puesto hour y price el id sigue igual para que
		//actualice el id
		
		
		empleadoEste.setNombre(empleado.getNombre());
		empleadoEste.setApellido(empleado.getApellido());
		empleadoEste.setFechanac(empleado.getFechanac());
		empleadoEste.setDni(empleado.getDni());
		empleadoEste.setPuesto(empleado.getPuesto());
		
		
		 
		System.out.println("el valor del objeto empleadoEste es es: " +  empleadoEste);
		
		if(empleadoEste.getId() == 0) {
			
			System.out.println("no hay nada aca!! " + empleadoEste);
		}else {
			
			System.out.println("ACAAAA cae entonces si hay un dato en el id valores: " + empleadoEste);
			
			empleadoService.updateEmpleado(empleadoEste);
		}
		
		
		
		return "redirect:/empleados/listempleados";
	}
	
	//eliminar empleados
	
	@RequestMapping("/deleteempleado/{id}")
	public String deleteEmpleado(@PathVariable("id") String id) {
		
		System.out.println("Ha caido en deleteEmpleado()");
		
		empleadoEste = empleadoService.getEmpleado(Integer.parseInt(id));
		
		empleadoService.removeEmpleado(empleadoEste.getId());
		
		return "redirect:/empleados/listempleados";
	}
}
