package org.miguelbtcode.springcloud.msvc.usuarios.services;

import org.miguelbtcode.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> Listar();
    Optional<Usuario> PorId(Long id);
    Usuario Guardar(Usuario usuario);
    void Eliminar(Long id);
}
