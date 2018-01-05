package me.perotin.wordhistory;

import me.perotin.wordhistory.commands.WordHistoryCommand;
import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.events.MainMenuClickEvent;
import me.perotin.wordhistory.inventory.events.commands.CommandsClickEvent;
import me.perotin.wordhistory.inventory.events.commands.CommandsCollectEvent;
import me.perotin.wordhistory.inventory.events.grammar.GrammarClickEvent;
import me.perotin.wordhistory.inventory.events.messages.CollectMessagesEvent;
import me.perotin.wordhistory.inventory.events.messages.MessagesClickEvent;
import me.perotin.wordhistory.inventory.events.phrases.PhrasesMenuEvent;
import me.perotin.wordhistory.players.WordPlayer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

/*
    Created November 11, 2017 by Perotin

    TODO
    1. Finished making scrolling obj, now implement it with messages and commands object
 */
public class WordHistory extends JavaPlugin{

    /// Singleton for making WordFile life easier
    private static WordHistory plugin;

    private HashSet<WordPlayer> players;

    @Override
    public void onEnable(){
        plugin = this;
        players = new HashSet<>();
        WordFile.loadFiles();

        getCommand("wordhistory").setExecutor(new WordHistoryCommand(this));

        Bukkit.getPluginManager().registerEvents(new MainMenuClickEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new GrammarClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PhrasesMenuEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MessagesClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CollectMessagesEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CommandsClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CommandsCollectEvent(), this);

    }


    public static WordHistory getPlugin (){
        return plugin;
    }

    public HashSet<WordPlayer> getPlayers() {
        return players;
    }
}
