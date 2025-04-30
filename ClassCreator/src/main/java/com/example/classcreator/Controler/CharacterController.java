package com.example.classcreator.Controler;

import com.example.classcreator.service.CharacterService;
import com.example.classcreator.service.UserService;
import com.example.classcreator.model.Character;
import com.example.classcreator.model.User;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.List;
import java.util.UUID;

@Controller
public class CharacterController {

    private final CharacterService characterService;
    private final UserService userService;  // Inject UserService

    public CharacterController(CharacterService characterService, UserService userService) {
        this.characterService = characterService;
        this.userService = userService;
    }

    // Character-related methods (same as before)

    @GetMapping("/personnage/create")
    public String createPersonnage(Model model) {
        model.addAttribute("character", new Character());
        return "personnageCreation";
    }

    @PostMapping("/saveCharacter")
    public String saveCharacter(@ModelAttribute Character character,
                                @CookieValue(value = "user", defaultValue = "") String login,
                                Model model) {
        if (!login.isEmpty()) {
            characterService.saveCharacter(character, login);
            model.addAttribute("personnage", character);
            return "personnageDetails";
        }
        return "redirect:/login";
    }

    @GetMapping("/personnage/{id}")
    public String personnageDetails(@PathVariable UUID id, Model model) {
        Character personnage = characterService.getCharacterDetails(id);
        model.addAttribute("personnage", personnage);
        return "personnageDetails";
    }

    @PostMapping("/redo-stats")
    public String redoStats(@ModelAttribute Character character, Model model) {
        characterService.redostats(character);
        model.addAttribute("character", character);
        characterService.saveCharacter(character, null );
        return "perso";
    }

    @GetMapping("/personnages")
    public String personnagesList(@CookieValue(value = "user", defaultValue = "") String login, Model model) {
        if (!login.isEmpty()) {
            List<Character> characterList = characterService.getCharactersByOwner(login);
            model.addAttribute("characterList", characterList);
            return "homePageCharacterList";
        }
        return "redirect:/login";
    }

    @GetMapping("/homepage")
    public String homepage(Model model) {
        model.addAttribute("loggedIn", false);
        return "homePage";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User("", ""));
        return "register";  // Points to the registration page template
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        boolean success = userService.registerUser(user);
        if (success) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Username already taken or invalid.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User("", ""));
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        boolean isValid = userService.validateLogin(user.getLogin(), user.getPassword());
        Cookie cookie = new Cookie("user", user.getLogin());
        if (isValid) {

            return "redirect:/personnages";
        } else {
            model.addAttribute("error", "Invalid login or password.");
            return "login";
        }
    }


}

