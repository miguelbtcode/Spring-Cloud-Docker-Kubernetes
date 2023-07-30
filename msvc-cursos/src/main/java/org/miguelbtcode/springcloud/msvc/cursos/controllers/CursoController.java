package org.miguelbtcode.springcloud.msvc.cursos.controllers;

import jakarta.validation.Valid;
import org.miguelbtcode.springcloud.msvc.cursos.models.entity.Curso;
import org.miguelbtcode.springcloud.msvc.cursos.services.CursoService;
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
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> Listar(){
        return ResponseEntity.ok(cursoService.Listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> Detalle(@PathVariable Long id){
        Optional<Curso> optionalCurso = cursoService.PorId(id);
        if (optionalCurso.isPresent()){
            return ResponseEntity.ok(optionalCurso.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> Crear(@Valid @RequestBody Curso curso, BindingResult result){
        if (result.hasErrors()){
            return getErrors(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.Guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> Editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return getErrors(result);
        }
        Optional<Curso> optionalCurso = cursoService.PorId(id);
        if (optionalCurso.isPresent()){
            Curso cursoDb = optionalCurso.get();
            cursoDb.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.Guardar(cursoDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Eliminar(@PathVariable Long id){
        Optional<Curso> optionalCurso = cursoService.PorId(id);
        if (optionalCurso.isPresent()){
            cursoService.Eliminar(optionalCurso.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> getErrors(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError err : result.getFieldErrors()) {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errores);
    }
}
