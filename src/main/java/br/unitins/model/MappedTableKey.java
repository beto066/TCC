package br.unitins.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class MappedTableKey implements Serializable {
    @Column(name = "table_id")
    Long tableId;

    @Column(name = "value_id")
    Long valueId;
}
