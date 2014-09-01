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
import com.iCo6.system.events.MoneySendEvent;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class AuszugListener implements Listener {

	@EventHandler
	public void onUpdate(TransactionEvent e) {
		OfflinePlayer owner = e.getOwner();
		double price = e.getPrice();
		DecimalFormat df = new DecimalFormat("#.##");
		String io = "§4n/A";
		if (e.getTransactionType() == TransactionType.BUY)
			io = "§2[B]";
		if (e.getTransactionType() == TransactionType.SELL)
			io = "§c[S]";
		Kontoauszug.add(owner.getName().replace("%gkonto", ""), "§7["
				+ getDate() + "][CS][" + e.getSign().getLine(1) + "]" + io
				+ "§9[" + e.getSign().getLine(3) + "] §3"
				+ e.getClient().getName() + ": §f" + df.format(price) + " SD");
	}

	@EventHandler
	public void onSend(MoneySendEvent e) {
		String owner = e.getReceiver();
		DecimalFormat df = new DecimalFormat("#.##");
		Kontoauszug.add(owner, "§7[" + getDate() + "][IC]§2[+]" + e.getSender()
				+ ": " + df.format(e.getAmount()) + " SD");
		Kontoauszug.add(e.getSender(), "§7[" + getDate() + "][IC]§c[-]" + owner
				+ ": " + df.format(e.getAmount()) + " SD");
	}

	@EventHandler
	public void onRegionBuy(RegionBuyEvent e) {
		String owner = e.getOwner();
		DecimalFormat df = new DecimalFormat("#.##");
		Kontoauszug.add(owner.replace("%gkonto", ""), "§7[" + getDate()
				+ "][GS]§9[" + e.getRegion() + "]§3" + e.getBuyer() + ": §f"
				+ df.format(e.getAmount()) + " SD");
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

}
