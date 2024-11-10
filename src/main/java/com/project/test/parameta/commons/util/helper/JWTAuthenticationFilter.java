package com.project.test.parameta.commons.util.helper;

import com.project.test.parameta.commons.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.project.test.parameta.commons.util.constants.Constantes.BEARER_PREFIX;
import static com.project.test.parameta.commons.util.constants.Constantes.SIZE_BEARER;

/**
 * Filtro de autenticación basado en JWT que se ejecuta una vez por cada solicitud.
 * <p>
 * Verifica la validez de un token JWT y autentica al usuario si el token es válido.
 * </p>
 */
@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Servicio para operaciones relacionadas con JWT.
     */
    private final JwtService jwtService;

    /**
     * Servicio para cargar detalles del usuario.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Filtra las solicitudes entrantes para verificar la autenticidad del token JWT.
     * <p>
     * Si el token es válido y no hay una autenticación previa en el contexto de seguridad,
     * autentica al usuario y establece el contexto de seguridad.
     * </p>
     *
     * @param request     la solicitud HTTP entrante.
     * @param response    la respuesta HTTP.
     * @param filterChain la cadena de filtros a ejecutar.
     * @throws ServletException si ocurre un error en el filtro.
     * @throws IOException      si ocurre un error de E/S.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = getTokenFromRequest(request);
        String username = "";
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.getUsernameFromToken(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Extrae el token JWT de la cabecera de autorización de la solicitud HTTP.
     * <p>
     * Busca el token en el encabezado {@code Authorization} y verifica que comience con "Bearer ".
     * </p>
     *
     * @param request la solicitud HTTP.
     * @return el token JWT si está presente y es válido; de lo contrario, {@code null}.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(SIZE_BEARER);
        }
        return null;
    }
}
