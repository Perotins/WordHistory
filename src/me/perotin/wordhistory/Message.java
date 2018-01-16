package me.perotin.wordhistory;

import me.perotin.wordhistory.utils.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Message {

    private String msg;
    private String date;

    public Message(String msg, String date){
        this.msg = msg;
        this.date = date;
    }


     String getMessage(){
        return this.msg;
    }

     String getDate(){
        return this.date;
    }


    public boolean equals(Message message){
        return msg.equals(message.getMessage()) && date.equals(message.getDate());
    }

    public ItemStack getItem(){
        return ItemUtils.createItem(ChatColor.YELLOW + msg, ChatColor.GRAY + date, Material.PAPER);
    }


}
