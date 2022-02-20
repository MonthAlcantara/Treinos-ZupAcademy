package io.github.monthalcantara.casadocodigo.repository;

import io.github.monthalcantara.casadocodigo.model.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {
}
