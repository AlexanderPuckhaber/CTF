package net.askarian.MisterErwin.CTF.util;
import java.util.HashMap;

import net.askarian.MisterErwin.CTF.CTF;
//import net.askarian.MisterErwin.CTF.listener.TagAPIListener;
//import net.minecraft.server.v1_4_R1.EntityHuman;
//import net.minecraft.server.v1_4_R1.Packet20NamedEntitySpawn;

import net.playerforcehd.nametags.TagManager.TagManager;
import net.playerforcehd.nametags.TagManager.TagPlayer;

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
	public CTF plugin;
	
	private HashMap<String,ChatColor> namedPlayer = new HashMap();

	
    public Tags(CTF pl){
        plugin = pl;
    }
    
    
    public void setTag(Player p, ChatColor color){
    	/*
    	if (this.namedPlayer.containsKey(p.getName()))
    		this.namedPlayer.remove(p.getName());
    	
    	this.namedPlayer.put(p.getName(), color);
    	*/
    	
    	refreshPlayer(p);
    }
    
    
    //gives players wool blocks on their heads for teams
	public void refreshPlayer(Player p)
    {
		String team = "";
		if (plugin.tm.isinGame(p))
			team = plugin.tm.getTeam(p);
		
    	if(tapi){
    	TagPlayer tagplayer = new TagPlayer(p);
    	TagManager tagmanager =  new TagManager(tagplayer);
    	
   

    	if (team == "A")
    		tagmanager.setPrefix(p, "&1");
    	else if (team == "B")
    		tagmanager.setPrefix(p, "&C");
    	
    	}
    	else{
    	
			if (team == "A")
				p.getInventory().setHelmet(new ItemStack(Material.GLASS, 1 , (byte)4));
			else if (team == "B")
				p.getInventory().setHelmet(new ItemStack(Material.GLASS, 1 , (byte)1));
			
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
