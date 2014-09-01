package de.ersterstreber.kontauszuege.listeners;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!Kontoauszug.pconfig.contains("players." + e.getPlayer().getName())) {
			Kontoauszug.pconfig.addDefault("players." + e.getPlayer().getName(), new ArrayList<String>());
			Kontoauszug.pconfig.options().copyDefaults(true);
			Kontoauszug.savePConfig();
		}
//		Kontoauszug.addEntry(e.getPlayer().getName());
//		Kontoauszug.addGKonto(e.getPlayer());
	}
	
}
