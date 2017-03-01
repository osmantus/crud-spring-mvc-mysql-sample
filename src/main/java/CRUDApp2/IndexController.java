package CRUDApp2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Alex on 23.02.2017.
 */
@Controller
public class IndexController
{
    @RequestMapping("/")
    String index(){
        return "index";
    }
}
