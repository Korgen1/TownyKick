package me.Korgen0.TownyKick;



import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.utils.CombatUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin{
	
	@Override
		public void onEnable() {
		//run on startup / reloads
		
		
	}
	
	@Override
	public void onDisable() {
		//runs on shutdowns / reloads / disabled
		
		
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("tkversion")) {
			if (sender instanceof Player) {
				//player
				Player player = (Player) sender;
				player.sendMessage(ChatColor.RED + "Towny Kick version" + ChatColor.AQUA + " 1.0.0 " + ChatColor.RED + "by" + ChatColor.AQUA +" Korgen0");
				return true;
			}
			else {
				//console
				sender.sendMessage(ChatColor.RED + "Towny Kick version 1.0.0 by Korgen0");
				return true;
			}
		}
		
		
		
		if (label.equalsIgnoreCase("tkick")) {
			
			if (sender instanceof Player) {
				//player
				Player p = (Player) sender;
				if (p.hasPermission("tkick.use")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Format: /tkick <player>");
				}
				else {
					Player target = Bukkit.getPlayer(args[0]);
					if (target !=null) {
						if (!TownyAPI.getInstance().isWilderness(target.getLocation())) {
							Resident r = null;
							try {
								r = TownyAPI.getInstance().getDataSource().getResident(p.getName());
							} catch (NotRegisteredException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (!r.hasTown()) {
								sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Zaup" + ChatColor.GRAY + "" + ChatColor.BOLD + ">> " + ChatColor.RED + "You are not in a town");
								return true;
							}
							Town kt = TownyAPI.getInstance().getTown(target.getLocation());
							Town st = null;
							try {
								st = r.getTown();
							} catch (NotRegisteredException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (kt == st) {
								TownBlock tb = TownyAPI.getInstance().getTownBlock(target.getLocation());
								if(CombatUtil.preventPvP(tb.getWorld(),tb)) {
									Location tel = getServer().getWorld("Spawnworld").getSpawnLocation();
									target.teleport(tel);
									target.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Zaup" + ChatColor.GRAY + "" + ChatColor.BOLD + ">> " + ChatColor.RED + "You were kicked out of the town!");
								}
								else {
									p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Zaup" + ChatColor.GRAY + "" + ChatColor.BOLD + ">> " + ChatColor.RED + "Target player must be in a plot with PVP Disabled!");
								}
							}
							else {
								p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Zaup" + ChatColor.GRAY + "" + ChatColor.BOLD + ">> " + ChatColor.RED + "Player is not in your town!");
							}
						}
						else {
							p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Zaup" + ChatColor.GRAY + "" + ChatColor.BOLD + ">> " + ChatColor.RED + "Player is not in a town!");
						}
					}
					else {
						sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Zaup" + ChatColor.GRAY + "" + ChatColor.BOLD + ">> " + ChatColor.RED + "No player online by this name");
						return true;
					}
				}
				}
				else {
					p.sendMessage(ChatColor.RED + "You do not have permission!");
				}
			}
			else {
				//console
				sender.sendMessage(ChatColor.RED + "Can not be used by console");
				return true;
			}
		}
		
		return false;
	}
}
