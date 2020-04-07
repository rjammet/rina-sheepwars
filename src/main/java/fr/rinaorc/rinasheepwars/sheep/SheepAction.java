package fr.rinaorc.rinasheepwars.sheep;

import org.bukkit.entity.Player;

public abstract interface SheepAction {

    public abstract void onSpawn(Player paramPlayer, CustomSheep paramCustomSheep, org.bukkit.entity.Sheep paramSheep);

    public abstract boolean onTicking(Player paramPlayer, long paramLong, CustomSheep paramCustomSheep, org.bukkit.entity.Sheep paramSheep);

    public abstract void onFinish(Player paramPlayer, CustomSheep paramCustomSheep, org.bukkit.entity.Sheep paramSheep, boolean paramBoolean);

}
