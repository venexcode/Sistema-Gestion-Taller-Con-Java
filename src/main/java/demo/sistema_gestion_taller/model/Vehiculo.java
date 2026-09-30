package demo.sistema_gestion_taller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data                   // Genera automáticamente Getters, Setters, toString, equals y hashCode
@Builder                // Permite usar el patrón Builder para instanciar objetos fácilmente
@NoArgsConstructor      // Genera el constructor vacío (Obligatorio para JPA)
@AllArgsConstructor     // Genera un constructor con todos los campos
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String patente;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    private int anio;

    private String color;
}
