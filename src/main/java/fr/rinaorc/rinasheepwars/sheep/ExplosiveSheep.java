package fr.rinaorc.rinasheepwars.sheep;

import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class ExplosiveSheep implements SheepAction{

	public ExplosiveSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		if ((ticks <= 60L) && (ticks % 3L == 0L)) {
			if (ticks == 60L) {
				sheep.getWorld().playSound(sheep.getLocation(), Sound.FUSE, 1.0F, 1.0F);
			}
			sheep.setColor(sheep.getColor() == DyeColor.WHITE ? DyeColor.RED : DyeColor.WHITE);
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death){
		if (!death) {
			customSheep.explode(3.7F);
		}
	}
}
