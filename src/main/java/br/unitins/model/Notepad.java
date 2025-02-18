package br.unitins.model;

import java.util.ArrayList;

import br.unitins.model.converter.StringListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "notepads")
public class Notepad extends Note {
    @Column(name = "title")
    public String title;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    public ArrayList<String> body;
}
