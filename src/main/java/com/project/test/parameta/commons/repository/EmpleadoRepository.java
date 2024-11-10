package com.project.test.parameta.commons.repository;

import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de la entidad {@link EmpleadoEntity}.
 * <p>
 * Proporciona métodos estándar de CRUD a través de {@link JpaRepository} y métodos personalizados para
 * buscar empleados por su correo electrónico.
 * </p>
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, String> {

    /**
     * Busca un empleado por su correo electrónico.
     *
     * @param correoEmpleado el correo electrónico del empleado a buscar.
     * @return un {@link Optional} que contiene la entidad {@link EmpleadoEntity} si se encuentra,
     * de lo contrario, está vacío.
     */
    Optional<EmpleadoEntity> findByCorreoEmpleado(String correoEmpleado);
}
