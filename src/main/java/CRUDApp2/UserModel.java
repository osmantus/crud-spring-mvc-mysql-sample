package CRUDApp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 22.02.2017.
 */
@Service
public class UserModel implements IUserModel
{
    private UserRepository repo;

    @Autowired
    public void setUserRepository(UserRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public List<User> listAllUsers()
    {
        return repo.findAll();
    }

    @Override
    public User getUserById(Long id)
    {
        return repo.findOne(id);
    }

    @Override
    public User saveUser(User user)
    {
        return repo.save(user);
    }

    @Override
    public void deleteUser(Long id)
    {
        repo.delete(id);
    }

    @Override
    public Page<User> findAllPageable(Pageable pageable)
    {
        //return repo.findByNameContainingIgnoreCase(name);
        //return repo.findByName(name, pageable);
        return repo.findAll(pageable);
    }

    @Override
    public List<User> findByNameContainingStr(String name)
    {
        return (List<User>) repo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Page<User> findByNameContainingStr(String name, Pageable pageable)
    {
        return repo.findByNameContainingIgnoreCase(name, pageable);
    }
}
