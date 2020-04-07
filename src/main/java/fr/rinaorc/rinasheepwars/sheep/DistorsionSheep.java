package fr.rinaorc.rinasheepwars.sheep;

import fr.rinaorc.rinasheepwars.utils.EntityUtils;
import fr.rinaorc.rinasheepwars.utils.MathUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class DistorsionSheep implements SheepAction {

	public DistorsionSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		if (ticks % 3L == 0L) {
			World world = sheep.getWorld();
			Location location = sheep.getLocation();
			world.playSound(location, Sound.ENDERMAN_TELEPORT, 0.5F, 5.0F);
			int y; for (int x = -4; x < 4; x++) {
				for (y = -4; y < 4; y++) {
					for (int z = -4; z < 4; z++) {
						if (MathUtils.randomBoolean(0.025F)) {
							Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
							if (block.getType() != Material.AIR) {
								EntityUtils.spawnFallingBlock(block, world, 0.1F, 0.3F, 0.1F);
								if (MathUtils.randomBoolean()) {
									// effet pour le TP
								}
								block.setType(Material.AIR);
							}
						}
					}
				}
			}
			for (Entity entity : sheep.getNearbyEntities(6.0D, 6.0D, 6.0D)) {
				if (((entity instanceof Player)) && (MathUtils.randomBoolean(0.1F))) {
					EntityUtils.moveToward(entity, location, 0.5D);
				}
			}
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {}
}
