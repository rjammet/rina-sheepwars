package fr.rinaorc.rinasheepwars.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtils {
	
	  public static final Random random = new Random(System.nanoTime());
	  
	  public RandomUtils() {}
	  
	  public static Vector getRandomVector() { double x = random.nextDouble() * 2.0D - 1.0D;
	    double y = random.nextDouble() * 2.0D - 1.0D;
	    double z = random.nextDouble() * 2.0D - 1.0D;
	    return new Vector(x, y, z).normalize();
	  }
	  
	  public static Vector getRandomCircleVector() {
	    double rnd = random.nextDouble() * 2.0D * 3.141592653589793D;
	    double x = Math.cos(rnd);
	    double z = Math.sin(rnd);
	    return new Vector(x, 0.0D, z);
	  }
	  
	  public static Material getRandomMaterial(Material[] materials) {
	    return materials[random.nextInt(materials.length)];
	  }
	  
	  public static double getRandomAngle() {
	    return random.nextDouble() * 2.0D * 3.141592653589793D;
	  }
	  
	  public static Player getRandomPlayer() {
	    if (Bukkit.getOnlinePlayers().isEmpty()) {
	      return null;
	    }
	    List<Player> rplayer = new ArrayList<Player>();
	    for (Player p : Bukkit.getOnlinePlayers()) {
	      rplayer.add(p);
	    }
	    int alea = new Random().nextInt(rplayer.size());
	    Player rplayer2 = (Player)rplayer.get(alea);
	    return rplayer2;
	  }
	  
	  public static Boolean getRandomByPercent(Integer percent){
	    Integer rdm = Integer.valueOf(new Random().nextInt(101));
	    if (rdm.intValue() < percent.intValue()) {
	      return Boolean.valueOf(true);
	    }
	    return Boolean.valueOf(false);
	  }
	  
	  public static Color getRandomColor(){
	    Random r = new Random();
	    int i = r.nextInt(17) + 1;
	    Color c = null;
	    if (i == 1) {
	      c = Color.AQUA;
	    }
	    if (i == 2) {
	      c = Color.BLACK;
	    }
	    if (i == 3) {
	      c = Color.BLUE;
	    }
	    if (i == 4) {
	      c = Color.FUCHSIA;
	    }
	    if (i == 5) {
	      c = Color.GRAY;
	    }
	    if (i == 6) {
	      c = Color.GREEN;
	    }
	    if (i == 7) {
	      c = Color.LIME;
	    }
	    if (i == 8) {
	      c = Color.MAROON;
	    }
	    if (i == 9) {
	      c = Color.NAVY;
	    }
	    if (i == 10) {
	      c = Color.OLIVE;
	    }
	    if (i == 11) {
	      c = Color.ORANGE;
	    }
	    if (i == 12) {
	      c = Color.PURPLE;
	    }
	    if (i == 13) {
	      c = Color.RED;
	    }
	    if (i == 14) {
	      c = Color.SILVER;
	    }
	    if (i == 15) {
	      c = Color.TEAL;
	    }
	    if (i == 16) {
	      c = Color.WHITE;
	    }
	    if (i == 17) {
	      c = Color.YELLOW;
	    }
	    
	    return c;
	  }
}