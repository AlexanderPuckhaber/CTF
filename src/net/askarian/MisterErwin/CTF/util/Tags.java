package net.askarian.MisterErwin.CTF.util;
import java.util.HashMap;

import net.askarian.MisterErwin.CTF.CTF;
//import net.askarian.MisterErwin.CTF.listener.TagAPIListener;
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
 
    public Tags(CTF pl){
        
    }
    
    
    public void setTag(Player p, ChatColor color){

    	if (this.namedPlayer.containsKey(p.getName()))
    		this.namedPlayer.remove(p.getName());
    	
    	this.namedPlayer.put(p.getName(), color);
    	
    	refreshPlayer(p);
    	
    }
    
    
    //gives players wool blocks on their heads for teams
    public void refreshPlayer(Player p)
    {
    	//removed tagAPI stuff as it is deprecated
		if (plugin.tm.getTeam(p) == "A")
			p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1 , DyeColor.RED.getWoolData()));
		else if (plugin.tm.getTeam(p) == "B")
			p.getInventory().setHelmet(new ItemStack(Material.WOOL, 1 , DyeColor.BLUE.getWoolData()));

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
