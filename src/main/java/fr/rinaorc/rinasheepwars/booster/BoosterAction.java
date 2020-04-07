package fr.rinaorc.rinasheepwars.booster;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract interface BoosterAction {

	public abstract boolean onStart(Player paramPlayer, Booster paramBooster);

	public abstract boolean onEvent(Player paramPlayer, Event paramEvent, Booster.TriggerAction paramTriggerAction);

}
