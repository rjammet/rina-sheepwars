package fr.rinaorc.rinasheepwars.runnable;

import fr.rinaorc.rinacore.api.actionbar.ActionBarUtils;
import fr.rinaorc.rinacore.api.titles.TitleUtils;
import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.kit.Kits;
import fr.rinaorc.rinasheepwars.status.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PrepareStartGame {

    public static int timer = 17;
    int task;

    public PrepareStartGame(){
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

                if(timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2){
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        TitleUtils.sendTitle(pl, "§c"+timer, "§ePréparez-vous !", 20, 20, 20);
                        pl.sendMessage("§eLa partie va démarrer dans §c"+timer + " §esecondes");
                    }
                }
                if(timer == 1){
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        TitleUtils.sendTitle(pl, "§c"+timer, "§ePréparez-vous !", 20, 20, 20);
                        pl.sendMessage("§eLa partie va démarrer dans §c"+timer + " §eseconde");
                    }
                }
                if(timer == 0){
                    Bukkit.getScheduler().cancelTask(task);
                    // [...]
                    GameState.setState(GameState.GAME);

                    new GameTask(SheepWars.get());
                    //new TeamLocations().teleportPlayers();

                    for(Player pl : Bukkit.getOnlinePlayers()){
                        Kits kit = SheepWars.get().kits.get(pl);
                        ArrayList<ItemStack> itemKit = kit.getItems();
                        pl.getInventory().clear();

                        for(ItemStack item : itemKit){
                            pl.getInventory().addItem(item);
                        }
                    }
                }
            }
        }, 20, 20);
    }
}
