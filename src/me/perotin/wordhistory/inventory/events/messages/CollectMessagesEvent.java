package me.perotin.wordhistory.inventory.events.messages;

import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CollectMessagesEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player chatter = event.getPlayer();
        String msg = event.getMessage();
        WordPlayer player = WordPlayer.getWordPlayer(chatter.getUniqueId());


        String date = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy zzz").format(new Date());

        player.addMessage(msg, date);

    }
}
