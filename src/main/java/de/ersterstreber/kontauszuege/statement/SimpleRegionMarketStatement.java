package de.ersterstreber.kontauszuege.statement;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class SimpleRegionMarketStatement {

	private String player;

	public SimpleRegionMarketStatement(String player) {
		this.player = player.toLowerCase();
	}

	public String getPlayer() {
		return player;
	}

	public double getExpenses(String date, boolean month) {
		List<String> auszuege = Kontoauszug.pconfig.getStringList("players."
				+ player);
		List<String> srmoutgoes = new ArrayList<String>();
		for (String s : auszuege) {
			if (s.contains("[-]") && s.contains("[GS]")) {
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
			String[] ss = splitted[1].split(" ");
			double price = 0;
			try {
				price = Double.parseDouble(ss[0].replace("�f", ""));
			} catch (NumberFormatException ex) {
				System.err.println(ss[0].replace("�f", "") + " could not be cast to a double!");
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
			if (s.contains("[+]") && s.contains("[GS]")) {
				if (month) {
					if (s.contains("." + date + "."))
						srmins.add(s);
				} else {
					if (s.contains("[" + date + "]"))
						srmins.add(s);
				}
			}
		}
		double iprice = 0;
		for (String s : srmins) {
			String[] splitted = s.split(": ");
			String[] ss = splitted[1].split(" ");
			double price = 0;
			try {
				price = Double.parseDouble(ss[0].replace("�f", ""));
			} catch (NumberFormatException ex) {
				System.err.println(ss[0].replace("�f", "") + " could not be cast to a double!");
				return price;
			}
			iprice += price;
		}
		return iprice;
	}
	
	public String getProfit(String date, boolean month) {
		double profit = getEarnings(date, month) - getExpenses(date, month);
		String s = null;
		DecimalFormat dc = new DecimalFormat("#.##");
		if (profit == 0.0) s = "�7[+/-]" + dc.format(profit);
		if (profit > 0.0) s = "�2[+]" + dc.format(profit);
		if (profit < 0.0) s = "�c[-]" + dc.format(profit * -1);
		
		return s + " SD";
	}
	
	public double getProfitAsDouble(String date, boolean month) {
		double profit = getEarnings(date, month) - getExpenses(date, month);
		DecimalFormat dc = new DecimalFormat("#.##");
		
		return Double.parseDouble(dc.format(profit));
	}
	
}
