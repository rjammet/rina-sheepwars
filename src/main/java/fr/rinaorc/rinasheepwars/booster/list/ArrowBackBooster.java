package fr.rinaorc.rinasheepwars.booster.list;

import fr.rinaorc.rinasheepwars.booster.Booster;
import fr.rinaorc.rinasheepwars.booster.BoosterAction;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.util.HashMap;
import java.util.Map;

public class ArrowBackBooster implements BoosterAction {

	private final Map<Player, Long> players;

	public ArrowBackBooster(){
		players = new HashMap<Player, Long>();
	}

	@Override
	public boolean onEvent(Player player, Event event, Booster.TriggerAction trigger) {
		if (trigger == Booster.TriggerAction.ARROW_LAUNCH) {
			ProjectileLaunchEvent launchEvent = (ProjectileLaunchEvent)event;
			if ((player != null) && (players.containsKey(player))) {
				long time = ((Long)players.get(player)).longValue();
				if (System.currentTimeMillis() - time <= 15000L) {
					((Arrow)launchEvent.getEntity()).setKnockbackStrength(2);
					return true;
				}
				players.remove(player);
			}
		}
		return false;
	}

	@Override
	public boolean onStart(Player player, Booster paramBooster) {
		players.put(player, Long.valueOf(System.currentTimeMillis()));
		return true;
	}
}
