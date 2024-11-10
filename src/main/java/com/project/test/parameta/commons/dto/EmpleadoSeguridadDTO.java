package com.project.test.parameta.commons.dto;

import com.project.test.parameta.commons.util.constants.Constantes;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * DTO para representar los detalles de seguridad de un empleado.
 * <p>
 * Extiende la clase {@link EmpleadoDTO} para incluir los métodos necesarios
 * para implementar la interfaz {@link UserDetails} de Spring Security.
 * </p>
 */
@Data
public class EmpleadoSeguridadDTO extends EmpleadoDTO implements UserDetails {

    /**
     * Contraseña del empleado utilizada para la autenticación.
     */
    private String password;

    /**
     * Constructor que inicializa los datos de seguridad del empleado basándose
     * en un objeto {@link EmpleadoDTO}.
     *
     * @param empleadoDTO el DTO con la información del empleado.
     */
    public EmpleadoSeguridadDTO(EmpleadoDTO empleadoDTO) {
        super(empleadoDTO.getCodigoEmpleado(),
                empleadoDTO.getNombreEmpleado(),
                empleadoDTO.getApellidosEmpleado(),
                empleadoDTO.getNumeroDocumentoEmpleado(),
                empleadoDTO.getFechaNacimientoEmpleado(),
                empleadoDTO.getFechaVinculacionCompaniaEmpleado(),
                empleadoDTO.getCargoFk(),
                empleadoDTO.getTipoDocumentoFk(),
                empleadoDTO.getCorreoEmpleado(),
                empleadoDTO.getSalarioEmpleado(),
                empleadoDTO.getHashPassword());
    }

    /**
     * Obtiene las autoridades asignadas al empleado.
     *
     * @return una colección de objetos {@link GrantedAuthority} con las autoridades del empleado.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Constantes.ROL_EMPLEADO));
    }

    /**
     * Obtiene la contraseña del empleado para la autenticación.
     *
     * @return la contraseña cifrada del empleado.
     */
    @Override
    public String getPassword() {
        return super.getHashPassword();
    }

    /**
     * Obtiene el nombre de usuario del empleado para la autenticación.
     *
     * @return el correo electrónico del empleado.
     */
    @Override
    public String getUsername() {
        return super.getCorreoEmpleado();
    }

    /**
     * Verifica si la cuenta del empleado ha expirado.
     *
     * @return {@code true} si la cuenta no ha expirado.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Verifica si la cuenta del empleado está bloqueada.
     *
     * @return {@code true} si la cuenta no está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Verifica si las credenciales del empleado han expirado.
     *
     * @return {@code true} si las credenciales no han expirado.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Verifica si la cuenta del empleado está habilitada.
     *
     * @return {@code true} si la cuenta está habilitada.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
