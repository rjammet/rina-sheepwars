package fr.rinaorc.rinasheepwars.listeners.player;

import fr.rinaorc.rinacore.api.RinaAPI;
import fr.rinaorc.rinacore.api.actionbar.ActionBarUtils;
import fr.rinaorc.rinacore.api.events.PlayerConnectedEvent;
import fr.rinaorc.rinacore.api.events.ScoreboardUpdateEvent;
import fr.rinaorc.rinacore.api.player.RinaPlayer;
import fr.rinaorc.rinacore.api.titles.TitleUtils;
import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.runnable.PrepareStartGame;
import fr.rinaorc.rinasheepwars.runnable.WaitingGame;
import fr.rinaorc.rinasheepwars.status.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnected implements Listener {

    @EventHandler
    public void onPlayerConnected(PlayerJoinEvent e){
        final Player player = e.getPlayer();
        RinaPlayer p = RinaPlayer.getPlayer(player);

        RinaAPI.getBossbarManager().setBossBar(player, "§e§lVous jouez en §b§lSheepWars §e§lsur §a§lPLAY.RINAORC.COM", 100);
        p.getScoreboardManager().createObjective("§b§lSheepWars");
        p.getScoreboardManager().updateScoreboard();
        p.getScoreboardManager().initHearts();

        Bukkit.getScheduler().runTaskLater(SheepWars.get(), () -> TitleUtils.sendTitle(player, "§eSheepWars", "§apar Rinaorc", 20 * 2, 20, 20), 20 * 2);

        player.getInventory().clear();
        player.setMaxHealth(20);
        player.setFoodLevel(20);

        if(!GameState.isState(GameState.LOBBY)){
            //
            // ADDING NEW SYSTEM SPECTATOR MANAGER SOON
            //
            player.setGameMode(GameMode.SPECTATOR);
            ActionBarUtils.sendActionBar(player, "§c§lVous êtes en mode spectateur");
        }

        if((!SheepWars.get().playersInGame.contains(player)) && (GameState.isState(GameState.LOBBY))){
            SheepWars.get().playersInGame.add(player);
            player.setGameMode(GameMode.SURVIVAL);
            // implementation for kit & more

            player.teleport(new Location(player.getWorld(), -120, 138.5, -63));

            Bukkit.broadcastMessage(p.getColorName() + " §evient de rejoindre la partie (§b"+SheepWars.get().playersInGame.size()+"§e/§b10§e)");
            /**
             * Not definitive condition for lunch game
             */
            if(SheepWars.get().playersInGame.size() >= 1){
                new WaitingGame();
            }
        }else{
            // send mode spectator for game
            // for last future..
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void onScorebordUpdate(ScoreboardUpdateEvent e){
        Player player = e.getPlayer();
        RinaPlayer p = RinaPlayer.getPlayer(player);

        if(GameState.isState(GameState.LOBBY)){
            int i = 9;
            p.getScoreboardManager().setLine("§a ", i--);
            p.getScoreboardManager().setLine("§fJoueurs: §a"+SheepWars.get().playersInGame.size(), i--);
            p.getScoreboardManager().setLine("§bBleu §f: §a"+SheepWars.get().playersBleu.size(), i--);
            p.getScoreboardManager().setLine("§cRouge §f: §a"+SheepWars.get().playersRed.size(), i--);
            p.getScoreboardManager().setLine("§b ", i--);
            if(SheepWars.get().playersInGame.size() >= 1){
                p.getScoreboardManager().setLine("§fLancement: "+PrepareStartGame.timer, i--);
            }else{
                p.getScoreboardManager().setLine("§fEn attente...", i--);
            }
            p.getScoreboardManager().setLine("§c ", i--);
            p.getScoreboardManager().setLine("§fMap: §aCastle", i--);
            p.getScoreboardManager().setLine("§d ", i--);
            p.getScoreboardManager().setLine("§6play.rinaorc.com", i--);

        }else if(GameState.isState(GameState.PREGAME)){
            int i = 12;
            p.getScoreboardManager().setLine("§a", i--);
            p.getScoreboardManager().setLine("§fTemps : §a00:00", i--);
            p.getScoreboardManager().setLine("§b", i--);
            p.getScoreboardManager().setLine("§fMouton §7: §a5s", i--);
            p.getScoreboardManager().setLine("§fBooster §7: §a5s", i--);
            p.getScoreboardManager().setLine("§c", i--);
            p.getScoreboardManager().setLine("§cRouge §7: §e"+SheepWars.get().playersRed.size(), i--);
            p.getScoreboardManager().setLine("§9Bleue §7: §e"+SheepWars.get().playersBleu.size(), i--);
            p.getScoreboardManager().setLine("§d", i--);
            p.getScoreboardManager().setLine("§fKills : §bnope", i--);
            p.getScoreboardManager().setLine("§e", i--);
            p.getScoreboardManager().setLine("§6play.rinaorc.com", i--);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        final Player p = e.getPlayer();
        e.setQuitMessage(null);
        // message de deco && check spec / ingame ...  process
    }
}
