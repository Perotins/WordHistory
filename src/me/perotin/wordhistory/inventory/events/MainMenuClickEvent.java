package me.perotin.wordhistory.inventory.events;

import me.perotin.wordhistory.WordHistory;
import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainMenuClickEvent implements Listener {

    private WordHistory plugin;

    public MainMenuClickEvent(WordHistory plugin){
        this.plugin = plugin;
    }

    private WordFile wordFile;
    @EventHandler
    public void onClick(InventoryClickEvent event){
        wordFile = new WordFile(WordFile.WordFileType.MESSAGES);
        if(event.getWhoClicked() instanceof Player){
            Player clicker = (Player) event.getWhoClicked();
            Inventory inv = event.getInventory();
            WordPlayer wordPlayer = WordPlayer.getWordPlayer(clicker.getUniqueId());
            if(inv.getName().equals(wordFile.getString("main-menu-title"))){
                // in the inventory
                event.setCancelled(true);
                ItemStack clicked = event.getCurrentItem();
                WordMenu wordMenu = new WordMenu(wordPlayer);
                if(clicked != null && clicked.getType() != Material.AIR){
                      switch (clicked.getType()) {
                          case BOOK_AND_QUILL:   wordMenu.showPhrasesMenu();
                          case PAPER: wordMenu.showPreviousCommandsMenu();
                          case INK_SACK: wordMenu.showGrammarMenu();
                          case ANVIL: wordMenu.showPlaceholdersMenu();
                      }
                }
            }
        }
    }
}
