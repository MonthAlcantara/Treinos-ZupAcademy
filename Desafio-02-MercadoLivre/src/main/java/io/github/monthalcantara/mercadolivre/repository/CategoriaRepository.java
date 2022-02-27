package io.github.monthalcantara.mercadolivre.repository;

import io.github.monthalcantara.mercadolivre.entity.CategoriaEntity;
import io.github.monthalcantara.mercadolivre.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<CategoriaEntity, Long> {
    CategoriaEntity findByCategoriaMaeId(Long categoriaMaeId);

    List<CategoriaEntity> findAllByCategoriaMaeId(Long id);
}
