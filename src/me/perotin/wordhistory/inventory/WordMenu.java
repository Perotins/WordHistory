package me.perotin.wordhistory.inventory;

import me.perotin.wordhistory.players.WordPlayer;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class WordMenu {


    private WordPlayer player;
    private ArrayList<Inventory> pages;
    private int pageNumber;


    public WordMenu(WordPlayer player){
        this.player = player;
        this.pages = new ArrayList<>();
        this.pageNumber = 0;
    }




}
