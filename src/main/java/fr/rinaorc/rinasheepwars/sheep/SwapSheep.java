package fr.rinaorc.rinasheepwars.sheep;

import fr.rinaorc.rinasheepwars.SheepWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SwapSheep implements SheepAction {
	
	public SwapSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(final Player player, long ticks, CustomSheep customSheep, final Sheep sheep){
		if (!sheep.hasMetadata("onGround")) {
			sheep.setMetadata("onGround", new FixedMetadataValue(SheepWars.get(), Boolean.valueOf(true)));
			Location location = player.getLocation();
			int distance = 10;
			Player nearest = null;
			Location lastLocation = null;
			for (Player online : Bukkit.getOnlinePlayers()){
				if (online != player){
					int dist = (int)(lastLocation = online.getLocation()).distance(sheep.getLocation());
					if (dist < distance){
						distance = dist;
						nearest = online;
					}
				}
			}
			if (nearest == null) {
				player.sendMessage("TP EN COURS...");
				return true;
			}
			final Player nearestFinal = nearest;
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 1));
			nearest.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 120, 1));
			player.playSound(location, Sound.PORTAL_TRAVEL, 1.0F, 1.0F);
			nearest.playSound(lastLocation, Sound.PORTAL_TRAVEL, 1.0F, 1.0F);
			new BukkitRunnable() {
				private int seconds = 4;

				public void run() {
					if (seconds == 0) {
						cancel();
						Location location = player.getLocation();
						player.setFallDistance(0.0F);
						player.teleport(nearestFinal.getLocation());
						nearestFinal.setFallDistance(0.0F);
						nearestFinal.teleport(location);
						player.sendMessage("tp..");
						nearestFinal.sendMessage("tp tp tp..");
						sheep.remove();
						return;
					}
					if (seconds == 1) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
						nearestFinal.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
					}
					seconds -= 1;
				}
			}.runTaskTimer(SheepWars.get(), 0L, 20L);
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {}
}
