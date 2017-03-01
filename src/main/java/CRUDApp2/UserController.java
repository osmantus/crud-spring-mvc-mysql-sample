package CRUDApp2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex on 22.02.2017.
 */
@Controller
public class UserController
{
    private IUserModel iUserModel;

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 10;

    @Autowired
    public void setUserModel(IUserModel iUserModel) {
        this.iUserModel = iUserModel;
    }

    /*@RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("users", iUserModel.listAllUsers());
        return "users";
    }*/

    /*@RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model, @PageableDefault(size = PAGE_SIZE) Pageable pageable){
        Page<User> users = iUserModel.findByName(pageable);
        model.addAttribute("users", users);
        model.addAttribute("query", "select all from User");
        //model.addAttribute("query", query);
        //setPageData(model, pageable, users);
        return "users";
    }*/

    /*private void setPageData(Model model, Pageable pageable, Page<User> users) {
        model.addAttribute("totalElements", users.getTotalElements());
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("pageSize", pageable.getPageSize());
        model.addAttribute("current", pageable.getPageNumber());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
    }*/

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView showPersonsPage(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page)
    {
        ModelAndView modelAndView = new ModelAndView("users");

        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        Page<User> users = iUserModel.findAllPageable(new PageRequest(evalPage, evalPageSize));

        /*Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("users", users);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pager", pager);*/

        Pager pager = null;
        if (users != null) {
            if (!users.isLast())
                pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
            else
            {
                users = iUserModel.findAllPageable(new PageRequest(users.getTotalPages() - 1, evalPageSize));
                pager = new Pager(users.getTotalPages(), users.getTotalPages() - 1, BUTTONS_TO_SHOW);
            }
        }
        modelAndView.addObject("users", users);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pager", pager);

        return modelAndView;
    }

    @RequestMapping("user/{id}")
    public String showUser(@PathVariable Long id, Model model){
        model.addAttribute("user", iUserModel.getUserById(id));
        return "usershow";
    }

    /*@RequestMapping("user/find/{name}")
    public String findByUserName(@PathVariable String name, Model model){
        //List<User> users = iUserModel.findByName(name);
        List<User> users = iUserModel.findByNameContainingStr(name);
        model.addAttribute("users", users);
        model.addAttribute("text_to_find", name);
        return "users_after_find";
    }*/

    @RequestMapping(value = "user/find/{name}", method = RequestMethod.GET)
    public ModelAndView findByUserName(@PathVariable String name, @RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "page", required = false) Integer page){
        //List<User> users = iUserModel.findByName(name);

        ModelAndView modelAndView = new ModelAndView("users_after_find");

        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        Page<User> users = iUserModel.findByNameContainingStr(name, new PageRequest(evalPage, evalPageSize));
        //Pager pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);

        Pager pager = null;
        if (users != null) {
            if (!users.isLast()) {
                pager = new Pager(users.getTotalPages(), users.getNumber(), BUTTONS_TO_SHOW);
            } else {
                users = iUserModel.findByNameContainingStr(name, new PageRequest(users.getTotalPages() - 1, evalPageSize));
                pager = new Pager(users.getTotalPages(), users.getTotalPages() - 1, BUTTONS_TO_SHOW);
            }
        }

        modelAndView.addObject("users", users);
        modelAndView.addObject("searchText", name);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pager", pager);

        return modelAndView;
    }

    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("user", iUserModel.getUserById(id));
        return "userform";
    }

    @RequestMapping("user/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "newuserform";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser(User user){

        iUserModel.saveUser(user);
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping("user/delete/{id}")
    public String delete(@PathVariable Long id){
        iUserModel.deleteUser(id);
        return "redirect:/users";
    }

}
