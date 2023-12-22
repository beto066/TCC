package br.unitins.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@MappedSuperclass
public class DefaultEntity extends PanacheEntityBase {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "data_cadastro")
    public LocalDate dataCadastro;

    @Column(name = "data_alteracao")
    public LocalDate dataAlteracao;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAlteracao = LocalDate.now();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultEntity other = (DefaultEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
