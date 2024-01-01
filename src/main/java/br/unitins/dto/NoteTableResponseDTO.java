package br.unitins.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.model.NoteTable;

public class NoteTableResponseDTO extends NoteResponseDTO {
    private List<NoteTableValueResoponseDTO> values;

    public NoteTableResponseDTO(NoteTable note) {
        super(note);
        values = note.values.stream().map(t -> new NoteTableValueResoponseDTO(t.value)).collect(Collectors.toList());
    }

    public List<NoteTableValueResoponseDTO> getValues() {
        if (values == null) {
            values = new ArrayList<NoteTableValueResoponseDTO>();
        }
        return values;
    }

    public void setValues(List<NoteTableValueResoponseDTO> values) {
        this.values = values;
    }
}
