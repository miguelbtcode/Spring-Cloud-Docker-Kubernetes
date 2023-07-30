package org.miguelbtcode.springcloud.msvc.cursos.services;

import org.miguelbtcode.springcloud.msvc.cursos.models.entity.Curso;
import org.miguelbtcode.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> Listar() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> PorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso Guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void Eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}
