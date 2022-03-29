package io.github.monthalcantara.repository;

import io.github.monthalcantara.entities.PropostaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<PropostaEntity, Long> {
}
