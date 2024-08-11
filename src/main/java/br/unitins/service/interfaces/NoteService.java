package br.unitins.service.interfaces;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.NoteResponseDTO;
import br.unitins.dto.NoteResumeDTO;
import br.unitins.dto.NoteTableDTO;
import br.unitins.dto.NoteTableResponseDTO;
import br.unitins.dto.NoteTrainingDTO;
import br.unitins.dto.NoteTrainingResponseDTO;
import br.unitins.dto.NotepadDTO;
import br.unitins.dto.NotepadResponseDTO;
import br.unitins.model.Note;
import jakarta.validation.Valid;

public interface NoteService {
    NoteResponseDTO findById(Long id);

    NoteTableResponseDTO createNoteTable(NoteTableDTO dto, JsonWebToken token);

    NoteTrainingResponseDTO createNoteTraining(NoteTrainingDTO dto, JsonWebToken token);

    NotepadResponseDTO createNotepad(@Valid NotepadDTO dto, JsonWebToken token);

    Note update(Long id, NoteResumeDTO dto, JsonWebToken token);
}
