package me.perotin.wordhistory.inventory;


import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.players.WordPlayer;
import me.perotin.wordhistory.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;


public class WordMenu {


    private WordPlayer player;
    private ScrollingInventory messages;
    private ScrollingInventory commands;
    private WordFile wordFile;

    public WordMenu(WordPlayer player){
        this.player = player;
        this.messages = new ScrollingInventory(player.getPlayer(), player.getMessageItems(), ScrollingInventory.WordMenuType.MESSAGES);
        this.commands = new ScrollingInventory(player.getPlayer(), player.getCommandItems(), ScrollingInventory.WordMenuType.COMMANDS);


        this.wordFile = new WordFile(WordFile.WordFileType.MESSAGES);

    }

    public void showMainMenu(){
        Inventory menu = Bukkit.createInventory(null, 27, wordFile.getString("main-menu-title"));

        // phrases & shortcuts item
        menu.setItem(10, ItemUtils.createItem(wordFile.getString("phrases-display-name"), wordFile.getString("click-to-expand"),
                Material.BOOK_AND_QUILL));

        // previous commands & messages
        menu.setItem(12, ItemUtils.createItem(wordFile.getString("previous-commands-display-name"), wordFile.getString("click-to-expand"),
                Material.PAPER));
        // grammar item
        menu.setItem(14, ItemUtils.createItem(wordFile.getString("grammar-check-display-name"), wordFile.getString("click-to-expand"),
                Material.INK_SACK));

        // placeholders
        menu.setItem(16, ItemUtils.createItem(wordFile.getString("previous-messages-display-name"),
                wordFile.getString("click-to-expand"), Material.BOOK));

        player.getPlayer().openInventory(menu);

    }

    public void showPhrasesMenu(){
        Inventory inventory = Bukkit.createInventory(null, 54, wordFile.getString("phrases-menu-title"));
        inventory.setItem(0, ItemUtils.createItem(wordFile.getString("add-phrase-name"), wordFile.getString("add-phrase-lore")
        , Material.EMERALD_BLOCK) );

        inventory.setItem(1, ItemUtils.createItem(wordFile.getString("delete-phrase-name"),
                wordFile.getString("delete-phrase-lore"),
                Material.REDSTONE_BLOCK));

        int slot = 9;
        if(!player.getPhrases().isEmpty()) {
            for (String key : player.getPhrases().keySet()) {
                inventory.setItem(slot, ItemUtils.createItem(ChatColor.YELLOW + key + " -> ", ChatColor.GRAY + player.getPhrases().get(key),
                        Material.PAPER));
                slot++;
            }
        }
        player.getPlayer().openInventory(inventory);
    }

    public void showGrammarMenu(){
        //TODO
        // add punctuation
        // add commas
        // capitalize propper words
        // suggest better words

        //TODO
        // replacement for toggling

        Inventory inventory = Bukkit.createInventory(null, 27, wordFile.getString("grammar-menu-title"));
        if(player.isPunctuation()) {

            inventory.setItem(10, ItemUtils.createItem(wordFile.getString("punctuation-display").replace("$misc$", wordFile.getString("enabled")), wordFile.getString("toggle-punctuation"),
                    Material.EMERALD_BLOCK));
        } else {
            inventory.setItem(10, ItemUtils.createItem(wordFile.getString("punctuation-display").replace("$misc$", wordFile.getString("disabled")), wordFile.getString("toggle-punctuation"),
                    Material.REDSTONE_BLOCK));
        }

        if(player.isCommas()){
            inventory.setItem(12, ItemUtils.createItem(wordFile.getString("commas-display").replace("$misc$", wordFile.getString("enabled")),
                    wordFile.getString("toggle-commas"), Material.EMERALD_BLOCK));
        } else {
            inventory.setItem(12, ItemUtils.createItem(wordFile.getString("commas-display").replace("$misc$", wordFile.getString("disabled")),
                    wordFile.getString("toggle-commas"), Material.REDSTONE_BLOCK));
        }

        if(player.isCapitalize()) {
            inventory.setItem(14, ItemUtils.createItem(wordFile.getString("capitalize-display").replace("$misc$", wordFile.getString("enabled")),
                    wordFile.getString("toggle-capitalize"), Material.EMERALD_BLOCK));
        } else {
            inventory.setItem(14, ItemUtils.createItem(wordFile.getString("capitalize-display").replace("$misc$", wordFile.getString("disabled")),
                    wordFile.getString("toggle-capitalize"), Material.EMERALD_BLOCK));
        }

        if(player.isWordReplacement()) {
            inventory.setItem(16, ItemUtils.createItem(wordFile.getString("replacement-display").replace("$misc$", wordFile.getString("enabled")), wordFile.getString("toggle-replacement"),
                    Material.EMERALD_BLOCK));
        } else {
            inventory.setItem(16, ItemUtils.createItem(wordFile.getString("replacement-display").replace("$misc$", wordFile.getString("disabled")), wordFile.getString("toggle-replacement"),
                    Material.EMERALD_BLOCK));
        }
        inventory.setItem(18, ItemUtils.getBackItem());
        player.getPlayer().openInventory(inventory);

    }

    public void showPreviousCommandsMenu(){
      commands.showInventory();
    }

    public void showPreviousMessagesMenu(){
        messages.showInventory();
    }


    public ScrollingInventory getMessages() {
        return messages;
    }

    public ScrollingInventory getCommands() {
        return commands;
    }
}
