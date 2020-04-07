package fr.rinaorc.rinasheepwars.sheep;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealerSheep implements SheepAction{
	
	public HealerSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		if (ticks % 20L == 0L) {

			for (Entity entity : sheep.getNearbyEntities(10.0D, 5.0D, 10.0D)) {
				if ((entity instanceof Player)) {
					Player nearby = (Player)entity;
					nearby.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
					// Pour Luxory apparement ;)
					if(player.getDisplayName() == "LuxoryTS"){
						nearby.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30, 1));
						player.sendMessage("§7§lprankeeeeeeeed");
					}
				}
			}
		} else if (ticks % 5L == 0L) {
			// effet de soin
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {}
}
