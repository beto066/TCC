package br.unitins.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class MappedTableValue {
    @EmbeddedId
    public MappedTableKey id;

    @ManyToOne
    @MapsId("tableId")
    @JoinColumn(name = "table_id")
    public NoteTable table;

    @ManyToOne
    @MapsId("valueId")
    @JoinColumn(name = "value_id")
    public NoteTableValue value;

    public Integer position;
}
