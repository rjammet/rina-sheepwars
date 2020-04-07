package fr.rinaorc.rinasheepwars.sheep;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class SeekerSheep implements SheepAction {
	
	public SeekerSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		if (ticks % 3L == 0L) {
			if (ticks <= 60L) {
				sheep.setColor(sheep.getColor() == DyeColor.WHITE ? DyeColor.LIME : DyeColor.WHITE);
			}
			for (Entity entity : sheep.getNearbyEntities(2.0D, 0.5D, 2.0D)) {
				if ((entity instanceof Player)) {
					return true;
				}
			}
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death){
		if (!death) {
			customSheep.explode(2.2F);
		}
	}
}
