package se.lexicon.spring_boot_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.spring_boot_rest_api.model.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String RoleName);
}
