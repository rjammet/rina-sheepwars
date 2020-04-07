package fr.rinaorc.rinasheepwars.sheep;

import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.utils.MathUtils;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class LightningSheep implements SheepAction {
	
	public LightningSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(final Player player, long ticks, CustomSheep customSheep, final Sheep sheep){
		if (!sheep.hasMetadata("onGround")) {
			sheep.setMetadata("onGround", new FixedMetadataValue(SheepWars.get(), Boolean.valueOf(true)));
			final World world = sheep.getWorld();
			world.setStorm(true);
			new BukkitRunnable() {
				private int seconds = 10;

				public void run() {
					if ((seconds == 0) || (sheep.isDead())) {
						cancel();
						world.setStorm(false);
						world.setThundering(false);
						return;
					}
					Location location = sheep.getLocation();
					for (int x = -6; x < 6; x++) {
						for (int y = -6; y < 6; y++) {
							for (int z = -6; z < 6; z++) {
								if (MathUtils.randomBoolean(0.01F)) {
									Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
									Block top = block.getRelative(BlockFace.UP);
									if ((block.getType() != Material.AIR) && (top.getType() == Material.AIR) && (!sheep.isDead())) {
										Location topLoc = top.getLocation();
										world.strikeLightning(topLoc);
										createExplosion(player, topLoc, 1.0F, true);
										seconds -= 2;
										return;
									}
								}
							}
						}
					}
					seconds -= 2;
				}
			}.runTaskTimer(SheepWars.get(), 20L, 40L);
		}
		
		// effet de chez pas quoi pourquoi pas....
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {}
	
	  public static void createExplosion(Player player, Location location, float power)
	  {
	    createExplosion(player, ((CraftWorld)location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), power, true, false);
	  }
	  
	  public static void createExplosion(Player player, Location location, float power, boolean fire) {
	    createExplosion(player, ((CraftWorld)location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), power, true, fire);
	  }
	  
	  public static void createExplosion(Player player, WorldServer worldServer, double x, double y, double z, float power, boolean breakBlocks, boolean fire) {
	    worldServer.createExplosion(((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)player).getHandle(), x, y, z, power, fire, breakBlocks);
	  }
}
