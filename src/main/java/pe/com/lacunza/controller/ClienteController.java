package pe.com.lacunza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String guardar(@ModelAttribute Cliente cliente) {
		
		clienteService.guardar(cliente);
		
		System.out.println("CLIENTE GUARDADO CON EXITO !!!");
		return "redirect:/views/clientes/";
	}
	
	
		
	
	
}
