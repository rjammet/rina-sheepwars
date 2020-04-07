package fr.rinaorc.rinasheepwars.booster;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class BoosterManager {

    public static ArrayList<Location> boosters = new ArrayList<>();

    public BoosterManager(){
        World w = Bukkit.getWorld("world");
        boosters.add(new Location(w, -66, 89, -26));
        boosters.add(new Location(w, -45, 84, -27));
        boosters.add(new Location(w, -31, 87, -27));
        boosters.add(new Location(w, -12, 84, -26));
        boosters.add(new Location(w, 3, 89, -26));
    }

    public static Location getRandomSpawnBooster(){
        Location loc = null;
        for(int i = 0; i < boosters.size(); i++){
            Location l = boosters.get(i);
            loc = l;
        }
        return loc;
    }
}
