package br.unitins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mapped_table_values")
public class MappedTableValue extends DefaultEntity {
    public Integer position;

    @ManyToOne
    @JoinColumn(name = "table_id")
    public NoteTable table;

    @ManyToOne
    @JoinColumn(name = "value_id")
    public NoteTableValue value;
}
