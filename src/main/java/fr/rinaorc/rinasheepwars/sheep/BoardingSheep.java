package fr.rinaorc.rinasheepwars.sheep;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

public class BoardingSheep implements SheepAction{

	public BoardingSheep() {}

	public void onSpawn(Player player, CustomSheep customSheep, Sheep sheep) {}

	public boolean onTicking(Player player, long ticks, CustomSheep customSheep, Sheep sheep){
		return sheep.getPassenger() == null;
	}

	public void onFinish(Player player, CustomSheep customSheep, Sheep sheep, boolean death) {
		sheep.setPassenger(null);
	}
}
