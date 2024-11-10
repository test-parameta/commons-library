package com.project.test.parameta.commons.entity;

import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad que representa la tabla "tipo_documento" en la base de datos.
 * <p>
 * Almacena información sobre los tipos de documentos, como su identificador único
 * y su nombre, que está representado como un enum {@link TipoDocumentoEnum}.
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "tipo_documento", schema = "parameta")
public class TipoDocumentoEntity {

    /**
     * Identificador único del tipo de documento.
     * Generado automáticamente mediante estrategia {@link GenerationType#IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento", nullable = false)
    private Integer idTipoDocumento;

    /**
     * Nombre del tipo de documento, representado como un valor del enum {@link TipoDocumentoEnum}.
     * Este campo es obligatorio y se almacena como una cadena en la base de datos.
     */
    @Column(name = "nombre_tipo_documento", nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private TipoDocumentoEnum nombreTipoDocumento;
}
