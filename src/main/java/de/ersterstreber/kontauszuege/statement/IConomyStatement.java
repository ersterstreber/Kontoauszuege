package de.ersterstreber.kontauszuege.statement;

import java.util.ArrayList;
import java.util.List;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class IConomyStatement {

	private String player;

	public IConomyStatement(String player) {
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
	
	public double getProfit(String date, boolean month) {
		return getEarnings(date, month) - getExpenses(date, month);
	}

}
