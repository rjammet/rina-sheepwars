package fr.rinaorc.rinasheepwars.kit;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Contents {

    private ItemStack get(Material mat, int amount, byte data, String displayName, Enchantment ench, int enchLevel) {
        ItemStack i = new ItemStack(mat,amount,data);
        ItemMeta iM = i.getItemMeta();
        iM.setDisplayName(displayName);
        if(ench != null){
            iM.addEnchant(ench, enchLevel, true);
        }
        i.setItemMeta(iM);
        return i;
    }

    public ArrayList<ItemStack> getMoreLifeKit(){
        ArrayList<ItemStack> i = new ArrayList<>();
        i.add(new ItemStack(Material.BOW));
        i.add(new ItemStack(Material.FEATHER));
        i.add(new ItemStack(Material.WOOD_SWORD));
        return i;
    }

    public ArrayList<ItemStack> getImprovedArcKit(){
        ArrayList<ItemStack> i = new ArrayList<>();
        i.add(new ItemStack(Material.BOW));
        i.add(new ItemStack(Material.FEATHER));
        i.add(new ItemStack(Material.WOOD_SWORD));
        return i;
    }

    public ArrayList<ItemStack> getImprovedSwordKit(){
        ArrayList<ItemStack> i = new ArrayList<>();
        i.add(new ItemStack(Material.BOW));
        i.add(new ItemStack(Material.FEATHER));
        i.add(new ItemStack(Material.WOOD_SWORD));
        return i;
    }

    public ArrayList<ItemStack> getMoreSheepKit(){
        ArrayList<ItemStack> i = new ArrayList<>();
        i.add(new ItemStack(Material.BOW));
        i.add(new ItemStack(Material.FEATHER));
        i.add(new ItemStack(Material.WOOD_SWORD));
        return i;
    }

    public ArrayList<ItemStack> getBuilderKit(){
        ArrayList<ItemStack> i = new ArrayList<>();
        i.add(new ItemStack(Material.BOW));
        i.add(new ItemStack(Material.FEATHER));
        i.add(new ItemStack(Material.WOOD_SWORD));
        return i;
    }
    public ArrayList<ItemStack> getMobilityKit(){
        ArrayList<ItemStack> i = new ArrayList<>();
        i.add(new ItemStack(Material.BOW));
        i.add(new ItemStack(Material.FEATHER));
        i.add(new ItemStack(Material.WOOD_SWORD));
        return i;
    }

    public ArrayList<ItemStack> getArmoredSheepKit(){
        ArrayList<ItemStack> i = new ArrayList<>();
        i.add(new ItemStack(Material.BOW));
        i.add(new ItemStack(Material.FEATHER));
        i.add(new ItemStack(Material.WOOD_SWORD));
        return i;
    }
}
