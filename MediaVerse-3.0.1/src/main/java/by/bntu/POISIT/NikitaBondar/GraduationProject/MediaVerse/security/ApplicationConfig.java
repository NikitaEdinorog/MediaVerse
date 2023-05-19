package by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.security;

import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.Password;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.entities.User;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.managers.UserManager;
import by.bntu.POISIT.NikitaBondar.GraduationProject.MediaVerse.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final static Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private UserManager userManager;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public User loadUserByUsername(String username) throws UsernameNotFoundException {

                logger.debug("load");

                Optional<User> userOptional = userManager.queryUserByUsername(username);
                if (userOptional.isEmpty()) {
                    throw new UsernameNotFoundException("User not found");
                } else {
                    User user = userOptional.get();
                    Optional<Password> password = userManager.queryPassword(user.getId());
                    user.setPassword(password.get().getPassword());
                    return user;
                }
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}