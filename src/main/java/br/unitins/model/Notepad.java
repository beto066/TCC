package br.unitins.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Notepad")
public class Notepad extends Note {
    @Column(name = "title")
    public String title;
    public ArrayList<String> body;
}
