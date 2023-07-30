package org.miguelbtcode.springcloud.msvc.usuarios.services;

import org.miguelbtcode.springcloud.msvc.usuarios.models.entity.Usuario;
import org.miguelbtcode.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> Listar() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> PorId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario Guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void Eliminar(Long id) {
        repository.deleteById(id);
    }
}
