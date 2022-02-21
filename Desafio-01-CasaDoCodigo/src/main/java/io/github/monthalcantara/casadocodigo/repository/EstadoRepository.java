package io.github.monthalcantara.casadocodigo.repository;

import io.github.monthalcantara.casadocodigo.model.Estado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EstadoRepository extends CrudRepository<Estado, Long> {
    Optional<Estado> findByNomeAndPaisId(String nome, Long id);
}
