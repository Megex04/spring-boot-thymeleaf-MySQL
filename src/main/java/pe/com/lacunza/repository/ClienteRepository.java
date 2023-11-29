package pe.com.lacunza.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.lacunza.models.entity.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	
}
