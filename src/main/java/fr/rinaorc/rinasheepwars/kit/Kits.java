package fr.rinaorc.rinasheepwars.kit;

import fr.rinaorc.rinasheepwars.SheepWars;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public enum Kits {

    MORE_LIFE(1, "§dPlus de vie §7(Niveau : §b0§7)", new ItemStack(Material.APPLE, 1), SheepWars.get().contents.getMoreLifeKit()),
    IMPROVED_ARC(2, "§eArc améliorée §7(Niveau : §b0§7)", new ItemStack(Material.BOW, 1), SheepWars.get().contents.getImprovedArcKit()),
    IMPROVED_SWORD(3, "§cEpée améliorée §7(Niveau : §b0§7)", new ItemStack(Material.WOOD_SWORD, 1), SheepWars.get().contents.getImprovedSwordKit()),
    MORE_SHEEP(4, "§aPlus de moutons §7(Niveau : §b0§7)", new ItemStack(Material.WOOL, 1), SheepWars.get().contents.getMoreSheepKit()),
    BUILDER(5, "§5Buildeur §7(Niveau : §b0§7)", new ItemStack(Material.BRICK, 1), SheepWars.get().contents.getBuilderKit()),
    MOBILITY(6, "§bMobilité §7(Niveau : §b0§7)", new ItemStack(Material.LEATHER_BOOTS, 1), SheepWars.get().contents.getMobilityKit()),
    ARMORED_SHEEP(7, "§aMoutons blindés", new ItemStack(Material.OBSIDIAN, 1), SheepWars.get().contents.getArmoredSheepKit());

    private ArrayList<ItemStack> items;
    private int slot;
    private String name;
    private ItemStack icon;

    Kits(int slot, String name, ItemStack icon, ArrayList<ItemStack> items){
        this.slot = slot;
        this.name = name;
        this.icon = icon;
        this.items = items;
    }

    public int getSlot(){
        return slot;
    }

    public String getKitName(){
        return name;
    }

    public ItemStack getIcon(){
        return icon;
    }

    public ItemStack getItem(){
        ItemStack i = icon;
        ItemMeta iM = i.getItemMeta();
        iM.setDisplayName(name);
        i.setItemMeta(iM);
        return i;
    }

    public void add(Player player){

        if(SheepWars.get().kits.containsKey(player)){
            SheepWars.get().kits.remove(player);
        }

        SheepWars.get().kits.put(player, this);
        player.sendMessage("§7§lVous avez choisi : §e"+name);
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }
}