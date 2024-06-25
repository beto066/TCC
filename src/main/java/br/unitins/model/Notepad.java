package br.unitins.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Notepad")
public class Notepad extends Note {
    @Column(name = "title")
    public String title;
    public ArrayList<String> body;
}
