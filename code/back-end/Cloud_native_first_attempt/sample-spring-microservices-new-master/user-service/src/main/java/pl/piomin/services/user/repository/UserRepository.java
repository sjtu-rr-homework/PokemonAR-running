package pl.piomin.services.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piomin.services.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
