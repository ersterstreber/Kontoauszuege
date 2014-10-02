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
	public void onUpdate(final TransactionEvent e) {
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
		double d = e.getSignPrice() / e.getPrice();
		double bought = Double.parseDouble(e.getSign().getLine(1)) / d;
		Kontoauszug.prepareAdding(owner.getName().replace("%gkonto", "").toLowerCase(), "§7["
				+ getDate() + "][CS][IN][" + Math.round(bought) + "]" + ioowner
				+ "§9[" + e.getSign().getLine(3) + "] §3"
				+ e.getClient().getName() + ": §f" + df.format(price) + " SD");
		Kontoauszug.prepareAdding(e.getClient().getName()
				.replace("%gkonto", "").toLowerCase(), "§7[" + getDate() + "][CS][OUT]["
				+ Math.round(bought) + "]" + ioclient + "§9["
				+ e.getSign().getLine(3) + "] §3" + e.getOwner().getName()
				+ ": §f" + df.format(price) + " SD");

	}

	@EventHandler
	public void onSend(MoneyPayEvent e) {
		DecimalFormat df = new DecimalFormat("#.##");
		String reason = "/";
		if (e.getReason() != null)
			reason = e.getReason();
		Kontoauszug.prepareAdding(
				e.getOwner().toLowerCase(),
				"§7[" + getDate() + "][IC][" + reason + "]§2[+]"
						+ e.getClient() + ": " + df.format(e.getAmount())
						+ " SD");
		Kontoauszug.prepareAdding(e.getClient().toLowerCase(),
				"§7[" + getDate() + "][IC][" + reason + "]§c[-]" + e.getOwner()
						+ ": " + df.format(e.getAmount()) + " SD");
	}

	@EventHandler
	public void onRegionBuy(RegionBuyEvent e) {
		String owner = e.getOwner();
		DecimalFormat df = new DecimalFormat("#.##");
		Kontoauszug.prepareAdding(
				owner.replace("%gkonto", "").toLowerCase(),
				"§7[" + getDate() + "][GS]§2[+]§9[" + e.getRegion() + "]§3"
						+ e.getBuyer() + ": §f" + df.format(e.getAmount())
						+ " SD");
		Kontoauszug.prepareAdding(
				e.getBuyer().replace("%gkonto", "").toLowerCase(),
				"§7[" + getDate() + "][GS]§c[-]§9[" + e.getRegion() + "]§3"
						+ e.getOwner() + ": §f" + df.format(e.getAmount())
						+ " SD");
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

}
