package fr.rinaorc.rinasheepwars.booster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.rinaorc.rinasheepwars.booster.list.*;
import org.bukkit.entity.Player;

public enum Booster{
	
	POISON("§5§lPoison §7(30 secondes)", new PoisonBooster(), new ArrayList()),
	NAUSEA("§a§lNausée §7(30 secondes)", new NauseaBooster(), new ArrayList()),
	MORE_SHEEP("b§lPlus de moutons", new MoreSheepBooster(), new ArrayList()),
	REGENERATION("§d§lRégénération §7(30 secondes)", new RegenerationBooster(), new ArrayList()),
	RESISTANCE("§8§lRésistance §7(30 secondes)", new ResistanceBooster(), new ArrayList()),
	ARROW_FIRE("§6§lFlèches en feu §7(30 secondes)", new ArrowFireBooster(), Arrays.asList(new TriggerAction[] { TriggerAction.ARROW_LAUNCH })),
	ARROW_BACK("§aFlèche de recul §7(30 secondes)", new ArrowBackBooster(), Arrays.asList(new TriggerAction[] { TriggerAction.ARROW_LAUNCH }));

	private String name;
	private BoosterAction action;
	private List<TriggerAction> triggers;

	private Booster(String name, BoosterAction action, List<TriggerAction> triggers) {
		this.name = name;
		this.action = action;
		this.triggers = triggers;
	}

	public String getName(Player p) {
		return name;
	}

	public BoosterAction getAction() {
		return action;
	}

	public List<TriggerAction> getTriggers() {
		return triggers;
	}

	public static enum TriggerAction{
		ARROW_LAUNCH;
	}
}
