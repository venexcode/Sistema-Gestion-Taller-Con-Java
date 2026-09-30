package demo.sistema_gestion_taller.controller;

import demo.sistema_gestion_taller.model.Vehiculo;
import demo.sistema_gestion_taller.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // 1. GET: Obtener todos los vehículos
    @GetMapping
    public ResponseEntity<List<Vehiculo>> obtenerTodos() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerTodos();
        return ResponseEntity.ok(vehiculos); // Devuelve HTTP 200 OK
    }

    // 2. GET: Obtener vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable Long id) {
        return vehiculoService.obtenerPorID(id)
                .map(ResponseEntity::ok) // Si existe, responde 200 OK con el vehículo
                .orElse(ResponseEntity.notFound().build()); // Si no existe, responde 404 Not Found
    }

    // 3. POST: Crear un nuevo vehículo
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo nuevoVehiculo = vehiculoService.guardar(vehiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVehiculo); // Responde 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Responde 400 Bad Request si la patente está duplicada
        }
    }

    // 4. DELETE: Eliminar vehículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id) {
        try {
            vehiculoService.eliminarVehiculoPorID(id);
            return ResponseEntity.noContent().build(); // Responde 244 No Content (eliminado con éxito)
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Responde 404 Not Found
        }
    }
}