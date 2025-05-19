package fsa.cplminiprj.controller;

import fsa.cplminiprj.entity.User;
import fsa.cplminiprj.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
public class HomeController {
    private final UserRepository userRepository;

    List<User> testList = new ArrayList<>();

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/create-test-data")
    public String createTestData() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("user_" + i);
            user.setPassword("<PASSWORD>");
            user.setStatus(i % 3);
            userRepository.save(user);
        }
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String home(Model model) {
        System.out.println("FIND USER BY ID:");
        User user01 = userRepository.findById(1L).orElse(null);
        System.out.println(user01);
//        model.addAttribute("user", user01);

        System.out.println("SEARCH USER:");
        userRepository.search("r_2").forEach(System.out::println);

        System.out.println("COUNT USER:");
        System.out.println(userRepository.count());

        System.out.println("GET STATUS STATS:");
        userRepository.getStatusStats().forEach(System.out::println);

        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "index";
    }
}
