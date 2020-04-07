package fr.rinaorc.rinasheepwars.sheep;

import net.minecraft.server.v1_8_R3.EntityFireball;
import net.minecraft.server.v1_8_R3.MovingObjectPosition;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EntityMeteor extends EntityFireball {
	
	  public EntityMeteor(World world)
	  {
	    super(world);
	  }
	  
	  public EntityMeteor(World world, Player shooter) {
	    super(world);
	    this.shooter = ((CraftPlayer)shooter).getHandle();
	  }
	  
	  public void t_() {
	    if (inWater) {
	      world.createExplosion(shooter, locX, locY, locZ, 3.0F, true, true);
	      die();
	    }
	    else {
	      super.t_();
	      // EFFET D4EXPLOSION DE TNT CADENCES
	      
	      motX *= 1.0499999523162842D;
	      motY *= 1.0499999523162842D;
	      motZ *= 1.0499999523162842D;
	    }
	  }
	  
	  public void a(MovingObjectPosition movingobjectposition) {
	    world.createExplosion(shooter, locX, locY, locZ, 3.0F, true, true);
	    die();
	  }

}
