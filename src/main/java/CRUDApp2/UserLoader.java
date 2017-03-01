package CRUDApp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Alex on 23.02.2017.
 */
@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository repo;

    @Autowired
    public void setUserRepository(UserRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        /*User user = new User();
        user.setName("Tom");
        user.setAge(25);
        user.setAdmin(false);
        repo.save(user);

        user = new User();
        user.setName("George");
        user.setAge(30);
        user.setAdmin(true);
        repo.save(user);

        user = new User();
        user.setName("Kate");
        user.setAge(35);
        user.setAdmin(false);
        repo.save(user);*/
    }
}
