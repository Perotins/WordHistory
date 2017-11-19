package me.perotin.wordhistory.files;

import me.perotin.wordhistory.WordHistory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

import static me.perotin.wordhistory.files.WordFile.WordFileType.MESSAGES;
import static me.perotin.wordhistory.files.WordFile.WordFileType.PLAYERS;

public class WordFile {

    private File file;
    private FileConfiguration configuration;
    private WordFileType type;

    public WordFile(WordFileType type) {
        // hard-coding some stuff to make our lives easier, since we'll be using
        // files A LOT so it is important we have a good system set in place to easily retreive these things

        if (type == MESSAGES) {
            file = new File(WordHistory.getPlugin().getDataFolder(), "messages.yml");
            configuration = YamlConfiguration.loadConfiguration(file);
            type = MESSAGES;
        }
        if (type == PLAYERS) {
            file = new File(WordHistory.getPlugin().getDataFolder(), "players.yml");
            configuration = YamlConfiguration.loadConfiguration(file);
            type = PLAYERS;
        }


    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
            ;
        }
    }

    // some generic methods to speed up the process

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public Object get(String path) {
        return configuration.get(path);
    }

    public void set(String path, Object value) {
        configuration.set(path, value);
    }

    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', configuration.getString(path));
    }

    public void load() {

        File lang = null;
        InputStream defLangStream = null;

        switch (type) {
            case MESSAGES:
                lang = new File(WordHistory.getPlugin().getDataFolder(), "messages.yml");
                defLangStream = WordHistory.getPlugin().getResource("messages.yml");
                break;
            case PLAYERS:
                lang = new File(WordHistory.getPlugin().getDataFolder(), "players.yml");
                defLangStream = WordHistory.getPlugin().getResource("players.yml");
                break;

        }
        OutputStream out = null;
        if (!lang.exists()) {
            try {
                WordHistory.getPlugin().getDataFolder().mkdir();
                lang.createNewFile();
                if (defLangStream != null) {
                    out = new FileOutputStream(lang);
                    int read;
                    byte[] bytes = new byte[1024];

                    while ((read = defLangStream.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace(); // So they notice
                Bukkit.getLogger().severe("[WordHistory] Couldn't create " + type.toString().toLowerCase() + " file.");
                Bukkit.getLogger().severe("[WordHistory] This is a fatal error. Now disabling");
                WordHistory.getPlugin().getPluginLoader().disablePlugin(WordHistory.getPlugin()); // Without
                // it
                // loaded,
                // we
                // can't
                // send
                // them
                // messages
            } finally {
                if (defLangStream != null) {
                    try {
                        defLangStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public static void loadFiles(){
        for(WordFileType type : WordFileType.values()){
            new WordFile(type).load();
        }
    }


    public enum WordFileType {

        PLAYERS, MESSAGES,

    }



}
