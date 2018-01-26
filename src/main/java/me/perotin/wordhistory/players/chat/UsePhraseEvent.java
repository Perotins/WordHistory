package me.perotin.wordhistory.players.chat;

import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class UsePhraseEvent implements Listener {

    @EventHandler
    public void onUsePhrase(AsyncPlayerChatEvent event) {
        String possiblePhrase = event.getMessage();
        Player phraser = event.getPlayer();
        WordPlayer wordPlayer = WordPlayer.getWordPlayer(phraser.getUniqueId());
        int placeholderAmount = 0;


        int placeholdersInserted = 0;
        if (possiblePhrase.contains(":")) {
            // in the realm of potentially using a phrase
            if (!wordPlayer.getPhrases().isEmpty()) {
                for (String key : wordPlayer.getPhrases().keySet()) {
                    if (possiblePhrase.toLowerCase().contains(key.toLowerCase().split(":")[0] + ":")) {
                        // definitely a phrase
                        // check for placeholders
                        String value = wordPlayer.getPhrases().get(key);
                        if (value.contains("%")) {
                            for(Character x : value.toCharArray()){
                                if(x == '%') placeholderAmount++;
                            }
                            // debugging, ignore messiness

                            //TODO
                            // make it work with more than 1 placeholder
                            if(possiblePhrase.split(":").length == 2) {
                                String placeholders = possiblePhrase.split(":")[1].trim();
                                for(Character placeholder : value.toCharArray()) {

                                    if(placeholder.toString().equals("%")) {

                                        for (String x : placeholders.split(" ")) {
                                            if (placeholdersInserted <= placeholderAmount) {
                                                // still placeholders left
                                                // next line could potentially cause bugs
                                                if(value.contains(x)) continue;
                                                value = value.replaceFirst(placeholder.toString(), x);
                                                placeholdersInserted++;
                                            }

                                        }
                                    }
                                }

                                event.setMessage(value);
                            } else {
                                // incorrect syntax so assume it isn't a phrase they were using
                                // maybe send inquisitive message if they were trying to use a phrase
                                return;
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
