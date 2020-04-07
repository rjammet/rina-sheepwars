package fr.rinaorc.rinasheepwars.sheep;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DarkSheep implements SheepAction{

	public DarkSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		if ((ticks % 20L == 0L) && (!sheep.isDead())) {

			for (Entity entity : sheep.getNearbyEntities(10, 10, 10)) {
				if ((entity instanceof Player)) {
					Player nearby = (Player)entity;

					nearby.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
					nearby.playSound(sheep.getLocation(), Sound.ENDERMAN_IDLE, 1.0F, 0.0F);

				}
			}
		} else if (ticks % 5L == 0L) {
			// effets de fum�e
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {}
}
