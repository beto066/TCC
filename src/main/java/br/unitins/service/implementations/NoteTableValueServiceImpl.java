package br.unitins.service.implementations;

import java.util.List;

import br.unitins.dto.NoteTableValueDTO;
import br.unitins.dto.NoteTableValueResoponseDTO;
import br.unitins.model.NoteTableValue;
import br.unitins.model.Therapist;
import br.unitins.repository.NoteTableValueRepository;
import br.unitins.repository.UserRepository;
import br.unitins.service.interfaces.NoteTableValueService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class NoteTableValueServiceImpl implements NoteTableValueService {
    @Inject
    private NoteTableValueRepository repository;

    @Override
    public List<NoteTableValueResoponseDTO> getAll(Long therapistId) {
        return repository.findByTherapist(therapistId);
    }

    @Override
    public List<NoteTableValueResoponseDTO> search(String search, Long therapistId) {
        return repository.search(search, therapistId);
    }

    @Override
    public NoteTableValueResoponseDTO find(Long noteTableId) throws NotFoundException {
        NoteTableValueResoponseDTO value = repository.findValue(noteTableId);

        if (value == null) {
            throw new NotFoundException();
        }

        return value;
    }

    @Override
    @Transactional
    public NoteTableValueResoponseDTO store(Long therapistId, NoteTableValueDTO dto) {
        UserRepository uRepository = new UserRepository();
        NoteTableValue value = dto.toNoteTableValue();
        value.therapist = (Therapist) uRepository.findById(therapistId);
        repository.persist(value);

        return new NoteTableValueResoponseDTO(value);
    }

    @Override
    @Transactional
    public void delete(Long therapistId, Long id) {
        NoteTableValue value = repository.findByIdAndTherapist(id, therapistId);
        value.active = false;
    }
}
