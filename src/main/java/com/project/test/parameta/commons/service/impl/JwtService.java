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

@Service
@Log4j2
@RequiredArgsConstructor
public class JwtService implements IJwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    // Este método ahora acepta cualquier objeto que implemente UserDetails
    @Override
    public String getToken(UserDetails userDetails) {
        Map<String, Object> additionalClaims = new HashMap<>();

        // Asume que el UserDetails es del tipo EstudianteSeguridadDTO o AsistenciaSeguridadDTO

        EmpleadoSeguridadDTO empleadoSeguridadDTO = (EmpleadoSeguridadDTO) userDetails;
        additionalClaims.put("codigoEmpleado", empleadoSeguridadDTO.getCodigoEmpleado());
        return buildToken(additionalClaims, userDetails);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaims(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public AuthResponseDTO generarToken(UserDetails userDetails) {
        return AuthResponseDTO.builder().token(getToken(userDetails)).build();
    }

    private <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpiration(String token) {
        return getClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public String getIdentificadorFromToken(String token) {
        // Aquí se generaliza la obtención del identificador.
        // Puede ser codigoEstudiante, codigoAsistencia, o cualquier otro identificador según el DTO
        Claims claims = getAllClaims(token);

        if (claims.containsKey("codigoEmpleado")) {
            return claims.get("codigoEmpleado", String.class);
        }
        // Manejo de error en caso de que no haya un identificador válido en el token
        throw new IllegalArgumentException("Token no contiene un identificador válido.");
    }


    private Claims getAllClaims(String token) {
        return Jwts.parser().
                verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))).
                build().parseClaimsJws(token).getBody();
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails user) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)); // Cambia esto por el algoritmo adecuado si es necesario
        long expirationTimeInMillis = 24 * 60 * 60 * 1000;
        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(key)
                .compact();
    }
}
