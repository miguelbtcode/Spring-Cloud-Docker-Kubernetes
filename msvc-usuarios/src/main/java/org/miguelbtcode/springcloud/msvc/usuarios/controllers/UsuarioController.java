package org.miguelbtcode.springcloud.msvc.usuarios.controllers;

import jakarta.validation.Valid;
import org.miguelbtcode.springcloud.msvc.usuarios.models.entity.Usuario;
import org.miguelbtcode.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> Listar(){
        return service.Listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Detalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.PorId(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> Crear(@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasErrors()){
            return getErrors(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.Guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> Editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){

        if (result.hasErrors()){
            return getErrors(result);
        }

        Optional<Usuario> usuarioOpt = service.PorId(id);
        if(usuarioOpt.isPresent()){
            Usuario usuarioDb = usuarioOpt.get();
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.Guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable Long id){
        Optional<Usuario> usuarioOpt = service.PorId(id);
        if(usuarioOpt.isPresent()){
            service.Eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> getErrors(BindingResult result) {
        HashMap<String, String> errores = new HashMap<>();
        for (FieldError err : result.getFieldErrors()) {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errores);
    }
}
