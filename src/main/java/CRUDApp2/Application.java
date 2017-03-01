package CRUDApp2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.util.Locale;

/**
 * Created by Alex on 22.02.2017.
 */
@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        Locale.setDefault(new Locale("ru"));
        SpringApplication.run(Application.class);
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    public CommandLineRunner loadData(UserRepository repository) {
        return (args) ->
        {
            //repository.save(new User("Alex", 25, true));
            //repository.save(new User("George", 30, false));
            //repository.save(new User("Tom", 35, false));

            for (User user : repository.findAll())
            {
                System.out.println(user);
            }
        };
    }
}
