package de.ersterstreber.kontauszuege.statement;

import java.util.ArrayList;
import java.util.List;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class ChestShopStatement {

	private String player;

	public ChestShopStatement(String player) {
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}

	public double getExpenses(String date, boolean month) {
		List<String> auszuege = Kontoauszug.pconfig.getStringList("players."
				+ player);
		List<String> srmoutgoes = new ArrayList<String>();
		for (String s : auszuege) {
			if ((s.contains("[IN]") && s.contains("[CS]") && s.contains("[B]")) || (s.contains("[OUT]") && s.contains("[CS]") && s.contains("[S]"))) {
				if (month) {
					if (s.contains("." + date + "."))
						srmoutgoes.add(s);
				} else {
					if (s.contains("[" + date + "]"))
						srmoutgoes.add(s);
				}
			}
		}
		double iprice = 0;
		for (String s : srmoutgoes) {
			String[] splitted = s.split(": ");
			String[] q = splitted[1].split(" ");
			double price = 0;
			try {
				price = Double.parseDouble(q[0].replace("§f", ""));
			} catch (NumberFormatException ex) {
				System.err.println(q[0].replace("§d", "") + " could not be cast to a double!");
				return price;
			}
			iprice += price;
		}
		return iprice;
	}
	
	public double getEarnings(String date, boolean month) {
		List<String> auszuege = Kontoauszug.pconfig.getStringList("players."
				+ player);
		List<String> srmins = new ArrayList<String>();
		for (String s : auszuege) {
			if ((s.contains("[IN]") && s.contains("[CS]") && s.contains("[S]")) || (s.contains("[OUT]") && s.contains("[CS]") && s.contains("[B]"))) {
				if (month) {
					if (s.contains("." + date + "."))
						srmins.add(s);
				} else {
					if (s.contains("[" + date + "]"))
						srmins.add(s);
				}
			}
		}
		double iprice = 0.0;
		for (String s : srmins) {
			String[] splitted = s.split(": ");
			String[] q = splitted[1].split(" ");
			double price = 0.0;
			try {
				price = Double.parseDouble(q[0].replace("§f", ""));
			} catch (NumberFormatException ex) {
				System.err.println(q[0].replace("§d", "") + " could not be cast to a double!");
				return price;
			}
			iprice += price;
		}
		return iprice;
	}
	
	public String getProfit(String date, boolean month) {
		double profit = getEarnings(date, month) - getExpenses(date, month);
		String s = null;
		if (profit == 0.0) s = "§7[+/-]" + profit;
		if (profit > 0.0) s = "§2[+]" + profit;
		if (profit < 0.0) s = "§c[-]" + profit * -1;
		
		return s;
	}
	
}
