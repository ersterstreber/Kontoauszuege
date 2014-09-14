package de.ersterstreber.kontauszuege.listeners;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.ienze.SimpleRegionMarket.events.RegionBuyEvent;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Acrobot.ChestShop.Events.TransactionEvent;
import com.Acrobot.ChestShop.Events.TransactionEvent.TransactionType;
import com.greatmancode.craftconomy3.events.MoneyPayEvent;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class AuszugListener implements Listener {

	@EventHandler
	public void onUpdate(TransactionEvent e) {
		OfflinePlayer owner = e.getOwner();
		double price = e.getPrice();
		DecimalFormat df = new DecimalFormat("#.##");
		String ioowner = "§4n/A";
		String ioclient = "§4n/A";
		if (e.getTransactionType() == TransactionType.BUY) {
			ioowner = "§2[B]";
			ioclient = "§c[B]";
		}
		if (e.getTransactionType() == TransactionType.SELL) {
			ioowner = "§c[S]";
			ioclient = "§2[S]";
		}
		double realprice = 0;
		if (e.getTransactionType() == TransactionType.BUY) {
			String[] ss = e.getSign().getLine(2).split(":");
			if (ss.length > 1) {
				if (ss[0].toUpperCase().contains("B")) {
					realprice = Double.parseDouble(ss[0].replace(" ", "").split("B")[1]);
				}
				if (ss[1].toUpperCase().contains("B")) {
					realprice = Double.parseDouble(ss[1].replace(" ", "").split("B")[1]);
				}
			} else {
				realprice = Double.parseDouble(ss[0].replace(" ", "").split("B")[1]);
			}
		}
		if (e.getTransactionType() == TransactionType.SELL) {
			String[] ss = e.getSign().getLine(2).split(":");
			if (ss.length > 1) {
				if (ss[0].toUpperCase().contains("S")) {
					realprice = Double.parseDouble(ss[0].replace(" ", "").split("S")[1]);
				}
				if (ss[1].toUpperCase().contains("S")) {
					realprice = Double.parseDouble(ss[1].replace(" ", "").split("S")[1]);
				}
			} else {
				realprice = Double.parseDouble(ss[0].replace(" ", "").split("S")[1]);
			}
		}
		double d = realprice / e.getPrice();
		double itemsbought = Double.parseDouble(e.getSign().getLine(1)) / d;
		Kontoauszug.add(owner.getName().replace("%gkonto", ""), "§7["
				+ getDate() + "][CS][IN][" + Math.round(itemsbought) + "]" + ioowner
				+ "§9[" + e.getSign().getLine(3) + "] §3"
				+ e.getClient().getName() + ": §f" + df.format(price) + " SD");
		Kontoauszug.add(e.getClient().getName().replace("%gkonto", ""), "§7["
				+ getDate() + "][CS][OUT][" + Math.round(itemsbought) + "]" + ioclient
				+ "§9[" + e.getSign().getLine(3) + "] §3"
				+ e.getOwner().getName() + ": §f" + df.format(price) + " SD");
	}

	@EventHandler
	public void onSend(MoneyPayEvent e) {
		DecimalFormat df = new DecimalFormat("#.##");
		Kontoauszug.add(e.getOwner(), "§7[" + getDate() + "][IC]§2[+]" + e.getClient()
				+ ": " + df.format(e.getAmount()) + " SD");
		Kontoauszug.add(e.getClient(), "§7[" + getDate() + "][IC]§c[-]" + e.getOwner()
				+ ": " + df.format(e.getAmount()) + " SD");
	}

	@EventHandler
	public void onRegionBuy(RegionBuyEvent e) {
		String owner = e.getOwner();
		DecimalFormat df = new DecimalFormat("#.##");
		Kontoauszug.add(owner.replace("%gkonto", ""), "§7[" + getDate()
				+ "][GS]§2[+]§9[" + e.getRegion() + "]§3" + e.getBuyer() + ": §f"
				+ df.format(e.getAmount()) + " SD");
		Kontoauszug.add(e.getBuyer().replace("%gkonto", ""), "§7[" + getDate()
				+ "][GS]§c[-]§9[" + e.getRegion() + "]§3" + e.getOwner() + ": §f"
				+ df.format(e.getAmount()) + " SD");
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

}
