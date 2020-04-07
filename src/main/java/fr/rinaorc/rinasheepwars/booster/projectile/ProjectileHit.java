package fr.rinaorc.rinasheepwars.booster.projectile;

import java.lang.reflect.Field;

import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.booster.Booster;
import fr.rinaorc.rinasheepwars.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EntityArrow;

public class ProjectileHit implements Listener {
	
	  @EventHandler
	  public void onProjectileHit(ProjectileHitEvent event) {
	    if ((event.getEntity() instanceof Arrow)) {
	      final Arrow arrow = (Arrow)event.getEntity();
	      arrow.remove();
	      if ((arrow.getShooter() instanceof Player)) {
	        final Player player = (Player)arrow.getShooter();
	        new BukkitRunnable()
	        {
	          @SuppressWarnings("deprecation")
			public void run() {
	            try {
					/**
					 *  "d" "e" "f" field for access int
					 */
					EntityArrow entityArrow = ((CraftArrow)arrow).getHandle();
	              Field fieldX = EntityArrow.class.getDeclaredField("d");
	              Field fieldY = EntityArrow.class.getDeclaredField("e");
	              Field fieldZ = EntityArrow.class.getDeclaredField("f");
	              fieldX.setAccessible(true);
	              fieldY.setAccessible(true);
	              fieldZ.setAccessible(true);

	              int x = fieldX.getInt(entityArrow);
	              int y = fieldY.getInt(entityArrow);
	              int z = fieldZ.getInt(entityArrow);

	              Block block = arrow.getWorld().getBlockAt(x, y, z);

	              if ((block.getType() == Material.WOOL) && (SheepWars.get().isBooster(block.getLocation()))) {
	                
	                  block.setType(Material.AIR);
	                  block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getTypeId());
	                  Booster booster = Booster.values()[MathUtils.random.nextInt(Booster.values().length)];

	                  for (Player pl : Bukkit.getOnlinePlayers()) {
	                    pl.sendMessage("§b§l"+player.getName() + " §ea activé le booster " + booster.getName(player));
	                  }
	                  booster.getAction().onStart(player, booster);
	                
	              }
	            }
	            catch (NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
	              e.printStackTrace();
	            }
	          }
	        }.runTaskLater(SheepWars.get(), 1L);
	      }
	    }
	  }
}