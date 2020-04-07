package fr.rinaorc.rinasheepwars.team;

import fr.rinaorc.rinasheepwars.SheepWars;
import fr.rinaorc.rinasheepwars.utils.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Team {

    BLUE("BLUE", 0, "blue", "Bleue", Material.INK_SACK, (short) DyeColor.BLUE.getDyeData(), ChatColor.BLUE, Color.BLUE),
    RED("RED", 1, "red", "Rouge", Material.INK_SACK, (short)DyeColor.RED.getDyeData(), ChatColor.RED, Color.RED),
    SPEC("SPEC", 2, "spec", "Spectateur", null, (short)0, ChatColor.GRAY, null);

    private String name;
    private String displayName;
    private ItemStack icon;
    private short durability;
    private ChatColor color;
    private Color leatherColor;
    private static org.bukkit.scoreboard.Team craftTeam;
    private List<Location> spawns;
    private int lastSpawn;
    private Objective obj;

    public Team getPlayerTeam(Player player) {
        if (player == null) {
            return SPEC;
        }
        if (!player.hasMetadata("team")) {
            Team[] values;
            int length = (values = values()).length;
            for (int i = 0; i < length; i++) {
                Team team = values[i];
                if (craftTeam.getPlayers().contains(player)) {
                    return team;
                }
            }
        }
        else {
            String teamName = player.getMetadata("team").get(0).asString();
            Team[] values2;
            int length2 = (values2 = values()).length;
            for (int j = 0; j < length2; j++) {
                Team team2 = values2[j];
                if (name.equals(teamName)) {
                    return team2;
                }
            }
        }
        return SPEC;
    }

    public Team getRandomTeam() {
        return BLUE.getOnlinePlayers().size() < RED.getOnlinePlayers().size() ? BLUE : RED;
    }

    public Team getTeam(String name) {
        Team[] values;
        int length = (values = values()).length; for (int i = 0; i < length; i++) {
            Team team = values[i];
            if ((craftTeam != null) && (craftTeam.getName().equalsIgnoreCase(name))) {
                return team;
            }
        }
        return null;
    }

    public Team getTeam(ChatColor colors) {
        Team[] values;
        int length = (values = values()).length;
        for (int i = 0; i < length; i++) {
            Team team = values[i];
            if (colors == color) {
                return team;
            }
        }
        return null;
    }

    private Team(String s, int n, String name, String displayName, Material material, short durability, ChatColor color, Color leatherColor) {
        this.name = name;
        this.displayName = displayName;
        this.durability = durability;
        if (material != null) {
            icon = new ItemBuilder(material, 1, durability).setTitle(color + "Rejoindre l'équipe " + displayName).build();
        }
        this.color = color;
        this.leatherColor = leatherColor;
        lastSpawn = 0;
        createTeam(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void addPlayer(Player player) {
        player.setMetadata("team", new FixedMetadataValue(SheepWars.get(), name));
        player.setPlayerListName(color + (player.getName().length() > 14 ? player.getName().substring(0, 14) : player.getName()));
        craftTeam.addPlayer(player);
    }

    public void removePlayer(Player player) {
        player.removeMetadata("team", SheepWars.get());
        craftTeam.removePlayer(player);
    }

    public Set<Player> getOnlinePlayers() {
        Set<Player> players = new HashSet<>();
        for (OfflinePlayer offline : craftTeam.getPlayers()) {
            if ((offline instanceof Player)) {
                players.add((Player)offline);
            }
        }
        return players;
    }

    public void createTeam(Scoreboard scoreboard) {
        craftTeam = scoreboard.getTeam(name);
        if (craftTeam == null) {
            craftTeam = scoreboard.registerNewTeam(name);
        }
        craftTeam.setPrefix(color.toString());
        craftTeam.setDisplayName(name);
        craftTeam.setAllowFriendlyFire(false);
        craftTeam.setCanSeeFriendlyInvisibles(true);
        spawns = new ArrayList<>();
    }

    public String getDisplayName(Player p) {
        return displayName;
    }

    public ItemStack getIcon(Player p) {
        ItemStack i = new ItemStack(Material.INK_SACK, 1, durability);
        ItemMeta iMeta = i.getItemMeta();
        iMeta.setDisplayName("§7Rejoindre l'équipe " + displayName);
        i.setItemMeta(iMeta);
        return i;
    }

    public ChatColor getColor() {
        return color;
    }

    public Color getLeatherColor() {
        return leatherColor;
    }

    public org.bukkit.scoreboard.Team getCraftTeam() {
        return craftTeam;
    }

    public List<Location> getSpawns() {
        return spawns;
    }

    public Objective getObjective(){
        return obj;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
