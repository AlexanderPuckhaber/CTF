package net.askarian.MisterErwin.CTF.listener;


  import net.askarian.MisterErwin.CTF.CTF;

import org.bukkit.configuration.file.FileConfiguration;
	import org.bukkit.entity.Player;
	import org.bukkit.event.EventHandler;
	import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

	public class TagAPIListener
	  implements Listener
	{
	  CTF plugin;

	  public TagAPIListener(CTF instance)
	  {
	    this.plugin = instance;
	  }

	  @EventHandler
	  public void onTagReceive(PlayerReceiveNameTagEvent event) {
	    Player namedPlayer = event.getNamedPlayer();

	    if (this.plugin.tags.hascoloredname(namedPlayer.getName()))
	        event.setTag(this.plugin.tags.getColor(namedPlayer.getName()) + namedPlayer.getName());
	  }
	
}
