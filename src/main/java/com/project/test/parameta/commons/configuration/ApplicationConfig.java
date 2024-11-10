package com.project.test.parameta.commons.configuration;

import com.project.test.parameta.commons.dto.EmpleadoSeguridadDTO;
import com.project.test.parameta.commons.entity.EmpleadoEntity;
import com.project.test.parameta.commons.repository.EmpleadoRepository;
import com.project.test.parameta.commons.util.mappers.EmpleadoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.project.test.parameta.commons.util.constants.Constantes.ERROR_USUARIO_NO_ENCONTRADO;
import static com.project.test.parameta.commons.util.constants.Constantes.ORIGEN_DESARROLLO;

/**
 * Configuración principal para la aplicación.
 * <p>
 * Proporciona beans y configuraciones necesarias para la autenticación,
 * codificación de contraseñas, servicio de usuarios y configuración de CORS.
 * </p>
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /**
     * Repositorio para acceder a los datos de empleados.
     */
    private final EmpleadoRepository empleadoRepository;

    /**
     * Mapper para convertir entre entidades de empleados y DTOs.
     */
    private final EmpleadoMapper empleadoMapper;

    /**
     * Configura y devuelve el bean de {@link AuthenticationManager}.
     *
     * @param config la configuración de autenticación.
     * @return el bean de {@link AuthenticationManager}.
     * @throws Exception si ocurre algún error durante la configuración.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura y devuelve el proveedor de autenticación.
     *
     * @return el bean de {@link AuthenticationProvider}.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Configura y devuelve el bean para codificar contraseñas.
     *
     * @return el bean de {@link PasswordEncoder}, implementado con {@link BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura y devuelve el servicio para cargar detalles de usuarios.
     * <p>
     * Busca empleados por su correo electrónico en el repositorio y los convierte a
     * {@link EmpleadoSeguridadDTO}.
     * </p>
     *
     * @return el bean de {@link UserDetailsService}.
     */
    @Bean
    public UserDetailsService userDetailService() {
        return username -> {
            EmpleadoEntity empleado = empleadoRepository.findByCorreoEmpleado(username)
                    .orElseThrow(() -> new UsernameNotFoundException(ERROR_USUARIO_NO_ENCONTRADO + username));
            return new EmpleadoSeguridadDTO(empleadoMapper.entityToDto(empleado));
        };
    }

    /**
     * Configuración de CORS para el entorno de desarrollo.
     * <p>
     * Permite solicitudes desde un origen específico (por ejemplo, localhost:4200)
     * y habilita métodos HTTP como GET, POST, PUT, DELETE y OPTIONS.
     * </p>
     *
     * @return el bean de {@link WebMvcConfigurer} con la configuración de CORS.
     */
    @Bean
    public WebMvcConfigurer corsConfigurerDev() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todas las rutas
                        .allowedOrigins(ORIGEN_DESARROLLO) // Permitir solo el origen de desarrollo
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*"); // Permitir todos los headers en desarrollo
            }
        };
    }
}
