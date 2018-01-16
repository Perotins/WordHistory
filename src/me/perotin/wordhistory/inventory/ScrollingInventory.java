package me.perotin.wordhistory.inventory;

import me.perotin.wordhistory.files.WordFile;
import me.perotin.wordhistory.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ScrollingInventory {

    //TODO scrolling inv object

    public ArrayList<Inventory> pages;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    private Player clicker;
    public static HashMap<UUID, ScrollingInventory> users = new HashMap<>();
    private ArrayList<ItemStack> items;
    private WordMenuType type;
    private WordFile messages = new WordFile(WordFile.WordFileType.MESSAGES);
    private int pageNumber;



     ScrollingInventory(Player clicker, ArrayList<ItemStack> items, WordMenuType type){
        this.clicker = clicker;
        this.pages = new ArrayList<>();
        this.type = type;
        this.items = items;
        pageNumber = 0;


    }

     void showInventory(){
        //TODO
        Inventory inventory = getBlankPage();
        int slot = 9;
            for(ItemStack item : items){
                // slot is taken, go to the next slot
               if (inventory.getItem(slot) != null) slot++;
                //if(inventory.getItem(slot).getType() != Material.AIR ) slot++;

                if(inventory.contains(item)) continue;

                inventory.setItem(slot, item);
                if(slot == 44 && !isEmpty(slot, inventory)){
                    // new inventory
                    pages.add(inventory);
                    inventory = getBlankPage();
                    slot = 9;
                  if (otherPagesContain(item)) continue;
                  if(!isEmpty(slot, inventory)) slot++;
                  inventory.setItem(slot, item);

                }
            }

        pages.add(inventory);
        users.put(clicker.getUniqueId(), this);
        // open first page
        clicker.openInventory(pages.get(0));



    }

    // helper methods
    private boolean isEmpty(int slot, Inventory inventory){
        return inventory.getItem(slot) == null || inventory.getItem(slot).getType() == Material.AIR;
    }

    private boolean otherPagesContain(ItemStack item){
      for(Inventory page : pages){
          if(page.contains(item)) return true;
      }

      return false;
    }

    private Inventory getBlankPage(){
        Inventory blankPage = null;
        // setting title and dependant items
        switch (type){
            case MESSAGES:
                blankPage = Bukkit.createInventory(null, 54, messages.getString("previous-messages-menu-title"));
                blankPage.setItem(1, ItemUtils.createItem(messages.getString("delete-message-info"), "", Material.REDSTONE_BLOCK));
                blankPage.setItem(0, ItemUtils.createItem(messages.getString("copy-message-info"), "", Material.EMERALD_BLOCK));
                break;

            case COMMANDS:
                blankPage = Bukkit.createInventory(null, 54, messages.getString("previous-commands-menu-title"));
                blankPage.setItem(1, ItemUtils.createItem(messages.getString("delete-command-info"), "", Material.REDSTONE_BLOCK));
                blankPage.setItem(0, ItemUtils.createItem(messages.getString("copy-command-info"), "", Material.EMERALD_BLOCK));
        }


        // arrow head

        ItemStack skullRight = new ItemStack(Material.SKULL_ITEM, (byte) 1, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) skullRight.getItemMeta();
        skullMeta.setOwner("MHF_ArrowRight");
        skullMeta.setDisplayName(messages.getString("next"));
        skullRight.setItemMeta(skullMeta);

        ItemStack skullLeft = new ItemStack(Material.SKULL_ITEM, (byte) 1, (byte) 3);
        SkullMeta leftMeta = (SkullMeta) skullLeft.getItemMeta();
        leftMeta.setDisplayName(messages.getString("go-back"));
        leftMeta.setOwner("MHF_ArrowLeft");
        skullLeft.setItemMeta(leftMeta);

        blankPage.setItem(53, skullRight);
        blankPage.setItem(45, skullLeft);



        return blankPage;
    }

    public enum WordMenuType{
        COMMANDS, MESSAGES
    }


}
