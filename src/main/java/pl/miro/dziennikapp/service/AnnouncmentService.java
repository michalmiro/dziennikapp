package pl.miro.dziennikapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.miro.dziennikapp.model.Announcment;
import pl.miro.dziennikapp.repository.AnnouncmentRepository;


import java.util.Optional;

@Service
public class AnnouncmentService {

    private AnnouncmentRepository announcmentRepository;

    public AnnouncmentService(AnnouncmentRepository announcmentRepository) {
        this.announcmentRepository = announcmentRepository;
    }

    @Transactional
    public void update(Long id, String contents) {
        Optional<Announcment> byId = announcmentRepository.findById(id);
        Announcment updatedAnnouncment = byId.get();
        updatedAnnouncment.setContents(contents);
    }
}
