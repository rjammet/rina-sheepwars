package fr.rinaorc.rinasheepwars.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class EntityUtils {
	
	  public EntityUtils() {}
	  
	  public static void moveToward(org.bukkit.entity.Entity entity, Location to, double speed) {
	    Location location = entity.getLocation();
	    double x = location.getX() - to.getX();
	    double y = location.getY() - to.getY();
	    double z = location.getZ() - to.getZ();
	    Vector velocity = new Vector(x, y, z).normalize().multiply(-speed);
	    entity.setVelocity(velocity);
	  }
	  
	  public static void spawnFallingBlock(Block block, org.bukkit.World world, float xSpeed, float ySpeed, float zSpeed) {

		org.bukkit.entity.FallingBlock fallingBlock = world.spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
	    float x = -xSpeed + (float)(Math.random() * (xSpeed - -xSpeed + 1.0F));
	    float y = -ySpeed + (float)(Math.random() * (ySpeed - -ySpeed + 1.0F));
	    float z = -zSpeed + (float)(Math.random() * (zSpeed - -zSpeed + 1.0F));
	    fallingBlock.setVelocity(new Vector(x, y, z));
	    fallingBlock.setDropItem(false);
	  }

}
