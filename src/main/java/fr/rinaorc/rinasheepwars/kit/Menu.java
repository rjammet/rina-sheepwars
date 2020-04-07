package fr.rinaorc.rinasheepwars.kit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements Listener {

    private Inventory inv;
    private String inv_name = "§7§lSelection du Kit";

    public Menu(){
        this.inv = Bukkit.createInventory(null, 9, inv_name);
        this.inv.setItem(Kits.MORE_LIFE.getSlot(), Kits.MORE_LIFE.getItem());
        this.inv.setItem(Kits.IMPROVED_ARC.getSlot(), Kits.IMPROVED_ARC.getItem());
        this.inv.setItem(Kits.IMPROVED_SWORD.getSlot(), Kits.IMPROVED_SWORD.getItem());
        this.inv.setItem(Kits.MORE_SHEEP.getSlot(), Kits.MORE_SHEEP.getItem());
        this.inv.setItem(Kits.BUILDER.getSlot(), Kits.BUILDER.getItem());
        this.inv.setItem(Kits.MOBILITY.getSlot(), Kits.MOBILITY.getItem());
        this.inv.setItem(Kits.ARMORED_SHEEP.getSlot(), Kits.ARMORED_SHEEP.getItem());
    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        ItemStack is = new ItemStack(Material.FEATHER);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§aChoisir votre kit §7(clique droit)");
        is.setItemMeta(im);
        p.getInventory().setItem(4, is);
        p.updateInventory();
    }

    @EventHandler
    public void interact(PlayerInteractEvent e){
        Player p = e.getPlayer();
        ItemStack i = e.getItem();
        if(i != null && i.getType() != null && i.getType() == Material.FEATHER){
            p.openInventory(inv);
        }
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack current = e.getCurrentItem();

        if(inv.getName().equalsIgnoreCase(inv_name)){
            if(current == null) return;
            e.setCancelled(true);

            if(current.getType() == Kits.MORE_LIFE.getIcon().getType()){
                Kits.MORE_LIFE.add(p);
            }else if(current.getType() == Kits.IMPROVED_ARC.getIcon().getType()){
                Kits.IMPROVED_ARC.add(p);
            }else if(current.getType() == Kits.IMPROVED_SWORD.getIcon().getType()){
                Kits.IMPROVED_SWORD.add(p);
            }else if(current.getType() == Kits.MORE_SHEEP.getIcon().getType()){
                Kits.MORE_SHEEP.add(p);
            }else if(current.getType() == Kits.BUILDER.getIcon().getType()){
                Kits.BUILDER.add(p);
            }else if(current.getType() == Kits.MOBILITY.getIcon().getType()){
                Kits.MOBILITY.add(p);
            }else if(current.getType() == Kits.ARMORED_SHEEP.getIcon().getType()){
                Kits.ARMORED_SHEEP.add(p);
            }
            p.closeInventory();
        }
    }
}
