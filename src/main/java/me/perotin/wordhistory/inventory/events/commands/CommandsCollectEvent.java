package me.perotin.wordhistory.inventory.events.commands;

import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandsCollectEvent implements Listener {

    @EventHandler
    public void on(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        WordPlayer wordPlayer = WordPlayer.getWordPlayer(player.getUniqueId());
        String command = event.getMessage();
        String date = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy zzz").format(new Date());

        wordPlayer.addCommand(command, date);

    }
}
