package fr.rinaorc.rinasheepwars.sheep;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FrozenSheepAction implements SheepAction{

	public FrozenSheepAction() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	@SuppressWarnings("deprecation")
	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		if (ticks % 40L == 0L) {
			World world = sheep.getWorld();
			Location location = sheep.getLocation();
			int z; for (int x = -8; x < 8; x++) {
				for (int y = -8; y < 8; y++) {
					for (z = -8; z < 8; z++) {
						Block block = world.getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
						Block top = block.getRelative(BlockFace.UP);
						if ((block.getType() != Material.AIR) && (block.getType() != Material.SNOW) && (block.getType().isSolid()) && ((top.getType() == Material.AIR) || (top.getType() == Material.SNOW))) {
							// effet de glace / destruction ...
							if (top.getType() != Material.SNOW) {
								top.setData((byte)0);
								top.setType(Material.SNOW);
							}
							else {
								byte data = top.getData();
								if (data < 7) {
									top.setData((byte)(1 + data));
								}
							}
						}
					}
				}
			}

			for (Entity entity : sheep.getNearbyEntities(8.0D, 8.0D, 8.0D)) {
				if ((entity instanceof Player)) {
					Player nearby = (Player)entity;

					nearby.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1)); }
			}
		}

		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {}
}
