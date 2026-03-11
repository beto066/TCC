package br.unitins.dto;

import java.time.LocalDateTime;

public class NoteFilterDTO {
    private String title;
    private LocalDateTime from;
    private LocalDateTime to;
    private Integer type;

    public NoteFilterDTO() {}

    public NoteFilterDTO(
        String title, 
        LocalDateTime from, 
        LocalDateTime to, 
        Integer type
    ) {
        this.title = title;
        this.from = from;
        this.to = to;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public LocalDateTime getFrom() {
        return from;
    }
    
    public void setFrom(LocalDateTime from) {
        this.from = from;
    }
    
    public LocalDateTime getTo() {
        return to;
    }
    
    public void setTo(LocalDateTime to) {
        this.to = to;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
}
