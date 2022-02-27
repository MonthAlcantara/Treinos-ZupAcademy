package io.github.monthalcantara.mercadolivre.processor;

import io.github.monthalcantara.mercadolivre.core.Processor;
import io.github.monthalcantara.mercadolivre.entity.UsuarioEntity;
import io.github.monthalcantara.mercadolivre.model.Usuario;
import io.github.monthalcantara.mercadolivre.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CadastraUsuarioProcessorImpl implements Processor<Usuario> {

    private final UsuarioRepository usuarioRepository;
    private final Logger log = LoggerFactory.getLogger(CadastraUsuarioProcessorImpl.class);

    public CadastraUsuarioProcessorImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario processa(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);

        log.info("Model convertido para objeto de persistencia", usuarioEntity);

        usuarioRepository.save(usuarioEntity);

        log.info("Objeto salvo no banco com sucesso");

        return usuario;
    }
}
