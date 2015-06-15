package net.askarian.MisterErwin.CTF;

import java.util.HashMap;
import java.util.Random;

import net.askarian.MisterErwin.CTF.API.events.ChangeAwayFromClassEvent;
import net.askarian.MisterErwin.CTF.API.events.ChangeToClassEvent;
import net.askarian.MisterErwin.CTF.util.Tags;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

public class TeamManager
{
  public HashMap<String, String> TeamA = new HashMap();
  public HashMap<String, String> TeamB = new HashMap();

  public HashMap<String, String> offlineP = new HashMap();
  private CTF plugin;

  public TeamManager(CTF pl)
  {
    this.plugin = pl;
  }
  
  public Team AScoreTeam;
  public Team BScoreTeam;

  

  public String getTeam(Player p) {
    if (this.TeamA.containsKey(p.getName()))
      return "A";
    if (this.TeamB.containsKey(p.getName()))
      return "B";
    return "";
  }

  public String getTeam(String p) {
    if (this.TeamA.containsKey(p))
      return "A";
    if (this.TeamB.containsKey(p))
      return "B";
    return "";
  }

  public void setTeam(Player p, String team) {
    if (team == "A"){
      this.TeamA.put(p.getName(), plugin.clm.Classes.get(0).getCommand());
      plugin.tags.setTag(p, ChatColor.RED);
      this.AScoreTeam.addPlayer(p);
    }else if (team == "B"){
      this.TeamB.put(p.getName(), plugin.clm.Classes.get(0).getCommand());
      plugin.tags.setTag(p, ChatColor.BLUE);
      this.BScoreTeam.addPlayer(p);

    }else{
      this.TeamA.put(p.getName(), plugin.clm.Classes.get(0).getCommand());
      plugin.tags.setTag(p, ChatColor.RED);
      this.AScoreTeam.addPlayer(p);

    }
  }

  public String getPClass(Player p) {
    if (this.TeamA.containsKey(p.getName()))
      return (String)this.TeamA.get(p.getName());
    if (this.TeamB.containsKey(p.getName()))
      return (String)this.TeamB.get(p.getName());
    return "";
  }

  public void setClass(Player p, String kit) {
    if (this.TeamA.containsKey(p.getName())) {      
    	// Call the events
    	plugin.getServer().getPluginManager().callEvent(new ChangeAwayFromClassEvent(p));
    	plugin.getServer().getPluginManager().callEvent(new ChangeToClassEvent(p));
    	this.TeamA.put(p.getName(), kit);
    }else if (this.TeamB.containsKey(p.getName())){
    	// Call the events
    	plugin.getServer().getPluginManager().callEvent(new ChangeAwayFromClassEvent(p));
    	plugin.getServer().getPluginManager().callEvent(new ChangeToClassEvent(p));
    	this.TeamB.put(p.getName(), kit);
    }
  }

  public void JoinTeam(Player p)
  {
    if ((this.TeamA.containsKey(p.getName())) || (this.TeamB.containsKey(p.getName()))) {
      return;
    }
//    if (this.offlineP.containsKey(p.getName()) && false)
//    {
//      String oteam = (String)this.offlineP.get(p.getName());
//      if (oteam == "A") {
//        if (this.TeamA.size() < this.TeamB.size() - 5)
//          setTeam(p, "A");
//        else
//          setTeam(p, "B");
//      }
//      else if (this.TeamA.size() < this.TeamB.size() - 5)
//        setTeam(p, "A");
//      else {
//        setTeam(p, "B");
//      }
//
//    }
    else if (this.TeamA.size() < this.TeamB.size()) {
      setTeam(p, "A");
    }
    else if (this.TeamB.size() < this.TeamA.size()) {
      setTeam(p, "B");
    }
    else {
      Random r = new Random();
      //creates random number from 0 to 1
      int rnd = r.nextInt(2);
      if (rnd == 0)
      setTeam(p, "A");
      else
      setTeam(p, "B");
    }
    p.getInventory().clear();
    if (plugin.Game.running) {
    	if (plugin.tm.getTeam(p) == "A") {
    		p.teleport(plugin.Game.SpawnA);
    	}else if (plugin.tm.getTeam(p) == "B") {
    		p.teleport(plugin.Game.SpawnB);
    	}
    }else if(plugin.Game.idle){
    	p.teleport(plugin.Game.Spawn);
    }else
    	p.setHealth(0);
    
    p.setScoreboard(plugin.cm.sboard);
  }

  public void LeaveTeam(Player p, boolean onquit) {
	  if (Boolean.valueOf(plugin.conm.get("auto")) == true && !onquit)
	  {
		  plugin.cm.SendMessage(p, ChatColor.RED + "You can't leave the game!");
		  return;
	  }
	  
	  p.setScoreboard(plugin.cm.smanager.getNewScoreboard()); //manager.getNewScoreboard() will return a blank scoreboard
	  
    String team = "";
    if (this.TeamA.containsKey(p.getName())){
      team = "A";
      this.TeamA.remove(p.getName());
      this.AScoreTeam.removePlayer(p);

    }
    else if (this.TeamB.containsKey(p.getName())) {
      team = "B";
      this.TeamB.remove(p.getName());
      this.BScoreTeam.removePlayer(p);

    }
    if (this.plugin.Game.waitingPlayers.containsKey(p.getName()))
    	this.plugin.Game.waitingPlayers.remove(p.getName());
    
    plugin.cm.allupdate();
    
    this.offlineP.put(p.getName(), team);
    p.getInventory().clear();
    p.getInventory().setHelmet(null);
    p.getInventory().setChestplate(null);
    p.getInventory().setLeggings(null);
    p.getInventory().setBoots(null);
    
    p.setLevel(0);
    p.setExp(0);
    p.sendMessage(plugin.cm.clearChat());
    p.sendMessage(plugin.cm.clearChat());
    p.teleport(plugin.getServer().getWorlds().get(0).getSpawnLocation());
    
	  ChangeAwayFromClassEvent e = new ChangeAwayFromClassEvent(p);

    
    plugin.InvM.give(p);
    
    p.setDisplayName(ChatColor.stripColor(p.getDisplayName()));
    plugin.tags.refreshPlayer(p);
  }

  public void PlayerJoin(Player p) {
    if (this.offlineP.containsKey(p)) {
      this.plugin.cm.SendMessage(p, ChatColor.YELLOW + "Welcome back");
      JoinTeam(p);
    }
  }
  
  public boolean isinGame(Player p) {
	  if(this.getTeam(p) == "A" || this.getTeam(p) == "B")
		  return true;
	  else
		  return false;
  }

  public boolean isinGameWorld(Player p) {
	if (plugin.Game.Spawn != null && p.getWorld().getName() == plugin.Game.SpawnA.getWorld().getName())
		return true;
	else
		return false;
  }
  
  public boolean hasFlag(Player p){
	  if (this.plugin.Game.FlagAHolder instanceof Player 
			  && this.plugin.Game.FlagAHolder == p)
		  return true;
	  if (this.plugin.Game.FlagBHolder instanceof Player 
			  && this.plugin.Game.FlagBHolder == p)
		  return true;
	  
	  return false;
		  
  }
}
