package pe.com.lacunza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.lacunza.models.entity.Ciudad;
import pe.com.lacunza.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements ICiudadService {

	@Autowired
	private CiudadRepository ciudadRepository;
	
	@Override
	public List<Ciudad> listaCiudades() {
		return (List<Ciudad>) ciudadRepository.findAll();
	}

}
