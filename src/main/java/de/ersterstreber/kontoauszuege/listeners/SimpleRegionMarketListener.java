package de.ersterstreber.kontoauszuege.listeners;

import me.ienze.SimpleRegionMarket.events.RegionBuyEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.ersterstreber.kontoauszuege.Main;
import de.ersterstreber.kontoauszuege.statements.StatementManager;

public class SimpleRegionMarketListener implements Listener {

	@EventHandler
	public void onRegionBuy(RegionBuyEvent e) {
		String ownerName;
		Player owner = Bukkit.getPlayer(e.getSeller());
		if (owner != null) {
			ownerName = owner.getName();
		} else {
			ownerName = Bukkit.getOfflinePlayer(e.getSeller()).getName();
		}
		String clientName = Bukkit.getPlayer(e.getBuyer()).getName();
		StatementManager.saveStatement(e.getBuyer(), "§7[" + Main.date() + "][GS][" + e.getRegionName() + "][" + ownerName + "]§c[-] " + e.getPrice() + " SD");
		StatementManager.saveStatement(e.getSeller(), "§7[" + Main.date() + "][GS][" + e.getRegionName() + "][" + clientName + "]§2[+] " + e.getPrice() + " SD");
	}
	
}
