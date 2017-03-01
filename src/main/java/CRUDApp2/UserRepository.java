package CRUDApp2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by Alex on 22.02.2017.
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    List<User> findByNameContainingIgnoreCase(String name);
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
