package br.unitins.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "note_tables")
public class NoteTable extends Note {
    @OneToMany(mappedBy = "table")
    public List<MappedTableValue> values;
}
