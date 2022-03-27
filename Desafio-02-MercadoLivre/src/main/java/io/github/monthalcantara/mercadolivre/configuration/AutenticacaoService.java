package io.github.monthalcantara.mercadolivre.configuration;

import io.github.monthalcantara.mercadolivre.entity.UsuarioEntity;
import io.github.monthalcantara.mercadolivre.model.Usuario;
import io.github.monthalcantara.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * O auth.userDetailsService espera um UserDetailsService e não um AutenticacaoService
 * Para dizer ao Spring que essa é a classe que possui a lógica de autenticacao eu preciso
 * implementar UserDetailsService
 * */
@Service
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository repository;

    public AutenticacaoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    /*
     * La na tela do login (A do Spring ou outra qqr) quan eu digitar a senha e o login e dar enter,
     * O Spring vai vim nessa classe e vai buscar esse método loadUserByUsername, foi isso que eu
     * defini no configure(AuthenticationManagerBuilder auth)
     * o login usado na tela é o parametro recebido pelo método
     * A senha é checada em memória pelo Spring
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuario = repository.findByLogin(username);
        if (usuario.isPresent()) {
            return new Usuario(usuario.get());

        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
