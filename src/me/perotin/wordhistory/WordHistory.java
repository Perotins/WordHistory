package me.perotin.wordhistory;

import org.bukkit.plugin.java.JavaPlugin;

/*
    Created November 11, 2017 by Perotin
 */
public class WordHistory extends JavaPlugin{

    /// Singleton for making WordFile life easier
    private static WordHistory plugin;

    @Override
    public void onEnable(){
        plugin = this;


    }


    public static WordHistory getPlugin (){
        return plugin;
    }

}
