package fr.rinaorc.rinasheepwars.sheep;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RemoteSheep implements SheepAction{

	private final Map<Sheep, Player> sheeps;
	private final Map<Player, Location> locations;

	public RemoteSheep(){
		sheeps = new HashMap<Sheep, Player>();
		locations = new HashMap<Player, Location>();
	}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep){
		player.setNoDamageTicks(Integer.MAX_VALUE);
		player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 50));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 50));
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 5));
		sheeps.put(sheep, player);
		locations.put(player, player.getLocation());
		for (Player online : Bukkit.getOnlinePlayers())
		{
			online.hidePlayer(player);
		}
	}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		return sheep.getPassenger() == null;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death){
		player.teleport((Location)locations.remove(player));
		player.setFireTicks(0);
		player.setNoDamageTicks(0);
		player.playEffect(EntityEffect.HURT);
		for (Player online : Bukkit.getOnlinePlayers())
		{
			online.showPlayer(player);
		}
		for (PotionEffect effect : player.getActivePotionEffects()) {
			player.removePotionEffect(effect.getType());
		}
		if (!death) {
			customSheep.explode(2.5F);
		}
	}
}
