package fr.rinaorc.rinasheepwars.sheep;

import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.utils.MathUtils;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FragmentationSheep implements SheepAction{

	public FragmentationSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, org.bukkit.entity.Sheep sheep) {}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, org.bukkit.entity.Sheep sheep){
		if ((ticks <= 60L) && (ticks % 3L == 0L)) {
			if (ticks == 60L) {
				sheep.getWorld().playSound(sheep.getLocation(), Sound.FUSE, 1.0F, 1.0F);
			}
			sheep.setColor(sheep.getColor() == DyeColor.WHITE ? DyeColor.GRAY : DyeColor.WHITE);
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, org.bukkit.entity.Sheep sheep, boolean death){
		if (!death) {
			Location location = sheep.getLocation();
			customSheep.explode(3.5F);
			for (int i = 0; i < MathUtils.random(3, 6); i++) {
				final org.bukkit.entity.Sheep baby = Sheep.spawnFixSheep(location, player);
				baby.setColor(DyeColor.values()[MathUtils.random.nextInt(DyeColor.values().length)]);
				baby.setVelocity(new Vector(MathUtils.random(1.2F), MathUtils.random(1.9F), MathUtils.random(1.2F)));
				baby.setBaby();
				new BukkitRunnable() {
					public void run() {
						((CustomSheep)((CraftEntity)baby).getHandle()).explode(1.5F);
					}
				}.runTaskLater(SheepWars.get(), 20L);
			}
		}
	}
}
