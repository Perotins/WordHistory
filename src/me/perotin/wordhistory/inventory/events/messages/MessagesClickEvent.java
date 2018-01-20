package me.perotin.wordhistory.inventory.events.messages;

import me.perotin.wordhistory.ChatMessage;
import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.inventory.WordMenu;
import me.perotin.wordhistory.players.WordPlayer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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
                if(item.getType() == Material.PAPER){
                    if(event.getClick() == ClickType.RIGHT) {
                        // deleting a message
                        String message = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                        String date = ChatColor.stripColor(item.getItemMeta().getLore().get(0));
                        // Bukkit.broadcastMessage(date);

                        ChatMessage remove = new ChatMessage(message, date);
                        for (ChatMessage chatMessage : wordPlayer.getMessages()) {
                            if (chatMessage.equals(remove)) {
                                wordPlayer.getMessages().remove(chatMessage);
                            }
                        }


                        WordMenu menu = new WordMenu(wordPlayer);

                        menu.showPreviousMessagesMenu();
                    } else if (event.getClick() == ClickType.LEFT) {
                        TextComponent message = new TextComponent(messages.getString("click-to-copy"));
                        // make message configurable
                        message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ChatColor.stripColor(item.getItemMeta().getDisplayName())));
                        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(item.getItemMeta().getDisplayName()).create()));
                        player.closeInventory();
                        player.sendMessage(" ");
                        player.spigot().sendMessage(message);


                    }
                }
            }
        }
    }
}
