package pl.miro.dziennikapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.miro.dziennikapp.model.User;
import pl.miro.dziennikapp.repository.UserRepository;

import java.util.List;

@Controller
public class LoginController {

    private UserRepository userRepository;

    private List<User> allUsers;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String login(Model model) {
        allUsers = (List<User>) userRepository.findAll();
        model.addAttribute("tempUser", new User());
        return "login.html";
    }

    @PostMapping("/verify")
    public String verifyUser(User user) {

        for (User u : allUsers) {
            if (u.getEmail().equals(user.getEmail()) &&
                    (u.getPassword().equals(user.getPassword())))
                if (u.getUserType() == 0) {
                    return "redirect:/list";
                } else if (u.getUserType() == 1) {
                    return "redirect:/adminlist";
                }
        }
        return "redirect:/";
    }
}
