package io.github.monthalcantara.repository;

import io.github.monthalcantara.entities.PropostaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<PropostaEntity, Long> {
    Optional<PropostaEntity> findByCpfOuCnpj(String cpfOuCnpj);
}
