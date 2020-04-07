package fr.rinaorc.rinasheepwars.sheep;

import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.utils.EntityUtils;
import fr.rinaorc.rinasheepwars.utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class IntergalacticSheep implements SheepAction{

	public IntergalacticSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {
		for (Player online : Bukkit.getOnlinePlayers()){
			online.sendMessage("§b§l "+player.getName() + " §a§la lancé un mouton intergalgffsg");
			online.playSound(online.getLocation(), Sound.WITHER_SPAWN, 1.0F, 0.5F);
		}
	}

	public boolean onTicking(final Player player, long ticks, CustomSheep customSheep, final Sheep sheep){
		if (!sheep.hasMetadata("onGround")) {
			final World world = sheep.getWorld();
			final Location location = sheep.getLocation();
			sheep.setMetadata("onGround", new FixedMetadataValue(SheepWars.get(), Boolean.valueOf(true)));
			world.setTime(20000L);
			new BukkitRunnable() {
				private int seconds = MathUtils.random(4, 10) + 1;

				public void run() {
					if (seconds == 0) {
						cancel();
						sheep.remove();
						world.setTime(6000L);
						return;
					}
					if (seconds > 2) {
						EntityMeteor meteor = new EntityMeteor(((CraftWorld)location.getWorld()).getHandle(), player);
						meteor.setPosition(location.getX() + MathUtils.random(-20, 20), location.getY() + 40.0D, location.getZ() + MathUtils.random(-20, 20));
						((CraftWorld)location.getWorld()).getHandle().addEntity(meteor);
						Fireball fireball = (Fireball)meteor.getBukkitEntity();
						fireball.setBounce(false);
						fireball.setIsIncendiary(true);
						for (Player online : Bukkit.getOnlinePlayers())
						{
							online.playSound(fireball.getLocation(), Sound.GHAST_FIREBALL, 5.0F, 1.5F);
						}
						EntityUtils.moveToward(fireball, location.clone().add(MathUtils.random(-5, 5), 0.0D, MathUtils.random(-5, 5)), 0.7D);
					}
					seconds -= 1;
				}
			}.runTaskTimer(SheepWars.get(), 0L, 20L);
		}
		return false;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {}
}
