package fr.rinaorc.rinasheepwars.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {

	private String title;
	  private final int amount;
	  private final short damage;
	  private Color leatherColor;
	  private Boolean glow;
	  private final Material material;
	  private final List<String> lores;
	  private final Map<Enchantment, Integer> enchantments;
	  
	  public static org.bukkit.inventory.ItemStack getRandomResource(Material material) {
	    return getRandomResource(material, 1, (short)0);
	  }
	  
	  public static org.bukkit.inventory.ItemStack getRandomResource(Material material, short durability) {
	    return getRandomResource(material, 1, durability);
	  }
	  
	  public static org.bukkit.inventory.ItemStack getRandomResource(Material material, int amount, short durability) {
	    org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(material, amount, durability);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName("Resource #" + MathUtils.random.nextInt(255));
	    item.setItemMeta(meta);
	    return item;
	  }
	  
	  public ItemBuilder(org.bukkit.inventory.ItemStack item) {
	    this(item.getType(), item.getAmount(), item.getDurability());
	  }
	  
	  public ItemBuilder(Material material) {
	    this(material, 1, (short)0);
	  }
	  
	  public ItemBuilder(Material material, int amount) {
	    this(material, amount, (short)0);
	  }
	  
	  public ItemBuilder(Material material, int amount, short damage){
	    lores = new ArrayList<String>();
	    enchantments = new HashMap<Enchantment, Integer>();
	    this.material = material;
	    this.amount = amount;
	    this.damage = damage;
	    glow = Boolean.valueOf(false);
	  }
	  
	  public ItemBuilder(Material material, short durability) {
	    this(material, 1, durability);
	  }
	  
	  public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
	    enchantments.put(enchantment, Integer.valueOf(level));
	    return this;
	  }
	  
	  public ItemBuilder addIllegalyGlowEffect(Boolean b) {
	    glow = b;
	    return this;
	  }
	  
	  public org.bukkit.inventory.ItemStack createSkull(String name, String owner) {
	    org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(Material.SKULL_ITEM, 1, (byte)SkullType.PLAYER.ordinal());
	    SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
	    itemMeta.setDisplayName(name);
	    itemMeta.setOwner(Bukkit.getPlayer(owner).getName());
	    item.setItemMeta(itemMeta);
	    return item;
	  }
	  
	  public ItemBuilder addLore(String lore) {
	    lores.add(lore);
	    return this;
	  }
	  
	  public ItemBuilder addLores(String... lores) {
	    for (String lore : lores) {
	      addLore(lore);
	    }
	    return this;
	  }
	  
	  public ItemBuilder setLeatherColor(Color color) {
	    leatherColor = color;
	    return this;
	  }
	  
	  public org.bukkit.inventory.ItemStack build() {
	    if (material == null) {
	      throw new NullPointerException("Material cannot be null!");
	    }
	    
	    org.bukkit.inventory.ItemStack item = new org.bukkit.inventory.ItemStack(material, amount, damage);
	    if (!enchantments.isEmpty()) {
	      item.addUnsafeEnchantments(enchantments);
	    }
	    
	    ItemMeta meta = item.getItemMeta();
	    if (title != null) {
	      meta.setDisplayName(title);
	    }
	    
	    if ((leatherColor != null) && (item.getType().name().contains("LEATHER_"))) {
	      ((LeatherArmorMeta)meta).setColor(leatherColor);
	    }
	    
	    if (!lores.isEmpty()) {
	      meta.setLore(lores);
	    }
	    
	    item.setItemMeta(meta);
	    
	    if (glow.booleanValue()) {
	      ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
	      NBTTagCompound tag = null;
	      if (!nmsStack.hasTag()) {
	        tag = new NBTTagCompound();
	        nmsStack.setTag(tag);
	      }
	      if (tag == null) tag = nmsStack.getTag();
	      NBTTagList ench = new NBTTagList();
	      tag.set("ench", ench);
	      nmsStack.setTag(tag);
	      
	      return CraftItemStack.asCraftMirror(nmsStack);
	    }
	    return item;
	  }
	  
	  public ItemBuilder setTitle(String title){
	    this.title = title;
	    return this;
	  }
}
