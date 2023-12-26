package br.unitins.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Notepad")
public class Notepad extends Note {
    public String title;
    public String body;
}
