package me.perotin.wordhistory.commands;

import me.perotin.wordhistory.ChatMessage;
import me.perotin.wordhistory.CommandMessage;
import me.perotin.wordhistory.WordHistory;
import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class WordHistoryCommand implements CommandExecutor {


    private WordHistory plugin;
    private WordFile messages = new WordFile(WordFile.WordFileType.MESSAGES);

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
                ArrayList<ItemStack> messages = new ArrayList<>();
                ArrayList<ItemStack> commands = new ArrayList<>();

                // getting items and storing in a list
                for(ChatMessage message : wordPlayer.getMessages()){
                    messages.add(message.getItem());
                }

                for(CommandMessage commandMessage : wordPlayer.getCommands()){
                    commands.add(commandMessage.getItem());
                }

                new WordMenu(wordPlayer).showMainMenu();



            } else {
                // no permission
                player.sendMessage(messages.getString("no-permission"));
                return true;
            }


        } else {
            // not a command sender
            commandSender.sendMessage(messages.getString("player-only"));
        }

        return true;
    }
}
