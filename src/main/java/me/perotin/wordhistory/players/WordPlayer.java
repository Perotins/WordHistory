package me.perotin.wordhistory.players;

import me.perotin.wordhistory.ChatMessage;
import me.perotin.wordhistory.CommandMessage;
import me.perotin.wordhistory.WordHistory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class WordPlayer {

    private String name;
    private UUID uuid;
    private HashMap<String, String> phrases;
    private boolean grammar;
    private boolean punctuation;
    private boolean commas;
    private boolean capitalize;
    private boolean spellCheck;
    private List<CommandMessage> commands;
    private List<ChatMessage> messages;


    // default constructor for a new player
    public WordPlayer(String name, UUID uuid){
        this.name = name;
        this.uuid = uuid;
        this.phrases = new HashMap<>();
        this.grammar = true;
        this.punctuation = true;
        this.commas = true;
        this.capitalize = true;
        this.punctuation = true;
        this.commands = new ArrayList<>();
        this.messages = new ArrayList<>();
    }


    public List<CommandMessage> getCommands() {

        return commands;
    }

    public void addCommand(String command, String date) {
       //TODO
        int max = WordHistory.getPlugin().getConfig().getInt("max-commands");
        if(getCommands().size() == max){
            getCommands().remove(0);
        }
        getCommands().add(new CommandMessage(command, date));
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void addMessage(String message, String date) {
       // todo
        int max = WordHistory.getPlugin().getConfig().getInt("max-messages");
        if(getMessages().size() == max){
            getMessages().remove(0);
        }
        getMessages().add(new ChatMessage(message, date));
    }

    public WordPlayer(String name, UUID uuid, HashMap<String, String> phrases, boolean grammar) {
        this.name = name;
        this.uuid = uuid;
        this.phrases = phrases;
        this.grammar = grammar;
        if(!grammar){
            this.punctuation = false;
            this.commas = false;
            this.capitalize = false;
            this.spellCheck = false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private UUID getUuid() {
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

    public boolean isPunctuation() {
        return punctuation;
    }

    public void setPuncuation(boolean puncuation) {
        this.punctuation = puncuation;
    }

    public boolean isCommas() {
        return commas;
    }

    public void setCommas(boolean commas) {
        this.commas = commas;
    }

    public boolean isCapitalize() {
        return capitalize;
    }

    public void setCapitalize(boolean capitalize) {
        this.capitalize = capitalize;
    }

    public boolean isSpellCheck() {
        return spellCheck;
    }

    public void setSpellCheck(boolean spellCheck) {
        this.spellCheck = spellCheck;
    }


    public ArrayList<ItemStack> getMessageItems(){
        ArrayList<ItemStack> items = new ArrayList<>();
        for(ChatMessage item : getMessages()){
            items.add(item.getItem());
        }

        return items;
    }
    public ArrayList<ItemStack> getCommandItems(){
        ArrayList<ItemStack> items = new ArrayList<>();
        for(CommandMessage item : getCommands()){
            items.add(item.getItem());
        }

        return items;
    }



    public Player getPlayer(){
        if(Bukkit.getPlayer(uuid) != null){
            return Bukkit.getPlayer(uuid);
        } else {
            throw new NullPointerException("Player is null for WordPlayer!");

        }

    }

    public static WordPlayer getWordPlayer(UUID uuid){
        for(WordPlayer wordPlayer : WordHistory.getPlugin().getPlayers()){
            if(wordPlayer.getUuid().equals(uuid)){
                return wordPlayer;
            }
        }
        WordHistory.getPlugin().getPlayers().add(new WordPlayer(Bukkit.getPlayer(uuid).getName(), uuid));
        return new WordPlayer(Bukkit.getPlayer(uuid).getName(), uuid);
    }

}
