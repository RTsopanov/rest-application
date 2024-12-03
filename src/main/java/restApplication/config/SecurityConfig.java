package restApplication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) {
        String indexUrl = "/swagger-ui/index.html";

        try {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(autorize -> autorize
                            .requestMatchers("/**").hasRole("READER")
                            .requestMatchers(HttpMethod.DELETE, "/v2/employees").hasRole("WRITER")
                            .requestMatchers(HttpMethod.POST, "/v2/employees").hasRole("WRITER")
                    )
                    .formLogin(from -> from
                            .defaultSuccessUrl(indexUrl, true));
            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails userR = User.withDefaultPasswordEncoder()
                .username("reader")
                .password("123")
                .roles("READER")
                .build();

        UserDetails userW = User.withDefaultPasswordEncoder()
                .username("writer")
                .password("456")
                .roles("WRITER", "READER")
                .build();

        return new InMemoryUserDetailsManager(userR, userW);
    }
}
