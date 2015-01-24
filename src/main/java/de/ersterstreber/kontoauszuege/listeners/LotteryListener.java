package de.ersterstreber.kontoauszuege.listeners;

import net.erbros.lottery.events.PlayerWinEvent;
import net.erbros.lottery.events.TicketBuyEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.ersterstreber.kontoauszuege.Main;
import de.ersterstreber.kontoauszuege.statements.StatementManager;

public class LotteryListener implements Listener {

	@EventHandler
	public void onTicketBuy(TicketBuyEvent e) {
		StatementManager.saveStatement(e.getBuyer(), "§7[" + Main.date() + "][LOT][" + e.getAmount() + "]§c[-] " + e.getPrice() + " SD");
	}
	
	@EventHandler
	public void onWin(PlayerWinEvent e) {
		StatementManager.saveStatement(e.getWinner(), "§7[" + Main.date() + "][LOT]§2[+] " + e.getWon() + " SD");
	}
	
}
