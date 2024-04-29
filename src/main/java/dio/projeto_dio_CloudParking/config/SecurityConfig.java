package dio.projeto_dio_CloudParking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita CSRF para simplificar, habilitar conforme necessário
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()  // Permite acesso sem autenticação a este caminho
                        .anyRequest().authenticated()  // Todos os outros pedidos exigem autenticação
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Define a página de login customizada
                        .permitAll()  // Permite a todos acessar a página de login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")  // URL de redirecionamento após logout
                        .permitAll()  // Permite a todos acessar o logout
                )
                .rememberMe(remember -> remember
                        .tokenValiditySeconds(86400)  // Define a validade do token para 24 horas
                );

        return http.build();
    }
}
