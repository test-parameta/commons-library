package com.project.test.parameta.commons.repository;

import com.project.test.parameta.commons.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, String> {

    Optional<EmpleadoEntity> findByCorreoEmpleado(String correoEmpleado);
}
