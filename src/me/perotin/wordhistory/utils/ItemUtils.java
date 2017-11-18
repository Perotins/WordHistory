package me.perotin.wordhistory.utils;

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
        return itemStack;
    }

    public static ItemStack createItemWithBigLore(String name, ArrayList<String> lores, Material material) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lores);
        return itemStack;
    }
}
