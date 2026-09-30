package demo.sistema_gestion_taller.service;

import demo.sistema_gestion_taller.model.Vehiculo;
import demo.sistema_gestion_taller.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    // 1. Obtener los vehiculos
    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    // 2. Obtener un vehiculo por ID
    public Optional<Vehiculo> obtenerPorID(Long id) {
       return vehiculoRepository.findById(id);
    }

    // 3. Guardar un nuevo vehiculo con validacion
    public Vehiculo guardar(Vehiculo vehiculo) {
        // Regla de negocio: Verificar si la patente ya esta registrada
        if (vehiculoRepository.existsByPatente(vehiculo.getPatente())) {
            throw new RuntimeException("Ya existe un vehiculo registrado con la patente: " + vehiculo.getPatente());
        }
        return vehiculoRepository.save(vehiculo);
    }

    // 4. Eliminar vehiculo por ID
    public void eliminarVehiculoPorID(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new RuntimeException("No se encontro vehiculo con ID: " + id);
        }
        vehiculoRepository.deleteById(id);
    }
}
