package me.perotin.wordhistory.inventory.events.grammar.punctuation;

import me.perotin.wordhistory.WordHistory;
import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PunctuationEvents implements Listener {

   private WordFile wordFile = new WordFile(WordFile.WordFileType.MESSAGES);
   private WordHistory plugin;

   public PunctuationEvents(WordHistory plugin){
       this.plugin = plugin;
   }


    @EventHandler
    public void onChat (AsyncPlayerChatEvent event){
        String message = event.getMessage();
        Player chatter = event.getPlayer();
        WordPlayer wordPlayer = WordPlayer.getWordPlayer(chatter.getUniqueId());
        if(wordPlayer.isPunctuation()){
            // enabled punctuation

            // if they put their own punctuation then just ignore it
            if(message.endsWith(".") || message.endsWith("?") || message.endsWith("!")) return;

            // no punctuation is here, time for our magic

            for(String word : plugin.getConfig().getStringList("question-words")){
                if(message.toLowerCase().contains(word.toLowerCase())){
                    // its a question!!
                    event.setMessage(message + "?");
                    return;
                }
            }

            // exclamation next but lowkey tired so gonna watch the office or something.

        }
    }
}
