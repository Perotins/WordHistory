package me.perotin.wordhistory.inventory.events.grammar;

import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import me.perotin.wordhistory.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static me.perotin.wordhistory.files.WordFile.WordFileType.MESSAGES;

public class GrammarClickEvent implements Listener{


    private WordFile messages = new WordFile(MESSAGES);
    @EventHandler
    public void onGrammar(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player clicker = (Player) event.getWhoClicked();
            Inventory inventory = event.getClickedInventory();
            if(inventory.getName().equals(messages.getString("grammar-menu-title"))){
                // in the menu
                event.setCancelled(true);
                WordPlayer player = WordPlayer.getWordPlayer(clicker.getUniqueId());
                ItemStack clicked = event.getCurrentItem();
                if(clicked != null && clicked.getType() != Material.AIR){
                    String name = clicked.getItemMeta().getDisplayName();

                    if(name.equals(messages.getString("go-back")) && clicked.getType() == Material.FEATHER){
                        // main menu
                        WordMenu menu = new WordMenu(player);
                        menu.showMainMenu();
                    }

                     if (name.equals(messages.getString("punctuation-display").replace("$misc$", messages.getString("enabled"))) ||
                             name.equals(messages.getString("punctuation-display").replace("$misc$", messages.getString("disabled")))){
                        if(player.isPunctuation()) {
                            player.setPuncuation(false);
                        } else {
                            player.setPuncuation(true);
                        }
                        inventory.setItem(10, ItemUtils.toggleBlock(clicked));
                        clicker.openInventory(inventory);

                     }
                    if (name.equals(messages.getString("commas-display").replace("$misc$", messages.getString("enabled"))) ||
                            name.equals(messages.getString("commas-display").replace("$misc$", messages.getString("disabled")))){
                        if(player.isCommas()) {
                            player.setCommas(false);
                        } else {
                            player.setCommas(true);
                        }
                        inventory.setItem(12, ItemUtils.toggleBlock(clicked));
                        clicker.openInventory(inventory);

                    }
                    if (name.equals(messages.getString("capitalize-display").replace("$misc$", messages.getString("enabled"))) ||
                            name.equals(messages.getString("capitalize-display").replace("$misc$", messages.getString("disabled")))){
                        if(player.isCapitalize()) {
                            player.setCapitalize(false);
                        } else {
                            player.setCapitalize(true);
                        }
                        inventory.setItem(14, ItemUtils.toggleBlock(clicked));
                        clicker.openInventory(inventory);

                    }
                    if (name.equals(messages.getString("spellcheck-display").replace("$misc$", messages.getString("enabled"))) ||
                            name.equals(messages.getString("spellcheck-display").replace("$misc$", messages.getString("disabled")))){
                        if(player.isSpellCheck()) {
                            player.setSpellCheck(false);
                        } else {
                            player.setSpellCheck(true);
                        }
                        inventory.setItem(16, ItemUtils.toggleBlock(clicked));
                        clicker.openInventory(inventory);

                    }

                }
            }
        }
    }


}
