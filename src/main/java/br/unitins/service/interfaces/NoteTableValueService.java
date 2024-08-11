package br.unitins.service.interfaces;

import java.util.List;

import br.unitins.dto.NoteTableValueDTO;
import br.unitins.dto.NoteTableValueResoponseDTO;
import jakarta.ws.rs.NotFoundException;

public interface NoteTableValueService {
    List<NoteTableValueResoponseDTO> getAll(Long therapistId);

    List<NoteTableValueResoponseDTO> search(String search, Long therapistId);

    NoteTableValueResoponseDTO find(Long noteTableId) throws NotFoundException;

    NoteTableValueResoponseDTO store(Long therapistId, NoteTableValueDTO dto);

    void delete(Long therapistId, Long id);
}
