package fr.rinaorc.rinasheepwars.runnable;

import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.booster.BoosterManager;
import fr.rinaorc.rinasheepwars.sheep.Sheep;
import fr.rinaorc.rinasheepwars.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTask extends BukkitRunnable{

	public static int DurationInSec = 1200;

	public GameTask(SheepWars plugin){
		runTaskTimer(plugin, 0L, 20L);
	}

	public void run() {
		if ((DurationInSec == 0)) {
			cancel();
		}
		int remainingMins = DurationInSec / 60 % 60;
		int remainingSecs = DurationInSec % 60;

		if ((remainingMins == 19) && (remainingSecs == 40)) {
			new BukkitRunnable() {
				public void run() {
					Location booster = BoosterManager.getRandomSpawnBooster();
					Block magicBlock = booster.getBlock();
					magicBlock.setType(Material.WOOL);
					
					for (Player pl : Bukkit.getOnlinePlayers()) {
						pl.playSound(pl.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
						Bukkit.broadcastMessage("§eUne laine §e§lbooster §evient d'apparaître !");
					}

					new BukkitRunnable(){
						private int seconds = 10;

						public void run() {
							if ((seconds == 0) || (magicBlock.getType() == Material.AIR)) {
								cancel();
								magicBlock.setType(Material.AIR);
								return;
							}
							magicBlock.setData(DyeColor.values()[MathUtils.random.nextInt(DyeColor.values().length)].getWoolData());
							seconds -= 1;
						}
					}.runTaskTimer(SheepWars.get(), 0L, 20L);
				}
			}.runTaskTimer(SheepWars.get(), 0L, 400L);
		}

		if (DurationInSec == 1200){ // 1200 value first implementation

			/**
			 *
			 * Changements dans cette class a la prochaine modif des moutons :
			 *
			 * system de timer
			 * le temps d'interval
			 * new modifier
			 *  // seen -> look -> give -> check -> give -> check
			 */
			new BukkitRunnable(){
				public void run(){
					for (Player player : Bukkit.getOnlinePlayers()) {
						Sheep.giveRandomSheep(player);
					}
				}
			}.runTaskTimer(SheepWars.get(), 0L, 800L);

		}else if ((remainingMins == 10) && (remainingSecs == 0)) {
			new BukkitRunnable() {
				public void run() {
					for (Player player : Bukkit.getOnlinePlayers()) {
						Sheep.giveSheep(player, Sheep.BOARDING);
					}
				}
			}.runTaskTimer(SheepWars.get(), 0L, 1200L);
		}
		DurationInSec -= 1;
	}
}
