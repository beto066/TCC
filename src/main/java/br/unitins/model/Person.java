package br.unitins.model;

import jakarta.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public String cpf;
    // public Integer idade;

    public static Person findByName(String name) {
        return find("name", name).firstResult();
    }
}
