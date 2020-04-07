package fr.rinaorc.rinasheepwars.listeners;

import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.booster.projectile.ProjectileHit;
import fr.rinaorc.rinasheepwars.booster.projectile.ProjectileLaunch;
import fr.rinaorc.rinasheepwars.kit.Menu;
import fr.rinaorc.rinasheepwars.listeners.player.PlayerConnected;
import fr.rinaorc.rinasheepwars.listeners.player.PlayerInteract;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {
	
	Plugin plugin;
	
	public ListenersManager() {
		this.plugin = SheepWars.get();
	}

	public void registerListeners(){
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Menu(), plugin);
		pm.registerEvents(new PlayerConnected(), plugin);
		pm.registerEvents(new PlayerInteract(), plugin);
		pm.registerEvents(new ProjectileHit(), plugin);
		pm.registerEvents(new ProjectileLaunch(), plugin);
	}

}
