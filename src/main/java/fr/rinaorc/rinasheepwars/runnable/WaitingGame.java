package fr.rinaorc.rinasheepwars.runnable;

import fr.rinaorc.rinacore.api.actionbar.ActionBarUtils;
import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.status.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class WaitingGame {

    public static int timer = 61;
    int task;

    public WaitingGame(){
        GameState.setState(GameState.PREGAME);
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(SheepWars.get(), new Runnable() {
            @Override
            public void run() {
                timer--;
                for(Player pl : Bukkit.getOnlinePlayers()){
                    pl.setLevel(timer);
                    if(!SheepWars.get().kits.containsKey(pl)){
                        ActionBarUtils.sendActionBar(pl, "§a§lSélectionné votre Kit !");
                    }
                }
                if(timer == 60 || timer == 50 || timer == 40 || timer == 30 || timer == 20 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2){
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        ActionBarUtils.sendActionBar(pl, "§eLancement de la partie dans §c"+timer + " §esecondes");
                        pl.sendMessage("§eLancement de la partie dans §c"+timer + " §esecondes");
                    }
                }
                if(timer == 1){
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        ActionBarUtils.sendActionBar(pl, "§eLancement de la partie dans §c"+timer + " §eseconde");
                        pl.sendMessage("§eLancement de la partie dans §c"+timer + " §eseconde");
                    }
                }

                if(timer == 0){
                    Bukkit.getScheduler().cancelTask(task);
                    new PrepareStartGame();
                }
            }
        }, 20, 20);
    }
}
