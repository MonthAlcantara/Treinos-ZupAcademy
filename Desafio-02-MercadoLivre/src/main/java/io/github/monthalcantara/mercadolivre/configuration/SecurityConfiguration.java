package io.github.monthalcantara.mercadolivre.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     *  Esse método que recebe um AuthenticationManagerBuilder serve para configurar a parte de autenticação
     * Controle de acesso, login e etc
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Qual a classe de Serviço que tem a lógica que faz a authenticação?
        auth
                .userDetailsService(autenticacaoService)
                //Qual foi o algoritimo de Hash usado para salvar a senha no banco?
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Quais requests serão autorizadas e como será a autorização
                .authorizeRequests()
                /*Quais Urls eu vou filtrar? É pra permitir ou bloquear?
                O antMatchers tem 3 sobrecargas
                 1 - Passando só a url,
                 2 - Passando só p método (Get, Post...etc)
                 3 - Passando método + url
                 Nesse caso liberei o /topicos o /topicos/ com barra no final é barrado
                */
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                                //Qualquer outra requisção fora as que eu citei acima
                .anyRequest()
                //Só aceite requisição de pessoas authenticadas
                .authenticated()
                // e
                .and()
                /*E utilize o formulario de Login padrão do spring pra fazer a autenticação
                 Nesse caso além de fornecer a tela de login, o spring também fornece um controller
                 que ele mesmo acessa e faz toda a magica de receber o login e senha e chamar os métodos
                e service para realizar a autenticação
                */
                // .formLogin();

                /*
                 * CSRF é um tipo de ataque a aplicações web. Porém como a authenticação será feita por token
                 * a proteção a esse ataque é dispensável, então para evitar que o spring gaste processamento
                 * com essa proteção sem sentido, nesse caso, posso desabilitar
                 * */
                .csrf().disable()
                /*
                 * com o método abaixo eu digo que não quero que a autenticação fique salva na sessão
                 * eu informo que a politica de criação de sessão é stateless como rege as boas práticas
                 * do desenvolvimento de api's
                 * */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                /*Nesse caso eu não vou mais ter a tela padrão do Spring com o formLogin
                  e também perco o controller default onde o spring fazia toda a mágica de receber
                  o login e senha do formLogin. Nesse caso agora eu é que tenho que criar um controller
                  para receber esses dados e gerar o token e um filtro para interceptar a requisição
                  pegar o token e validar
                 */
                .and()
                /*
                Só que o Spring tem um filtro próprio. Se eu quiser usar o meu eu preciso dizer que o
                meu fitro deve ser usado antes mesmo do filtro do Spring
                */
                .addFilterBefore(new AutenticacaoViaTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /*
     * Esse método que recebe um WebSecurity serve para configurar a parte de autorização
     * urls, permissões de acesso, perfis de acesso e etc
     * Serve para configurar o acesso a conteúdos estáticos como CSS, JS, Imagens
     * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html",
                "/v2/api-docs",
                "/webjars/**",
                "/configuration/**",
                "/h2-console/**",
                "/swagger-resources/**");
    }

}
