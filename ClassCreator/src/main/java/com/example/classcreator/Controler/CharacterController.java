package com.example.classcreator.Controler;

import com.example.classcreator.service.CharacterService;
import com.example.classcreator.model.Character;
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

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

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
        characterService.saveCharacter(character, null /*erreur içi!!!*/);
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

    @GetMapping("/Homepage")
    public String homepage(Model model) {
        return "homePage";
    }
}
