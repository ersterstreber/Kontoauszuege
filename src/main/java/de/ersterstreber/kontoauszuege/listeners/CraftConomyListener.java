package de.ersterstreber.kontoauszuege.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.greatmancode.craftconomy3.events.MoneyPayEvent;

import de.ersterstreber.kontoauszuege.Main;
import de.ersterstreber.kontoauszuege.statements.StatementManager;

public class CraftConomyListener implements Listener {

	@EventHandler
	public void onMoneyTransaction(MoneyPayEvent e) {
		String ownerName;
		Player owner = Bukkit.getPlayer(e.getOwner());
		if (owner != null) {
			ownerName = owner.getName();
		} else {
			ownerName = Bukkit.getOfflinePlayer(e.getOwner()).getName();
		}
		String clientName = Bukkit.getPlayer(e.getClient()).getName();
		String reason;
		if (e.getReason() != null) {
			reason = e.getReason();
		} else {
			reason = "/";
		}
		StatementManager.saveStatement(e.getClient(), "§7[" + Main.date() + "][ECO][" + reason + "][" + ownerName + "]§c[-] " + e.getAmount() + " SD");
		StatementManager.saveStatement(e.getOwner(), "§7[" + Main.date() + "][ECO][" + reason + "][" + clientName + "]§2[+] " + e.getAmount() + " SD");
	}
	
}
