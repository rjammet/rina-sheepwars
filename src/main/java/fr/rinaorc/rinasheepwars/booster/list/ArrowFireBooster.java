package fr.rinaorc.rinasheepwars.booster.list;

import java.util.HashMap;
import java.util.Map;

import fr.rinaorc.rinasheepwars.booster.Booster;
import fr.rinaorc.rinasheepwars.booster.BoosterAction;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ArrowFireBooster implements BoosterAction {
	
	private final Map<Player, Long> players;

	public ArrowFireBooster(){
		players = new HashMap<Player, Long>();
	}

	@Override
	public boolean onEvent(Player player, Event event, Booster.TriggerAction trigger) {
		if (trigger == Booster.TriggerAction.ARROW_LAUNCH) {
			ProjectileLaunchEvent launchEvent = (ProjectileLaunchEvent)event;
			if (players.containsKey(player)) {
				long time = ((Long)players.get(player)).longValue();
				if (System.currentTimeMillis() - time <= 15000L) {
					((Arrow)launchEvent.getEntity()).setFireTicks(Integer.MAX_VALUE);
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
