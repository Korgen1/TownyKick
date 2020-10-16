package me.Korgen0.TownyKick;



import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyWorld;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.utils.CombatUtil;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin{
	
	@Override
		public void onEnable() {
		//run on startup / reloads
		this.saveDefaultConfig();
		
	}
	
	@Override
	public void onDisable() {
		//runs on shutdowns / reloads / disabled
		
		
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("tkversion")) {
			if (sender instanceof Player) {
				//player
			}
			else {
				//console
				sender.sendMessage(ChatColor.RED + "Towny Kick version 1.0.1 by Korgen0");
				return true;
			}
		}
		
		
		if (label.equalsIgnoreCase("tkick")) {
			
			if (sender instanceof Player) {
				//player
				Player p = (Player) sender;
				if (p.hasPermission("tkick.use")) {
				if (args.length == 0) {
					sender.sendMessage(colorize(this.getConfig().getString("format")));
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
								sender.sendMessage(colorize(this.getConfig().getString("notintown")));
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
									//code to kick out of town
									int e = 0;
									int x = 1;
									for (int i = 0; i < x; ++i) {
										World world = target.getWorld();
								        int baseX = target.getLocation().getChunk().getX();
								        int baseZ = target.getLocation().getChunk().getZ(); 
								        
							
								        Chunk chunk1 = world.getChunkAt(baseX + e, baseZ);
								        Chunk chunk2 = world.getChunkAt(baseX - e, baseZ);
								        Chunk chunk3 = world.getChunkAt(baseX, baseZ + e);
								        Chunk chunk4 = world.getChunkAt(baseX, baseZ - e );
								        
								        Chunk chunk5 = world.getChunkAt(baseX + e, baseZ + e);
								        Chunk chunk6 = world.getChunkAt(baseX - e, baseX - e);
								        Chunk chunk7 = world.getChunkAt(baseX + e, baseZ - e);
								        Chunk chunk8 = world.getChunkAt(baseX - e, baseZ + e);
								        
								        
								        
								        
								       
								        
										Location loc1 = new Location(world,chunk1.getX() * 16,chunk1.getWorld().getHighestBlockYAt(chunk1.getX() * 16, chunk1.getZ()) + 1,chunk1.getZ());
										Location loc2 = new Location(world,chunk2.getX() * 16,chunk2.getWorld().getHighestBlockYAt(chunk2.getX() * 16, chunk2.getZ()) + 1,chunk2.getZ());
										Location loc3 = new Location(world,chunk3.getX() * 16,chunk3.getWorld().getHighestBlockYAt(chunk3.getX() * 16, chunk3.getZ()) + 1,chunk3.getZ());
										Location loc4 = new Location(world,chunk4.getX() * 16,chunk4.getWorld().getHighestBlockYAt(chunk4.getX() * 16, chunk4.getZ()) + 1,chunk4.getZ());
										Location loc5 = new Location(world,chunk5.getX() * 16,chunk5.getWorld().getHighestBlockYAt(chunk5.getX() * 16, chunk5.getZ()) + 1,chunk5.getZ());
										Location loc6 = new Location(world,chunk6.getX() * 16,chunk6.getWorld().getHighestBlockYAt(chunk6.getX() * 16, chunk6.getZ()) + 1,chunk6.getZ());
										Location loc7 = new Location(world,chunk7.getX() * 16,chunk7.getWorld().getHighestBlockYAt(chunk7.getX() * 16, chunk7.getZ()) + 1,chunk7.getZ());
										Location loc8 = new Location(world,chunk8.getX() * 16,chunk8.getWorld().getHighestBlockYAt(chunk8.getX() * 16, chunk8.getZ()) + 1,chunk8.getZ());

										if(TownyAPI.getInstance().isWilderness(loc1)) {
											target.teleport(loc1);
											break;
										} else if(TownyAPI.getInstance().isWilderness(loc2)) {
											target.teleport(loc2);
											break;
										} else if(TownyAPI.getInstance().isWilderness(loc3)) {
											target.teleport(loc3);
											break;
										} else if(TownyAPI.getInstance().isWilderness(loc4)) {
											target.teleport(loc4);
											break;
										} else if(TownyAPI.getInstance().isWilderness(loc5)) {
											target.teleport(loc5);
											break;
										} else if(TownyAPI.getInstance().isWilderness(loc6)) {
											target.teleport(loc6);
											break;
										} else if(TownyAPI.getInstance().isWilderness(loc7)) {
											target.teleport(loc7);
											break;
										} else if(TownyAPI.getInstance().isWilderness(loc8)) {
											target.teleport(loc8);
											break;
										} 
										e = e + 1;
									    x++; 
									}
									target.sendMessage(colorize(this.getConfig().getString("kick")));
								}
								else {
									p.sendMessage(colorize(this.getConfig().getString("pvp")));
								}
							}
							else {
								p.sendMessage(colorize(this.getConfig().getString("notinyourtown")));
							}
						}
						else {
							p.sendMessage(colorize(this.getConfig().getString("notinsidetown")));
						}
					}
					else {
						sender.sendMessage(colorize(this.getConfig().getString("no-online")));
						return true;
					}
				}
				}
				else {
					p.sendMessage(colorize(this.getConfig().getString("no-permission")));
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
	
	
	
	public String colorize(String msg)
    {
        String coloredMsg = "";
        for(int i = 0; i < msg.length(); i++)
        {
            if(msg.charAt(i) == '&')
                coloredMsg += '§';
            else
                coloredMsg += msg.charAt(i);
        }
        return coloredMsg;
    }
}
	
	
	
