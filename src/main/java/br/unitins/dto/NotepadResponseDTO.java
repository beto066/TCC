package br.unitins.dto;

import java.util.List;

import br.unitins.model.Notepad;

public class NotepadResponseDTO extends NoteResponseDTO {
    private String title;
    private List<String> body;

    public NotepadResponseDTO(Notepad note) {
        super(note);
        title = note.title;
        body = note.body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }
}
