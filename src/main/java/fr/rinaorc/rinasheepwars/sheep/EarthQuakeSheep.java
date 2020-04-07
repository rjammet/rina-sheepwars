package fr.rinaorc.rinasheepwars.sheep;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.util.Vector;

public class EarthQuakeSheep implements SheepAction{
	
	public EarthQuakeSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		if ((ticks % 20L == 0L) && (!sheep.isDead())) {
			World world = sheep.getWorld();
			Location location = sheep.getLocation();

			world.playSound(location, Sound.IRONGOLEM_HIT, 1.0F, 1.0F);
			int z; for (int x = -6; x < 6; x++) {
				for (int y = -6; y < 6; y++) {
					for (z = -6; z < 6; z++) {
						Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
						Block top = block.getRelative(BlockFace.UP);
						if ((block.getType() != Material.AIR) && (top.getType() == Material.AIR)) {
							// efft classeffect en api
						}
					}
				}
			}
			for (Entity entity : sheep.getNearbyEntities(6.0D, 3.0D, 6.0D)) {
				if (((entity instanceof Player))) {
					Player victim = (Player)entity;
					victim.setVelocity(new Vector(0.0D, 0.6D, 0.0D).add(entity.getLocation().getDirection()).multiply(0.9D));
				}
			}
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {}
}
