package fr.evrox.faction;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	FileConfiguration config = this.getConfig();
	
	@Override
	public void onEnable() {
		config.createSection("factionsList");
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = ((Player) sender).getPlayer();
			if(command.getName().equalsIgnoreCase("f")) {
				if(args.length == 0) {
					player.sendMessage("§a-----[FACTION]----");
					player.sendMessage("§a/f create <nomFaction>");
					player.sendMessage("§a-----[by evroxfr]-----");
				}else {
					switch (args[0]) {
					case "create":
						if(args.length < 2) {
							player.sendMessage("§a/f create <nomFaction>");
						}else {
							config.createSection("ownersList."+player.getUniqueId().toString());
							config.set("ownersList."+player.getUniqueId().toString()+".ownerUUID", player.getUniqueId().toString());
							config.set("ownersList."+player.getUniqueId().toString()+".faction", args[1]);
							config.createSection("factionsList."+args[1]);
							config.createSection("factionsList."+args[1]+".players.admin");
							config.createSection("factionsList."+args[1]+".players.mod");
							config.createSection("factionsList."+args[1]+".players.member");
							config.set("factionsList."+args[1]+".players.admin", player.getUniqueId().toString());
							saveConfig();
							player.sendMessage("§aFaction "+args[1]+" crée avec succès !");
							Bukkit.getPlayer(config.getString("ownersList."+player.getUniqueId()+".ownerUUID")).sendMessage("ça marche");;
						}
						break;
					case "invite":
						if(args.length < 2) {
							player.sendMessage("§a/f invite <player>");
						}else {
							
						}
						break;
					default:
						break;
					}
				}
			}
		}
		return false;
	}

}
