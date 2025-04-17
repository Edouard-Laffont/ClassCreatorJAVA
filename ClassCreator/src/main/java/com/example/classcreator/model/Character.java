package com.example.classcreator.model;

import com.example.classcreator.Stats;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Document
public class Character {
    @MongoId
    private UUID id;
    private String name;
    private String classe;
    private String race;
    private int level;
    private List<Stats> stats;
    private String ownerLogin;  // This will link the character to a specific user

    // Getters and setters
    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public Character() {
        this.id = UUID.randomUUID();
        this.level = 1;
        this.stats = generateAtribute();
    }
/*
    public Character(String name, String classe, String race) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.classe = classe;
        this.race = race;
        this.level = 1;
        this.stats = generateAtribute();
    }*/

    //-------------------------------------------------------------------------------------------------------------

    public List<Stats> getStats() {
        return stats;
    }

    private List<Stats> generateAtribute() {

        List<Stats> abilities = new ArrayList<Stats>();

        abilities.add(new Stats("Force", 0));
        abilities.add(new Stats("Agilite", 0));
        abilities.add(new Stats("Endurance", 0));
        abilities.add(new Stats("Speed", 0));
        abilities.add(new Stats("Intelligence", 0));
        abilities.add(new Stats("Luck", 0));
        abilities.add(new Stats("Charisme", 0));

        return addPoints(abilities,20);
    }

    private List<Stats> addPoints(List<Stats> stats,int points){

        for(int i = 0 ; i <= points; i++){

            Random rand = new Random();

            int rand_int = rand.nextInt(7);
            stats.get(rand_int).increasePoints();

        }

        System.out.println(stats);
        return stats;
    }

    public void redostats(){

        generateAtribute();
    }


    //-------------------------------------------------------------------------------------------------------------

    public void addLevel(){
        level++;
    }


    //-------------------------------------------------------------------------------------------------------------

    public UUID getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getClasse(){
        return classe;
    }

    public String getRace(){
        return race;
    }

    public int getLevel(){
        return level;
    }

    //-------------------------------------------------------------------------------------------------------------

    public void setId(){
        this.id = id;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setClasse(String Classe){
        this.classe = Classe;
    }

    public void setRace(String Race){
        this.race = Race;
    }

    public void setLevel(int level){
        this.level = level;
    }
}


