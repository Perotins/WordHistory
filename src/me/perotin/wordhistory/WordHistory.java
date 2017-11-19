package me.perotin.wordhistory;

import me.perotin.wordhistory.commands.WordHistoryCommand;
import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.events.MainMenuClickEvent;
import me.perotin.wordhistory.players.WordPlayer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

/*
    Created November 11, 2017 by Perotin
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
    }


    public static WordHistory getPlugin (){
        return plugin;
    }

    public HashSet<WordPlayer> getPlayers() {
        return players;
    }
}
