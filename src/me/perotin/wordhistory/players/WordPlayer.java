package me.perotin.wordhistory.players;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class WordPlayer {

    private String name;
    private UUID uuid;
    private HashMap<String, String> phrases;
    private boolean grammar;


    // default constructor for a new player
    public WordPlayer(String name, UUID uuid){
        this.name = name;
        this.uuid = uuid;
        this.phrases = new HashMap<>();
        this.grammar = true;
    }


    public WordPlayer(String name, UUID uuid, HashMap<String, String> phrases, boolean grammar) {
        this.name = name;
        this.uuid = uuid;
        this.phrases = phrases;
        this.grammar = grammar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public HashMap<String, String> getPhrases() {
        return phrases;
    }

    public void addPhrase(String keyword, String phrase) {
        phrases.put(keyword, phrase);
    }

    public boolean isGrammar() {
        return grammar;
    }

    public void setGrammar(boolean grammar) {
        this.grammar = grammar;
    }

    public Player getPlayer(){
        if(Bukkit.getPlayer(uuid) != null){
            return Bukkit.getPlayer(uuid);
        } else {
            throw new NullPointerException("Player is null for WordPlayer!");

        }

    }
}
