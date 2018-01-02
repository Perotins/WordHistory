package me.perotin.wordhistory.inventory.events.messages;

import me.perotin.wordhistory.ChatMessage;
import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MessagesClickEvent implements Listener {

   private WordFile messages = new WordFile(WordFile.WordFileType.MESSAGES);

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getClickedInventory();
            WordPlayer wordPlayer = WordPlayer.getWordPlayer(player.getUniqueId());

            if(inventory.getName().equals(messages.getString("previous-messages-menu-title"))){
                //TODO handle events
                ItemStack item = event.getCurrentItem();
                event.setCancelled(true);
                if(event.getClick() == ClickType.RIGHT && item.getType() == Material.PAPER){
                    // deleting a message
                    String message = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                    String date = ChatColor.stripColor(item.getItemMeta().getLore().get(0));
                   // Bukkit.broadcastMessage(date);

                    ChatMessage remove = new ChatMessage(message, date);
                    for(ChatMessage chatMessage : wordPlayer.getMessages()){
                        if(chatMessage.equals(remove)){
                            wordPlayer.getMessages().remove(chatMessage);
                        }
                    }


                    WordMenu menu = new WordMenu(wordPlayer);

                    menu.showPreviousMessagesMenu();
                }
            }
        }
    }
}
