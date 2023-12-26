package br.unitins.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NoteTable")
public class NoteTable extends Note {
    @OneToMany(mappedBy = "table")
    public List<NoteTableValue> values;
}
