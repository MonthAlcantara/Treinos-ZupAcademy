package io.github.monthalcantara.mercadolivre.repository;

import io.github.monthalcantara.mercadolivre.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByLogin(String username);
}
