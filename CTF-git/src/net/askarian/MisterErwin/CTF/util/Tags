package net.askarian.MisterErwin.CTF.util;
import java.util.HashMap;

import net.askarian.MisterErwin.CTF.CTF;
import net.askarian.MisterErwin.CTF.listener.TagAPIListener;
//import net.minecraft.server.v1_4_R1.EntityHuman;
//import net.minecraft.server.v1_4_R1.Packet20NamedEntitySpawn;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
//import org.bukkit.craftbukkit.v1_4_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
 
public class Tags {
 
  private boolean tapi = false;
	private CTF plugin;
	
	private HashMap<String,ChatColor> namedPlayer = new HashMap();
	private TagAPIListener tagListener;
 
    public Tags(CTF pl){
        plugin = pl;
        for (Plugin p : pl.getServer().getPluginManager().getPlugins()) {
            String name = p.getClass().getName();

            if (name.equals("org.kitteh.tag.TagAPI")) {
              this.tagListener = new TagAPIListener(this.plugin);
              plugin.getServer().getPluginManager().registerEvents(this.tagListener, this.plugin);

          		pl.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Found TagAPI - Colored Namtags are enabled!");

          		tapi = true;
            }

          }
        if (tapi == false) {
        	pl.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + " TagAPI is not enabled! - There 'll be no colored nametags");
        	tapi = false;
        }
    }
    
    
    public void setTag(Player p, ChatColor color){

    	if (this.namedPlayer.containsKey(p.getName()))
    		this.namedPlayer.remove(p.getName());
    	
    	this.namedPlayer.put(p.getName(), color);
    	
    	refreshPlayer(p);
    	
    }
    
 
    public void refreshPlayer(Player p)
    {
//    	if (true)
//    		return;
    	if (this.tapi == true){
    		try{
    			if (org.kitteh.tag.TagAPI.class == null)
    				throw new Exception();
    			org.kitteh.tag.TagAPI.refreshPlayer(p);
    		}catch (Exception e) {
				plugin.log.warning("Can't refresh Players nametag!");
			}
    	}else{
    		if (plugin.tm.getTeam(p) == "A")
    			p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1 , DyeColor.RED.getWoolData()));
    		else if (plugin.tm.getTeam(p) == "B")
    			p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1 , DyeColor.BLUE.getWoolData()));
			p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1 , DyeColor.GREEN.getWoolData()));


    	}
	}
    
    public boolean hascoloredname(String s){
    	return this.namedPlayer.containsKey(s);
    }
    
    public ChatColor getColor(String s){
    	if (!hascoloredname(s))
    		return ChatColor.RESET;
    	
    	return this.namedPlayer.get(s);
    }
    

}
