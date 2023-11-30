package pe.com.lacunza.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.lacunza.models.entity.Ciudad;

@Repository
public interface CiudadRepository extends CrudRepository<Ciudad, Integer> {

}
