package pl.piomin.services.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piomin.services.organization.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
