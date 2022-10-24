package se.lexicon.spring_boot_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.spring_boot_rest_api.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String username);
    boolean existsByUserName(String username);

    @Modifying
    @Query("update users u set u.password=:pw where u.userName=:un")
    void updatePasswordByUsername(@Param("un") String username, @Param("pw") String password);

    @Modifying
    @Query("UPDATE users u set u.expired=:status where u.userName= :un")
    void updateExpiredByUsername(@Param("un") String username,@Param("status") boolean status );

}
