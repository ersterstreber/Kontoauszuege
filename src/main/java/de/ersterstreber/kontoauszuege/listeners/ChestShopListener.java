package de.ersterstreber.kontoauszuege.listeners;

import java.text.DecimalFormat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Acrobot.ChestShop.Events.TransactionEvent;

import de.ersterstreber.kontoauszuege.Main;
import de.ersterstreber.kontoauszuege.statements.StatementManager;

public class ChestShopListener implements Listener {

	@EventHandler
	public void onTransaction(TransactionEvent e) {
		DecimalFormat format = new DecimalFormat("#.##");
		double price = Double.parseDouble(format.format(e.getPrice()));
		double signprice = e.getSignPrice();
		double factor = signprice / price;
		double amount = Double.parseDouble(e.getSign().getLine(1)) / factor;
		StatementManager.saveStatement(e.getClient().getUniqueId(), "§7[" + Main.date() + "][CS][" + e.getSign().getLine(3) + "][" + (int) amount + "][" + e.getOwner().getName() + "]"
				+ "§c[-] " + price + " SD");
		StatementManager.saveStatement(e.getOwner().getUniqueId(), "§7[" + Main.date() + "][CS][" + e.getSign().getLine(3) + "][" + (int) amount + "][" + e.getClient().getName() + "]"
				+ "§2[+] " + price + " SD");
	}
	
}
