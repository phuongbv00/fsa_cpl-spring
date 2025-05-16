package fsa.cplminiprj.controller;

import fsa.cplminiprj.entity.User;
import fsa.cplminiprj.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Instant;

@Controller
@Transactional
public class HomeController {
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String home(Model model) {
        User user = new User();
        user.setUsername("user_" + Instant.now().getEpochSecond());
        user.setPassword("<PASSWORD>");
        userRepository.save(user);
        User user01 = userRepository.findById(1L).orElse(null);
        model.addAttribute("user", user01);
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "index";
    }
}
