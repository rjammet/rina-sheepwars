package fr.rinaorc.rinasheepwars;

import fr.rinaorc.rinasheepwars.booster.BoosterManager;
import fr.rinaorc.rinasheepwars.kit.Contents;
import fr.rinaorc.rinasheepwars.kit.Kits;
import fr.rinaorc.rinasheepwars.listeners.ListenersManager;
import fr.rinaorc.rinasheepwars.sheep.CustomEntityType;
import fr.rinaorc.rinasheepwars.sheep.CustomSheep;
import fr.rinaorc.rinasheepwars.status.GameState;
import fr.rinaorc.rinasheepwars.team.TeamLocations;
import net.minecraft.server.v1_8_R3.EntitySheep;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class SheepWars extends JavaPlugin {

	public ArrayList<Player> playersInGame = new ArrayList<>();
	public ArrayList<Player> playersBleu = new ArrayList<>();
	public ArrayList<Player> playersRed = new ArrayList<>();
	public HashMap<Player, Kits> kits = new HashMap<>();
	public Contents contents = new Contents();
	private static SheepWars instance;	

	@Override
	public void onEnable() {
		instance = this;

		new ListenersManager().registerListeners();
		new TeamLocations();
		new BoosterManager();

		GameState.setState(GameState.LOBBY);
		/**
		 * TEST FOR ADDING CUSTOM SHEEP | NOT FINISH
		 */
		CustomEntityType.registerEntity("Sheep", 91, EntitySheep.class, CustomSheep.class);
	}

	public boolean isBooster(Location location) {
		final Location booster = new Location(Bukkit.getWorld("world"), 0, 58, 0);
			if ((booster.getBlockX() == location.getBlockX()) && (booster.getBlockY() == location.getBlockY()) && (booster.getBlockZ() == location.getBlockZ())) {
				return true;
			}
		
		return false;
	}
	
	public static SheepWars get() {
		return instance;
	}
}
