package br.unitins.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

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
