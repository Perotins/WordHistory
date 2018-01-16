package me.perotin.wordhistory.inventory.events;

import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.ScrollingInventory;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryPagingEvent implements Listener {

    private WordFile messages = new WordFile(WordFile.WordFileType.MESSAGES);

    @EventHandler
    public void onPageEvent(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if(event.getWhoClicked() instanceof Player){
            Player clicker = (Player) event.getWhoClicked();
            if(inventory.getName().equals(messages.getString("previous-commands-display-name")) ||
                    inventory.getName().equals(messages.getString("previous-messages-display-name"))){
                // in one of our menus
                WordPlayer wordPlayer = WordPlayer.getWordPlayer(clicker.getUniqueId());
                WordMenu wordMenu = new WordMenu(wordPlayer);
                ItemStack clickedItem = event.getCurrentItem();
                if(clickedItem.getType() == Material.SKULL_ITEM){
                    // arrow
                    if(clickedItem.getItemMeta().getDisplayName().equals("next")){
                        // next page
                        if(inventory.getName().equals(messages.getString("previous-commands-display-name"))) {
                            if(ScrollingInventory.users.containsKey(clicker.getUniqueId())) {
                                ScrollingInventory scrollingInventory = ScrollingInventory.users.get(clicker.getUniqueId());
                                if(scrollingInventory.getPageNumber() == scrollingInventory.pages.indexOf(scrollingInventory)) {
                                    // synced up
                                    if(scrollingInventory.pages.size() > scrollingInventory.getPageNumber()){
                                        // pages left
                                        scrollingInventory.setPageNumber(scrollingInventory.getPageNumber() + 1);
                                        clicker.openInventory(scrollingInventory.pages.get(scrollingInventory.getPageNumber()));


                                    }

                                }
                            }

                        }
                    }
                }

            }



        }


    }
}
