package br.unitins.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MappedTableKey implements Serializable {
    @Column(name = "table_id")
    public Long tableId;

    @Column(name = "value_id")
    public Long valueId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
        result = prime * result + ((valueId == null) ? 0 : valueId.hashCode());
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
        MappedTableKey other = (MappedTableKey) obj;
        if (tableId == null) {
            if (other.tableId != null)
                return false;
        } else if (!tableId.equals(other.tableId))
            return false;
        if (valueId == null) {
            if (other.valueId != null)
                return false;
        } else if (!valueId.equals(other.valueId))
            return false;
        return true;
    }
}
