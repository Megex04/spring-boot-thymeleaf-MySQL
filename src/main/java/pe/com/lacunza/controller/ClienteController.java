package pe.com.lacunza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pe.com.lacunza.models.entity.Ciudad;
import pe.com.lacunza.models.entity.Cliente;
import pe.com.lacunza.service.ICiudadService;
import pe.com.lacunza.service.IClienteService;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ICiudadService ciudadService;
	
	// lista todos lo clientes y envia los datos a frontend
	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoClientes = clienteService.listarTodosClientes();
		
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", listadoClientes);
		return "/views/clientes/listar";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		
		List<Ciudad> listadoCiudades = ciudadService.listaCiudades();
		
		model.addAttribute("titulo", "Formulario: Nuevo cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listadoCiudades);
		
		return "/views/clientes/frmCrear";
	}
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		
		List<Ciudad> listadoCiudades = ciudadService.listaCiudades();
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo cliente");
			model.addAttribute("cliente", cliente);
			model.addAttribute("ciudades", listadoCiudades);
			System.out.println("Existieron errores en el formualario !!");
			return "/views/clientes/frmCrear";
		}
		
		clienteService.guardar(cliente);
		
		System.out.println("CLIENTE GUARDADO CON EXITO !!!");
		attributes.addFlashAttribute("success", "CLIENTE GUARDADO CON EXITO !!!");
		return "redirect:/views/clientes/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") long idCliente, Model model, RedirectAttributes attribute) {
		Cliente cliente = null;
		
		if(idCliente > 0) {
			cliente = clienteService.buscarPorId(idCliente);
			if(cliente == null) {
				System.out.println("Error: El ID del cliente no existe !!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del cliente no existe !!");
				return "redirect:/views/clientes/";
			}
			
		}else {
			System.out.println("Error con el ID del cliente");
			attribute.addFlashAttribute("error", "Error con el ID del cliente");
			return "redirect:/views/clientes/";
		}
		
		
		List<Ciudad> listadoCiudades = ciudadService.listaCiudades();
		
		model.addAttribute("titulo", "Formulario: Editar cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("ciudades", listadoCiudades);
		
		return "/views/clientes/frmCrear";
	} 	
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") long idCliente, RedirectAttributes attribute) {
		Cliente cliente = null;
		
		if(idCliente > 0) {
			cliente = clienteService.buscarPorId(idCliente);
			if(cliente == null) {
				System.out.println("Error: El ID del cliente no existe !!");
				attribute.addFlashAttribute("error", "ATENCION: El ID del cliente no existe !!");
				return "redirect:/views/clientes/";
			}
			
		}else {
			System.out.println("Error con el ID del cliente");
			attribute.addFlashAttribute("error", "Error con el ID del cliente");
			return "redirect:/views/clientes/";
		}
		
		clienteService.eliminar(idCliente);
		System.out.println("Registro eliminado con exito!!!");
		attribute.addFlashAttribute("warning", "Registro eliminado con exito!!!");
		
		return "redirect:/views/clientes/";
	} 	
	
	
}
