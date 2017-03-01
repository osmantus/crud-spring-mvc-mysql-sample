package CRUDApp2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Alex on 22.02.2017.
 */
public interface IUserModel
{
    List<User> listAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    //Page<User> findByName(String name, Pageable pageable);
    Page<User> findAllPageable(Pageable pageable);
    List<User> findByNameContainingStr(String name);
    Page<User> findByNameContainingStr(String name, Pageable pageable);
}
