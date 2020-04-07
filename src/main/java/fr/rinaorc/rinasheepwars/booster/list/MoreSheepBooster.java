package fr.rinaorc.rinasheepwars.booster.list;

import fr.rinaorc.rinasheepwars.booster.Booster;
import fr.rinaorc.rinasheepwars.booster.BoosterAction;
import fr.rinaorc.rinasheepwars.sheep.Sheep;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class MoreSheepBooster implements BoosterAction {
	
	public MoreSheepBooster() {}

	@Override
	public boolean onStart(Player paramPlayer, Booster paramBooster) {
		for(Player pl : Bukkit.getOnlinePlayers()) {
			Sheep.giveRandomSheep(pl);
		}
		return true;
	}

	@Override
	public boolean onEvent(Player paramPlayer, Event paramEvent, Booster.TriggerAction paramTriggerAction) {
		// TODO Auto-generated method stub
		return false;
	}
}
