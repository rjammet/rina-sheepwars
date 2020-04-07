package fr.rinaorc.rinasheepwars.booster.list;

import fr.rinaorc.rinasheepwars.booster.Booster;
import fr.rinaorc.rinasheepwars.booster.BoosterAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonBooster implements BoosterAction {
	
	public PoisonBooster() {}

	@Override
	public boolean onStart(Player paramPlayer, Booster paramBooster) {
	    for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
		}
	      return true;
	}

	@Override
	public boolean onEvent(Player paramPlayer, Event paramEvent, Booster.TriggerAction paramTriggerAction) {
		// TODO Auto-generated method stub
		return false;
	}
}
