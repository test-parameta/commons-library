package com.project.test.parameta.commons.service.impl;

import com.project.test.parameta.commons.dto.AuthResponseDTO;
import com.project.test.parameta.commons.dto.EmpleadoSeguridadDTO;
import com.project.test.parameta.commons.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.project.test.parameta.commons.util.constants.Constantes.*;

/**
 * Servicio para la gestión de tokens JWT.
 * <p>
 * Proporciona métodos para generar, validar y extraer información de tokens JWT.
 * </p>
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class JwtService implements IJwtService {

    /**
     * Clave secreta utilizada para firmar y verificar los tokens JWT.
     */
    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * Genera un token JWT para el usuario especificado.
     *
     * @param userDetails los detalles del usuario que se incluyen en el token.
     * @return el token JWT generado.
     */
    @Override
    public String getToken(UserDetails userDetails) {
        Map<String, Object> additionalClaims = new HashMap<>();
        EmpleadoSeguridadDTO empleadoSeguridadDTO = (EmpleadoSeguridadDTO) userDetails;
        additionalClaims.put(CLAIM_CODIGO_EMPLEADO, empleadoSeguridadDTO.getCodigoEmpleado());
        return buildToken(additionalClaims, userDetails);
    }

    /**
     * Extrae el nombre de usuario (subject) de un token JWT.
     *
     * @param token el token JWT del que se extrae el nombre de usuario.
     * @return el nombre de usuario contenido en el token.
     */
    @Override
    public String getUsernameFromToken(String token) {
        return getClaims(token, Claims::getSubject);
    }

    /**
     * Verifica si un token JWT es válido para el usuario especificado.
     *
     * @param token       el token JWT a validar.
     * @param userDetails los detalles del usuario para comparar.
     * @return true si el token es válido, false en caso contrario.
     */
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Genera un objeto {@link AuthResponseDTO} que contiene un token JWT para el usuario especificado.
     *
     * @param userDetails los detalles del usuario para generar el token.
     * @return un objeto {@link AuthResponseDTO} con el token generado.
     */
    public AuthResponseDTO generarToken(UserDetails userDetails) {
        return AuthResponseDTO.builder().token(getToken(userDetails)).build();
    }

    /**
     * Extrae un identificador personalizado del token JWT, como un código de empleado.
     *
     * @param token el token JWT del que se extrae el identificador.
     * @return el identificador contenido en el token.
     * @throws IllegalArgumentException si el token no contiene un identificador válido.
     */
    public String getIdentificadorFromToken(String token) {
        Claims claims = getAllClaims(token);

        if (claims.containsKey(CLAIM_CODIGO_EMPLEADO)) {
            return claims.get(CLAIM_CODIGO_EMPLEADO, String.class);
        }
        throw new IllegalArgumentException(ERROR_IDENTIFICADOR_NO_VALIDO);
    }

    /**
     * Obtiene todos los claims de un token JWT.
     *
     * @param token el token JWT del que se extraen los claims.
     * @return un objeto {@link Claims} con los datos del token.
     */
    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Construye un token JWT con los claims adicionales y los detalles del usuario.
     *
     * @param extraClaims los claims adicionales a incluir en el token.
     * @param user        los detalles del usuario.
     * @return el token JWT generado.
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails user) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLIS))
                .signWith(key)
                .compact();
    }

    /**
     * Extrae un claim específico de un token JWT utilizando un resolver de claims.
     *
     * @param token         el token JWT del que se extraen los claims.
     * @param claimsResolver la función para resolver el claim deseado.
     * @param <T>           el tipo del claim.
     * @return el claim extraído.
     */
    private <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene la fecha de expiración de un token JWT.
     *
     * @param token el token JWT del que se extrae la fecha de expiración.
     * @return la fecha de expiración del token.
     */
    public Date getExpiration(String token) {
        return getClaims(token, Claims::getExpiration);
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token el token JWT a verificar.
     * @return true si el token ha expirado, false en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
