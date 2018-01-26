package me.perotin.wordhistory.utils;

import me.perotin.wordhistory.files.WordFile;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
/*
    Util class for creating items easily
 */
public class ItemUtils {


    public static ItemStack createItem(String name, String lore, Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItem(String name, Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack createItemWithBigLore(String name, ArrayList<String> lores, Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack toggleBlock(ItemStack toToggle){
        WordFile messages = new WordFile(WordFile.WordFileType.MESSAGES);
        if(toToggle.getType() == Material.EMERALD_BLOCK) {
            ItemStack redstone = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta redMeta = redstone.getItemMeta();
            redMeta.setDisplayName(toToggle.getItemMeta().getDisplayName().replace(messages.getString("enabled"), messages.getString("disabled")));
            redMeta.setLore(toToggle.getItemMeta().getLore());
            redstone.setItemMeta(redMeta);
            return redstone;
        } else if (toToggle.getType() == Material.REDSTONE_BLOCK){
            ItemStack emerald = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta greenMeta = emerald.getItemMeta();
            greenMeta.setDisplayName(toToggle.getItemMeta().getDisplayName().replace(messages.getString("disabled"), messages.getString("enabled")));
            greenMeta.setLore(toToggle.getItemMeta().getLore());
            emerald.setItemMeta(greenMeta);
            return emerald;
        }

        return null;
    }

    public static ItemStack getBackItem(){
        WordFile messages = new WordFile(WordFile.WordFileType.MESSAGES);

        return createItem(messages.getString("go-back"), messages.getString("go-back-lore"), Material.FEATHER);
    }
}
