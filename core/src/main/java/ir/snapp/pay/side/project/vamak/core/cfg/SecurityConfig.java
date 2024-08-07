package ir.snapp.pay.side.project.vamak.core.cfg;

import ir.snapp.pay.side.project.vamak.core.repo.MemberRepository;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer bypassH2ConsoleFromSecurityCheck() {
        return web -> web.ignoring().requestMatchers(toH2Console());
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        ar -> ar.anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .build();
    }

    @Bean
    public PasswordEncoder defaultPasswordEncoder() {
        var passwordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // for handling not prefixed password
        passwordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
        return passwordEncoder;
    }

    @Bean
    public UserCache defaultUserDetailsCache(CacheManager manager) {
        return new SpringCacheBasedUserCache(manager.getCache("user-details"));
    }

    @Bean
    public UserDetailsService defaultUserDetailsService(MemberRepository repository, UserCache cache) {
        var userDetailsService = new CachingUserDetailsService(
                username -> repository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username))
        );
        userDetailsService.setUserCache(cache);
        return userDetailsService;
    }
}
