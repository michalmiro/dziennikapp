package pl.miro.dziennikapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.miro.dziennikapp.model.Announcment;

import java.util.Optional;

public interface AnnouncmentRepository extends CrudRepository<Announcment, Long> {
    Optional<Announcment> findByContentsIsContaining(String contents);
}
