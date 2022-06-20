package dev.rmpedro.app.gestionempleadosbackend.Controlador;


import dev.rmpedro.app.gestionempleadosbackend.excepciones.ResourceNotFoundException;
import dev.rmpedro.app.gestionempleadosbackend.modelo.Empleado;
import dev.rmpedro.app.gestionempleadosbackend.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoRepositorio repositorio;


    @GetMapping("/empleados")
    public List<Empleado> listarTodosEmpleados() {
        return repositorio.findAll();
    }

    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return repositorio.save(empleado);

    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID" + id));
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleadoPorId(@PathVariable Long id,@RequestBody Empleado detallesEmpleado) {
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID" + id));
        empleado.setNombre(detallesEmpleado.getNombre());
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoActualizado = repositorio.save(empleado);

        return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id){
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No existe el empleado con el ID " + id));
        repositorio.deleteById(id);
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);

    }


}
