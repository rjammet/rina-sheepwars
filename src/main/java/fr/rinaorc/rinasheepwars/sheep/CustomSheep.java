package fr.rinaorc.rinasheepwars.sheep;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntitySheep;
import net.minecraft.server.v1_8_R3.EnumColor;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

public class CustomSheep extends EntitySheep{

	private Sheep sheep;
	private Player player;
	private net.minecraft.server.v1_8_R3.World world;
	private boolean explosion = true;
	private boolean ground;
	private long defaultTicks;
	private long ticks;
	private boolean drop;

	public CustomSheep(net.minecraft.server.v1_8_R3.World world){
		super(world);
	}

	public CustomSheep(net.minecraft.server.v1_8_R3.World world, Player player) {
		super(world);
		this.player = player;
		this.world = ((CraftWorld)player.getWorld()).getHandle();
	}

	public void convertColor() {
		sheep.getColor().getWoolData();
	}

	public CustomSheep(net.minecraft.server.v1_8_R3.World world, Player player, Sheep sheep){
		this(world, player);
		getNavigation();
		a(new float[] { 0.9F, 1.3F });

		this.sheep = sheep;
		ticks = (sheep.getDuration() == -1 ? Long.MAX_VALUE : sheep.getDuration() * 20);
		defaultTicks = ticks;
		ground = (!sheep.isOnGround());
		drop = sheep.isDrop();

		setColor(EnumColor.fromColorIndex(sheep.getColor().getWoolData()));
		getBoundingBox().a(0.0D, 0.0D, 0.0D);

		if (sheep != null) {
			sheep.getAction().onSpawn(player, this, getBukkitSheep());
		}
		if ((sheep == Sheep.INTERGALACTIC) || (sheep == Sheep.LIGHTNING)) {
			fireProof = true;
		}else if (sheep == Sheep.SEEKER){
			try{
				Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
				bField.setAccessible(true);
				Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
				cField.setAccessible(true);
				bField.set(goalSelector, new UnsafeList<>());
				bField.set(targetSelector, new UnsafeList<>());
				cField.set(goalSelector, new UnsafeList<>());
				cField.set(targetSelector, new UnsafeList<>());
			}
			catch (Exception e){
				e.printStackTrace();
			}
			getNavigation();
			goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.5D, false));
			goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
			goalSelector.a(8, new PathfinderGoalRandomLookaround(this));

			targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
		}
	}


	public void g(double d0, double d1, double d2) {}

	public void g(float sideMot, float forMot){
		if ((sheep != null) && (onGround) && (sheep == Sheep.REMOTE)) {
			if ((passenger == null) || (!(passenger instanceof EntityHuman)) || (sheep != Sheep.REMOTE)) {
				super.g(sideMot, forMot);
				S = 1.0F;
				aK = 0.02F;
				return;
			}
			lastYaw = (this.yaw = passenger.yaw);
			pitch = (passenger.pitch * 0.5F);

			setYawPitch(yaw, pitch);
			setInvisible(false);
			aI = (this.aG = yaw);

			sideMot = passenger.ao() * 0.5F;
			forMot = passenger.af * 0.5F;
			if (forMot <= 0.0F){
				forMot *= 0.25F;
			}

			Field jump = null;

			try{
				jump = EntityLiving.class.getDeclaredField("aY");
			}
			catch (NoSuchFieldException localNoSuchFieldException) {}catch (SecurityException localSecurityException) {}

			jump.setAccessible(true);

			if ((jump != null) && (onGround)){
				try{
					if (jump.getBoolean(passenger)){
						double jumpHeight = 0.5D;

						motY = jumpHeight;
					}
				}
				catch (IllegalAccessException localIllegalAccessException) {}
			}

			S = 1.0F;

			aK = (bh() * 0.1F);
			if (!world.isClientSide){
				k(0.35F);
				super.g(sideMot, forMot);
			}

			ay = az;
			double d0 = locX - lastX;
			double d1 = locZ - lastZ;
			float f4 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
			if (f4 > 1.0F){
				f4 = 1.0F;
			}

			az += (f4 - az) * 0.4F;
			aA += az;
		}
		super.g(sideMot, forMot);
	}

	public void bL(){
		try{
			if (sheep != null){
				if (!ground){
					ground = ((sheep.isFriendly()) || (onGround) || (inWater));
				}
				else{
					if (((sheep.isFriendly()) || (ticks <= defaultTicks - 20L)) && ((ticks == 0L) || (sheep.getAction().onTicking(player, ticks, this, getBukkitSheep())) || (!isAlive()))){
						boolean death = true;
						if (isAlive()){
							die();
							death = false;
						}
						sheep.getAction().onFinish(player, this, getBukkitSheep(), death);
						return;
					}
					ticks -= 1L;
				}
				if ((!onGround) && (ticksLived < 100) && (!sheep.isFriendly())) {

					// creer particules pour plus de styles
				}
				explosion = (!explosion);
			}
		}
		catch (Exception ex) {
			return;
		}
		finally {
			super.bL(); } super.bL();

			super.bL();
	}

	public void dropDeathLoot(boolean flag, int i){
		if (drop) {
			if (getBukkitEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK){
				if ((getBukkitSheep().getKiller() instanceof Player)){
					Sheep.giveSheep(getBukkitSheep().getKiller(), sheep);
				}
			} else if (getBukkitEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE){
				if ((getBukkitSheep().getKiller() instanceof Player)){
					getBukkitSheep().getWorld().dropItem(getBukkitSheep().getLocation(), sheep.getIcon(getBukkitSheep().getKiller()));
				}
			}
		}
	}

	public Player getPlayer(){
		return player;
	}

	public org.bukkit.entity.Sheep getBukkitSheep() {
		return (org.bukkit.entity.Sheep)getBukkitEntity();
	}

	public void explode(float power){
		explode(power, true, false);
	}

	public void explode(float power, boolean fire){
		explode(power, true, fire);
	}

	public void explode(float power, boolean breakBlocks, boolean fire){
		drop = false;
		getBukkitEntity().remove();
		createExplosion(player, world, locX, locY, locZ, power, breakBlocks, fire);
	}


	public static void createExplosion(Player player, Location location, float power){
		createExplosion(player, ((CraftWorld)location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), power, true, false);
	}

	public static void createExplosion(Player player, Location location, float power, boolean fire) {
		createExplosion(player, ((CraftWorld)location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), power, true, fire);
	}

	public static void createExplosion(Player player, World world, double x, double y, double z, float power, boolean breakBlocks, boolean fire) {
		world.createExplosion(((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer)player).getHandle(), x, y, z, power, fire, breakBlocks);
	}

}
