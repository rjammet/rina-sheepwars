package fr.rinaorc.rinasheepwars.sheep;

import fr.rinaorc.rinasheepwars.runnable.GameTask;
import fr.rinaorc.rinasheepwars.utils.ItemBuilder;
import fr.rinaorc.rinasheepwars.utils.MathUtils;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum Sheep{

	BOARDING("BOARDING", 0, "Mouton d'abordage", "", new BoardingSheep(), DyeColor.WHITE, -1, false, false, false, 0.25F),
	EXPLOSIVE("EXPLOSIVE", 1, "Mouton explosion", "", new ExplosiveSheep(), DyeColor.RED, 5, true, false, true),
	HEALER("HEALER", 2, "Mouton soigneur", "", new HealerSheep(), DyeColor.PINK, 10, true, true, false),
	INCENDIARY("INCENDIARY", 3, "Mouton incendiaire", "", new IncendiarySheep(), DyeColor.ORANGE, 5, true, false, true, 0.5F),
	SEEKER("SEEKER", 4, "Mouton chercheur", "", new SeekerSheep(), DyeColor.LIME, 10, true, false, true, 0.5F),
	DARK("DARK", 5, "Mouton d'aveuglement", "", new DarkSheep(), DyeColor.BLACK, 10, true, false, true),
	FROZEN("FROZEN", 6, "Mouton reine des neiges", "", new FrozenSheepAction(), DyeColor.CYAN, 10, true, false, true, 0.5F),
	EARTHQUAKE("EARTHQUAKE", 7, "EARTHQUAKE_SHEEP", "", new EarthQuakeSheep(), DyeColor.BROWN, 10, true, false, true), 
	DISTORSION("DISTORSION", 8, "Mouton distortion", "", new DistorsionSheep(), DyeColor.PURPLE, 5, true, false, true),
	FRAGMENTATION("FRAGMENTATION", 9, "Mouton de fragmentation", "", new FragmentationSheep(), DyeColor.GRAY, 5, true, false, true),
	LIGHTNING("LIGHTNING", 10, "Mouton orageux", "", new LightningSheep(), DyeColor.YELLOW, 5, true, false, true, 0.35F),
	REMOTE("REMOTE", 11, "Mouton d'éloignement", "", new RemoteSheep(), DyeColor.PURPLE, 15, false, false, false, 0.35F),
	INTERGALACTIC("INTERGALACTIC", 12, "INTERGALACTIC", "", new IntergalacticSheep(), DyeColor.BLUE, -1, true, false, false, 0.15F), 
	SWAP("SWAP", 13, "Mouton swap",  "", new SwapSheep(), DyeColor.PURPLE, -1, true, false, false, 0.5F);

	private String name;
	private String id;
	private String description;
	private SheepAction action;
	private DyeColor color;
	private int duration;
	private boolean onGround;
	private boolean friendly;
	private boolean drop;
	private float random;

	public static org.bukkit.entity.Sheep spawnFixSheep(Location location, Player player) {
		CustomSheep customSheep = new CustomSheep(((CraftWorld)location.getWorld()).getHandle(), player);
		customSheep.setPosition(location.getX(), location.getY(), location.getZ());
		((CraftWorld)location.getWorld()).getHandle().addEntity(customSheep);
		customSheep.setInvisible(false);
		return (org.bukkit.entity.Sheep)customSheep.getBukkitEntity();
	}

	public static org.bukkit.entity.Sheep spawnSheep(Location location, Player player, Sheep sheep) {
		CustomSheep customSheep = new CustomSheep(((CraftWorld)location.getWorld()).getHandle(), player, sheep);
		customSheep.setInvisible(false);
		if (sheep.isFriendly()) {
			customSheep.setPosition(location.getX(), player.getLocation().getY(), location.getZ());
		} else {
			customSheep.setPosition(location.getX(), location.getY(), location.getZ());
		}
		((CraftWorld)location.getWorld()).getHandle().addEntity(customSheep);
		customSheep.setInvisible(false);
		return (org.bukkit.entity.Sheep)customSheep.getBukkitEntity();
	}


	public static void giveRandomSheep(Player player) {
		Sheep temp = null;
		for (Sheep sheep = null; sheep == null; sheep = temp) {
			temp = values()[MathUtils.random.nextInt(values().length)];
			MathUtils.randomBoolean();
		}
		giveSheep(player, temp);
	}

	public static void giveSheep(Player player, Sheep sheep) {

		if (((sheep == BOARDING) || (sheep == INTERGALACTIC)) && (GameTask.DurationInSec > 1080)) {
			giveRandomSheep(player);
		} else {
			player.getInventory().addItem(new ItemStack[] { sheep.getIcon(player) });
			player.sendMessage("§aVous avez recu : §b" + sheep.getName(player));
		}
	}

	private Sheep(String s, int n, String name, String description, SheepAction action, DyeColor color, int duration, boolean onGround, boolean friendly, boolean drop) {
		this(s, n, name, description, action, color, duration, onGround, friendly, drop, 1.0F);
	}

	private Sheep(String s, int n, String name, String description, SheepAction action, DyeColor color, int duration, boolean onGround, boolean friendly, boolean drop, float random) {
		this.name = name;
		id = s;
		this.description = description;
		this.action = action;
		this.color = color;
		this.duration = duration;
		this.onGround = onGround;
		this.friendly = friendly;
		this.drop = drop;
		this.random = random;
	}

	public org.bukkit.entity.Sheep spawnSheep(Location location, Player player) {
		return spawnSheep(location, player, this);
	}

	public String getName(Player p) {
		return name;
	}

	public SheepAction getAction() {
		return action;
	}

	public DyeColor getColor() {
		return color;
	}

	public ItemStack getIcon(Player p){
		return new ItemBuilder(Material.WOOL, color.getWoolData()).setTitle(name).build();
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public int getDuration() {
		return duration;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public boolean isFriendly() {
		return friendly;
	}

	public boolean isDrop() {
		return drop;
	}

	public float getRandom() {
		return random;
	}
}