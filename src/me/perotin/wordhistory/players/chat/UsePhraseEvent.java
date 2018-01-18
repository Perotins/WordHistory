package me.perotin.wordhistory.players.chat;

import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class UsePhraseEvent implements Listener {

    @EventHandler
    public void onUsePhrase(AsyncPlayerChatEvent event){
        String possiblePhrase = event.getMessage();
        Player phraser = event.getPlayer();
        WordPlayer wordPlayer = WordPlayer.getWordPlayer(phraser.getUniqueId());

        if(possiblePhrase.contains(":")){
            // in the realm of potentially using a phrase
            if(!wordPlayer.getPhrases().isEmpty()) {
                for (String key : wordPlayer.getPhrases().keySet()) {
                    if(possiblePhrase.toLowerCase().contains(key.toLowerCase().split(":")[0] + ":")){
                        // definitely a phrase
                        // check for placeholders
                        String value = wordPlayer.getPhrases().get(key);

                        //TODO
                        // redo below as logic is wrong, value contains the placeholders not the key!!

                        if(possiblePhrase.split(":")[1].contains("%")){
                            // placeholders in place
                            ArrayList<Integer> placeholderIndexes = new ArrayList<>();

                            for(int x = 0; x <= key.split(":")[1].length(); x++){
                                char character = key.split(":")[1].charAt(x);
                                if(character == '%'){
                                    // gather indexes to use later for replacement
                                    placeholderIndexes.add(key.split(":")[1].indexOf(character));
                                }
                            }


                        } else {
                            // no placeholders, just grab value and send it as them
                            event.setMessage(wordPlayer.getPhrases().get(key));
                        }
                    }
                }
            }
        }

    }
}
