package fr.rinaorc.rinasheepwars.booster.projectile;

import fr.rinaorc.rinasheepwars.booster.Booster;
import fr.rinaorc.rinasheepwars.utils.RandomUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileLaunch implements Listener {

	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		if ((event.getEntity() instanceof Arrow)) {
			Arrow arrow = (Arrow)event.getEntity();
			if ((arrow.getShooter() instanceof Player)) {
				Player player = (Player)arrow.getShooter();
				for (Booster booster : Booster.values()) {
					if (booster.getTriggers().contains(Booster.TriggerAction.ARROW_LAUNCH)) {
						booster.getAction().onEvent(player, event, Booster.TriggerAction.ARROW_LAUNCH);
					}
				}
				if (RandomUtils.getRandomByPercent(20)){
					arrow.setKnockbackStrength(2);
				}else if (RandomUtils.getRandomByPercent(10)){
					arrow.setCritical(true);
				}
			}
		}
	}
}