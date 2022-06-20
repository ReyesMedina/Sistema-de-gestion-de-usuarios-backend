package dev.rmpedro.app.gestionempleadosbackend.repositorio;


import dev.rmpedro.app.gestionempleadosbackend.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado,Long> {
}
