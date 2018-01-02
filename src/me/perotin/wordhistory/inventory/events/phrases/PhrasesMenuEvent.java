package me.perotin.wordhistory.inventory.events.phrases;

import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PhrasesMenuEvent implements Listener {

    private WordFile messages = new WordFile(WordFile.WordFileType.MESSAGES);

    private HashSet<UUID> phrasesAdder = new HashSet<>();
    private HashMap<String, String> newPhrase = new HashMap<>();
    private String shortCut = " ";

    @EventHandler
    public void onPhrasesClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player) {
            Player clicker = (Player) event.getWhoClicked();
            Inventory clicked = event.getClickedInventory();
            ItemStack item = event.getCurrentItem();
            WordPlayer player = WordPlayer.getWordPlayer(clicker.getUniqueId());

            if(clicked.getName().equals(messages.getString("phrases-menu-title"))){
                event.setCancelled(true);
                if(item != null && item.getType() != Material.AIR){
                    if(item.getType() == Material.EMERALD_BLOCK && item.getItemMeta().getDisplayName().equals(messages.getString("add-phrase-name"))){
                        // add a phrase
                        phrasesAdder.add(clicker.getUniqueId());
                        clicker.closeInventory();
                        for(int x = 0; x <= 20; x++){
                            clicker.sendMessage(" ");
                        }
                        clicker.sendMessage(messages.getString("type-shortcut-key"));
                    }

                    if(item.getType() == Material.PAPER && event.getClick() == ClickType.RIGHT){
                        if(item.getItemMeta() != null && item.getItemMeta().getDisplayName() != null && item.getItemMeta().getLore() != null){
                            String[] keyRaw =item.getItemMeta().getDisplayName().split(" ");
                            int length = keyRaw.length;
                            String key = " ";
                            for (int x = 0; x <= length; x++){
                                if( x == length - 1){
                                    break;
                                }
                                key += keyRaw[x] + " ";
                            }
                            String finalKey = ChatColor.stripColor(key.trim());


                            player.getPhrases().remove(finalKey);

                            new WordMenu(player).showPhrasesMenu();
                        }
                    }

                }
            }
        }
    }


    @EventHandler
    public void onAddPhrase(AsyncPlayerChatEvent event){
        Player chatter = event.getPlayer();
        String msg = event.getMessage();
        WordPlayer player = WordPlayer.getWordPlayer(chatter.getUniqueId());
        WordMenu menu = new WordMenu(player);
        if(phrasesAdder.contains(chatter.getUniqueId())){
            event.setCancelled(true);
            if(shortCut.equals(" ")){
                // inputting shortcut
                shortCut = msg;
                for(int x = 0; x <= 10; x++){
                    chatter.sendMessage(" ");
                }
                String[] typePhrase = messages.getString("type-phrase").split("/");
                for(String s : typePhrase){

                    chatter.sendMessage(s);
                    chatter.sendMessage(" ");
                }
            } else {
                // inputting phrase

                 player.addPhrase(shortCut, msg);
                 menu.showPhrasesMenu();
                 shortCut = " ";
                 phrasesAdder.remove(chatter.getUniqueId());





            }


        }
    }
}
