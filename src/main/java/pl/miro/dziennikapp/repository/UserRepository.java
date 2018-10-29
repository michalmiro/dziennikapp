package pl.miro.dziennikapp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.miro.dziennikapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
