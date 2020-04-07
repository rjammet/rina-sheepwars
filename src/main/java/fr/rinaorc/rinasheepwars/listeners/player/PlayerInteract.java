package fr.rinaorc.rinasheepwars.listeners.player;


import fr.rinaorc.rinasheepwars.sheep.Sheep;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
	
	@EventHandler
	  public void onPlayerInteract(PlayerInteractEvent event) {
	    final Player player = event.getPlayer();

	    if (event.getAction().name().contains("RIGHT")) {
	        if (event.hasItem()) {
	          ItemStack item = event.getItem();
	         
	          if (item.getType() != Material.WOOL) {
	            return;
	          }
	          event.setCancelled(true);
	            for (Sheep sheep : Sheep.values()) {
	              ItemStack sheepStack = sheep.getIcon(player);
	              if (sheepStack.isSimilar(item)) {
	                if (item.getAmount() == 1) {
	                  player.setItemInHand(null);
	                }else {
	                  item.setAmount(item.getAmount() - 1);
	                  player.setItemInHand(item);
	                }
	                player.updateInventory();
	                
	                Location playerLocation = player.getLocation();
	                Location location = sheep.isFriendly() ? playerLocation.toVector().add(playerLocation.getDirection().multiply(2.0D)).toLocation(player.getWorld()) : player.getLocation();
	                org.bukkit.entity.Sheep sheepEntity = sheep.spawnSheep(location, player);
	               
	                  sheepEntity.setMaxHealth(15.0D);
	                  sheepEntity.setHealth(15.0D);
	                
	                if ((sheep == Sheep.BOARDING) || (sheep == Sheep.REMOTE)) {
	                  sheepEntity.setPassenger(player);
	                }
	                
	                if (!sheep.isFriendly()) {
	                  sheepEntity.setVelocity(location.getDirection().multiply(4.0D));
	                }
	              }
	            } 
	      }
	    }
	  }
}