package me.perotin.wordhistory.files;

import me.perotin.wordhistory.WordHistory;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static me.perotin.wordhistory.files.WordFile.WordFileType.MESSAGES;
import static me.perotin.wordhistory.files.WordFile.WordFileType.PLAYERS;

public class WordFile {

    public enum WordFileType {

        PLAYERS, MESSAGES,

    }

    private File file;
    private FileConfiguration configuration;

    public WordFile(WordFileType type){
        // hard-coding some stuff to make our lives easier, since we'll be using
        // files A LOT so it is important we have a good system set in place to easily retreive these things

        if(type == MESSAGES){
            file = new File(WordHistory.getPlugin().getDataFolder(), "messages.yml");
            configuration = YamlConfiguration.loadConfiguration(file);
        }
        if(type == PLAYERS){
            file = new File(WordHistory.getPlugin().getDataFolder(), "players.yml");
            configuration = YamlConfiguration.loadConfiguration(file);
        }





    }

    // some generic methods to speed up the process

    public void save(){
        try{
            configuration.save(file);
        } catch (IOException ex){
            ex.printStackTrace();;
        }
    }


    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public Object get(String path){
        return configuration.get(path);
    }

    public void set(String path, Object value){
        configuration.set(path, value);
    }



}
