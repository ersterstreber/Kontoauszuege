package de.ersterstreber.kontauszuege.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!Kontoauszug.pconfig.contains("players." + e.getPlayer().getName().toLowerCase())) {
			Kontoauszug.pconfig.addDefault("players." + e.getPlayer().getName().toLowerCase(), new ArrayList<String>());
			Kontoauszug.pconfig.options().copyDefaults(true);
			Kontoauszug.savePConfig();
		} else {
			if (Kontoauszug.pconfig.contains("players." + e.getPlayer().getName())) {
				if (!e.getPlayer().getName().equals(e.getPlayer().getName().toLowerCase())) {
					List<String> auszuege = Kontoauszug.pconfig.getStringList("players." + e.getPlayer().getName());
					Kontoauszug.pconfig.set("players." + e.getPlayer().getName(), null);
					Kontoauszug.savePConfig();
					Kontoauszug.pconfig.addDefault("players." + e.getPlayer().getName().toLowerCase(), auszuege);
					Kontoauszug.pconfig.options().copyDefaults(true);
					Kontoauszug.savePConfig();
				}
			}
		}
		if (!Kontoauszug.toAdd.containsKey(e.getPlayer().getName().toLowerCase())) {
			Kontoauszug.toAdd.put(e.getPlayer().getName().toLowerCase(), new ArrayList<String>());
		}
//		Kontoauszug.addEntry(e.getPlayer().getName());
//		Kontoauszug.addGKonto(e.getPlayer());
	}
	
}
