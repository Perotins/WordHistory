package me.perotin.wordhistory.inventory;

import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.players.WordPlayer;
import me.perotin.wordhistory.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class WordMenu {


    private WordPlayer player;
    private ArrayList<Inventory> pages;
    private int pageNumber;
    private WordFile wordFile;

    public WordMenu(WordPlayer player){
        this.player = player;
        this.pages = new ArrayList<>();
        this.pageNumber = 0;
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
        menu.setItem(16, ItemUtils.createItem(wordFile.getString("placeholders-display-name"),
                wordFile.getString("click-to-expand"), Material.ANVIL));

        player.getPlayer().openInventory(menu);

    }

    public void showPhrasesMenu(){
        //TODO
    }

    public void showGrammarMenu(){
        //TODO
    }

    public void showPreviousCommandsMenu(){
        //TODO
    }

    public void showPlaceholdersMenu(){
        //TODO
    }






}
