package com.example.classcreator.service;

import com.example.classcreator.dao.MessageMongoDao;
import com.example.classcreator.model.Character;
import com.example.classcreator.Stats;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class CharacterService {

    private final MessageMongoDao messageMongoDao;

    public CharacterService(MessageMongoDao messageMongoDao) {
        this.messageMongoDao = messageMongoDao;
    }

    public Character saveCharacter(Character character, String login) {
        character.setOwnerLogin(login);
        return messageMongoDao.save(character);
    }

    public List<Character> getCharactersByOwner(String login) {
        return messageMongoDao.findByOwnerLogin(login);
    }


    private List<Stats> addPoints(List<Stats> stats, int points) {
        stats.forEach(Stats::resetPoints);

        for (int i = 0; i <= points; i++) {
            Random rand = new Random();
            int rand_int = rand.nextInt(7);
            stats.get(rand_int).increasePoints();
        }

        System.out.println(stats);
        return stats;
    }

    public void redostats(Character actualCharacter) {
        addPoints(actualCharacter.getStats(), 20);
    }

    public Character getCharacterDetails(UUID id) {
        return messageMongoDao.findById(id).orElseThrow();
    }
}
