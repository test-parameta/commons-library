package com.project.test.parameta.commons.entity;

import com.project.test.parameta.commons.util.enums.TipoDocumentoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_documento", schema = "parameta")
public class TipoDocumentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_documento", nullable = false)
    private Integer idTipoDocumento;

    @Column(name = "nombre_tipo_documento", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TipoDocumentoEnum nombreTipoDocumento;

}