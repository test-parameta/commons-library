package com.project.test.parameta.commons.dto;

import com.project.test.parameta.commons.util.constants.Constantes;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class EmpleadoSeguridadDTO extends EmpleadoDTO implements UserDetails {

    private String password;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Constantes.ROL_EMPLEADO));
    }

    @Override
    public String getPassword() {
        return super.getHashPassword();
    }

    @Override
    public String getUsername() {
        return super.getCorreoEmpleado();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
