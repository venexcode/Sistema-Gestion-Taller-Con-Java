package demo.sistema_gestion_taller.repository;

import demo.sistema_gestion_taller.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    // Buscar un vehiculo por su patente
    Optional<Vehiculo> findByPatente(String patente);

    // Comprobar si existe un vehículo con esa patente
    boolean existsByPatente(String patente);
}
