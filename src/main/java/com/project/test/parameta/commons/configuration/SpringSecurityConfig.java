package com.project.test.parameta.commons.configuration;

import com.project.test.parameta.commons.util.exceptions.CustomAccessDeniedHandler;
import com.project.test.parameta.commons.util.exceptions.CustomAuthenticationEntryPoint;
import com.project.test.parameta.commons.util.helper.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.project.test.parameta.commons.util.constants.Constantes.*;

/**
 * Configuración de seguridad para la aplicación utilizando Spring Security.
 * <p>
 * Define las políticas de seguridad, manejo de excepciones, y autenticación basada en tokens JWT.
 * </p>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    /**
     * Filtro para autenticar solicitudes basadas en JWT.
     */
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Proveedor de autenticación personalizado.
     */
    private final AuthenticationProvider authenticationProvider;

    /**
     * Manejador para excepciones de autenticación no autorizada.
     */
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /**
     * Manejador para excepciones de acceso denegado.
     */
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * Configura la cadena de filtros de seguridad.
     * <p>
     * Define las políticas de seguridad para las solicitudes HTTP, como la deshabilitación de CSRF,
     * la configuración de rutas permitidas, y la autenticación para otras solicitudes. También configura
     * el manejo de excepciones y el uso de un proveedor de autenticación y filtros personalizados.
     * </p>
     *
     * @param http el objeto {@link HttpSecurity} utilizado para configurar la seguridad.
     * @return el bean de {@link SecurityFilterChain} con la configuración de seguridad.
     * @throws Exception si ocurre algún error durante la configuración de seguridad.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF
                .authorizeHttpRequests(auth -> // Configura permisos para las solicitudes HTTP
                        auth
                                .requestMatchers(
                                        V3_API_DOCS_PATH, // Permite acceso público a la documentación de API
                                        SWAGGER_UI_PATH, // Permite acceso público a Swagger UI
                                        EMPLEADO_PATH, // Permite acceso público a rutas relacionadas con empleados
                                        WS_PATH // Permite acceso público a rutas relacionadas con servicios web SOAP
                                ).permitAll()
                                .anyRequest().authenticated() // Requiere autenticación para otras solicitudes
                )
                .sessionManagement(sessionManager -> // Configura sesiones como stateless
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(handling -> // Configura manejadores de excepciones personalizadas
                        handling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler)
                )
                .authenticationProvider(authenticationProvider) // Configura el proveedor de autenticación
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Agrega el filtro JWT antes del filtro de autenticación
                .build();
    }
}
