package io.github.monthalcantara.mercadolivre.processor;

import io.github.monthalcantara.mercadolivre.core.Processor;
import io.github.monthalcantara.mercadolivre.entity.CategoriaEntity;
import io.github.monthalcantara.mercadolivre.model.Categoria;
import io.github.monthalcantara.mercadolivre.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CadastraCategoriaProcessorImpl implements Processor<Categoria> {

    private final CategoriaRepository categoriaRepository;
    private final Logger log = LoggerFactory.getLogger(CadastraCategoriaProcessorImpl.class);

    public CadastraCategoriaProcessorImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria processa(Categoria categoria) {
        var categoriaMaeId = categoria.getCategoriaMae();
        if (categoriaMaeId != null) {

            var categoriaEntity = categoriaRepository
                    .findById(categoriaMaeId)
                    .map(c -> new CategoriaEntity(categoria.getNome(), c))
                    .orElse(new CategoriaEntity(categoria.getNome()));
            categoriaRepository.save(categoriaEntity);
        }else{
            categoriaRepository.save(new CategoriaEntity(categoria.getNome()));
        }
        return categoria;
    }
}
