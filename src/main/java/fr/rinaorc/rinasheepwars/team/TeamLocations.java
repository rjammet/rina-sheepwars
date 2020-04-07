package fr.rinaorc.rinasheepwars.team;

import fr.rinaorc.rinasheepwars.SheepWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamLocations {

    public static ArrayList<Location> bleue = new ArrayList<>();
    public static ArrayList<Location> red = new ArrayList<>();

    public TeamLocations(){
        World w = Bukkit.getWorld("world");
        bleue.add(new Location(w, 10, 50, 0));
        red.add(new Location(w, 0, 50, 10));
    }

    public void teleportPlayers(){
        for(int i = 0; i < SheepWars.get().playersBleu.size(); i++){
            Player p = SheepWars.get().playersBleu.get(i);
            Location bleu = bleue.get(i);
            p.teleport(bleu);
        }
        for(int i = 0; i < SheepWars.get().playersRed.size(); i++){
            Player p = SheepWars.get().playersRed.get(i);
            Location redd = red.get(i);
            p.teleport(redd);
        }
    }
}
