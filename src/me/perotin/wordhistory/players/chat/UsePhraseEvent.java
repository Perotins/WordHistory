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

        if (possiblePhrase.contains(":")) {
            // in the realm of potentially using a phrase
            if (!wordPlayer.getPhrases().isEmpty()) {
                for (String key : wordPlayer.getPhrases().keySet()) {
                    if (possiblePhrase.toLowerCase().contains(key.toLowerCase().split(":")[0] + ":")) {
                        // definitely a phrase
                        // check for placeholders
                        String value = wordPlayer.getPhrases().get(key);
                        if (value.contains("%")) {

                            //TODO
                            // make it work with more than 1 placeholder
                            if(possiblePhrase.split(":").length == 2) {
                                String placeholders = possiblePhrase.split(":")[1];
                                for(String x : placeholders.split(":")){
                                    value = value.replace("%", x);
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
