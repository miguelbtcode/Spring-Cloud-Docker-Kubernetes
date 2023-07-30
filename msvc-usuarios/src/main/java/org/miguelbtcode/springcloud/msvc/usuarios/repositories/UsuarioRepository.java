package org.miguelbtcode.springcloud.msvc.usuarios.repositories;

import org.miguelbtcode.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
