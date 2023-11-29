package pe.com.lacunza.service;

import java.util.List;

import pe.com.lacunza.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> listarTodosClientes();
	public void guardar(Cliente cliente);
	public Cliente buscarPorId(Long id);
	public void eliminar(Long id);
}
