package org.miguelbtcode.springcloud.msvc.cursos.services;

import org.miguelbtcode.springcloud.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> Listar();
    Optional<Curso> PorId(Long id);
    Curso Guardar(Curso curso);
    void Eliminar(Long id);
}
