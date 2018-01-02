package me.perotin.wordhistory.commands;

import me.perotin.wordhistory.WordHistory;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WordHistoryCommand implements CommandExecutor {


    private WordHistory plugin;

    public WordHistoryCommand (WordHistory plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            //WordPlayer wordPlayer = WordPlayer.getWordPlayer(player.getUniqueId());
            if(player.hasPermission("wordhistory.use")){
                WordPlayer wordPlayer = new WordPlayer(player.getName(), player.getUniqueId());
                plugin.getPlayers().add(wordPlayer);
                new WordMenu(wordPlayer).showMainMenu();


            } else {
                // no permission
            }


        } else {
            // not a command sender
        }

        return true;
    }
}
