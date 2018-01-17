package me.perotin.wordhistory.inventory.events;

import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.ScrollingInventory;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.Bukkit;
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
        if (event.getWhoClicked() instanceof Player) {
            Player clicker = (Player) event.getWhoClicked();
            if (inventory.getName().equals(messages.getString("previous-commands-menu-title")) ||
                    inventory.getName().equals(messages.getString("previous-messages-menu-title"))) {
                // in one of our menus
                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem.getType() == Material.SKULL_ITEM) {
                    // arrow
                    if (clickedItem.getItemMeta().getDisplayName().equals(messages.getString("next"))) {
                        // next page
                        if (ScrollingInventory.users.containsKey(clicker.getUniqueId())) {
                            ScrollingInventory scrollingInventory = ScrollingInventory.users.get(clicker.getUniqueId());
                            // synced up
                            if (scrollingInventory.pages.size() > scrollingInventory.getPageNumber()) {
                                // pages left

                                scrollingInventory.setPageNumber(scrollingInventory.getPageNumber() + 1);
                                clicker.openInventory(scrollingInventory.pages.get(scrollingInventory.getPageNumber()));


                            }


                        }


                    }
                    if (clickedItem.getItemMeta().getDisplayName().equals(messages.getString("go-back"))) {
                        // next page
                        if (ScrollingInventory.users.containsKey(clicker.getUniqueId())) {

                            ScrollingInventory scrollingInventory = ScrollingInventory.users.get(clicker.getUniqueId());
                            // synced up
                            if ( scrollingInventory.getPageNumber() > 0) {
                                // go back a page

                                scrollingInventory.setPageNumber(scrollingInventory.getPageNumber() - 1);
                                clicker.openInventory(scrollingInventory.pages.get(scrollingInventory.getPageNumber()));


                            }
                            if(scrollingInventory.getPageNumber() == 0){
                                // go to main page if they click "go back" on first page
                                new WordMenu(WordPlayer.getWordPlayer(clicker.getUniqueId())).showMainMenu();
                            }


                        }


                    }

                }

            }


        }


    }
}
