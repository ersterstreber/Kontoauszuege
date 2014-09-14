package de.ersterstreber.kontauszuege.statement;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class CraftConomyStatement {

	private String player;

	public CraftConomyStatement(String player) {
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}

	public double getExpenses(String date, boolean month) {
		List<String> auszuege = Kontoauszug.pconfig.getStringList("players."
				+ player);
		List<String> iconomyoutgoes = new ArrayList<String>();
		for (String s : auszuege) {
			if (s.contains("[-]") && s.contains("[IC]")) {
				if (month) {
					if (s.contains("." + date + "."))
						iconomyoutgoes.add(s);
				} else {
					if (s.contains("[" + date + "]"))
						iconomyoutgoes.add(s);
				}
			}
		}
		double iprice = 0;
		for (String s : iconomyoutgoes) {
			String[] splitted = s.split(": ");
			String[] ss = splitted[1].split(" ");
			double price = 0;
			try {
				price = Double.parseDouble(ss[0]);
			} catch (NumberFormatException ex) {
				System.err.println(splitted[1] + " could not be cast to a double!");
				return price;
			}
			iprice += price;
		}
		return iprice;
	}
	
	public double getEarnings(String date, boolean month) {
		List<String> auszuege = Kontoauszug.pconfig.getStringList("players."
				+ player);
		List<String> iconomyins = new ArrayList<String>();
		for (String s : auszuege) {
			if (s.contains("[+]") && s.contains("[IC]")) {
				if (month) {
					if (s.contains("." + date + "."))
						iconomyins.add(s);
				} else {
					if (s.contains("[" + date + "]"))
						iconomyins.add(s);
				}
			}
		}
		double iprice = 0;
		for (String s : iconomyins) {
			String[] splitted = s.split(": ");
			String[] ss = splitted[1].split(" ");
			double price = 0;
			try {
				price = Double.parseDouble(ss[0]);
			} catch (NumberFormatException ex) {
				System.err.println(splitted[1] + " could not be cast to a double!");
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
		DecimalFormat dc = new DecimalFormat("#.##");
		
		return dc.format(s);
	}

}
