package km.month9.myshop.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Integer> {

    PasswordResetToken getByToken(String token);

    @Query(value = "select p.user from PasswordResetToken p where p.id = 1")
    List<User> findAllUser();

    boolean existsByToken(String token);
}
