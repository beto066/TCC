package br.unitins.dto;

import br.unitins.model.MappedTableKey;
import br.unitins.model.MappedTableValue;
import br.unitins.model.NoteTable;
import br.unitins.repository.MappedTableValueRepository;
import br.unitins.repository.NoteTableValueRepository;

public class MappedTableValueDTO {
    private Long tableId;
    private Long valueId;
    private Integer position;

    public MappedTableValue toMappedTableValue(NoteTableValueRepository vRepository) {
        MappedTableValue mapped = new MappedTableValue();
        mapped.value = vRepository.findById(valueId);
        mapped.position = position;
        mapped.id = new MappedTableKey();
        mapped.id.tableId = tableId;
        mapped.id.tableId = valueId;
        return mapped;
    }

    public MappedTableValue toMappedTableValue(NoteTableValueRepository vRepository, NoteTable note) {
        MappedTableValueRepository repository = new MappedTableValueRepository();
        MappedTableValue mapped = new MappedTableValue();
        mapped.value = vRepository.findById(valueId);
        mapped.position = position;
        mapped.table = note;
        mapped.id = new MappedTableKey();
        mapped.id.tableId = note.id;
        mapped.id.tableId = valueId;
        repository.persist(mapped);
        return mapped;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
