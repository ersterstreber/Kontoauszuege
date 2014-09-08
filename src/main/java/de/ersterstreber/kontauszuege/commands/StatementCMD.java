package de.ersterstreber.kontauszuege.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ersterstreber.kontauszuege.statement.ChestShopStatement;
import de.ersterstreber.kontauszuege.statement.CraftConomyStatement;
import de.ersterstreber.kontauszuege.statement.SimpleRegionMarketStatement;

public class StatementCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player))
			return true;
		if (cmd.getName().equalsIgnoreCase("statement")) {
			Player p = (Player) sender;
			
			//-----CraftConomy-----//
			
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("tag") || args[0].equalsIgnoreCase("t")) {
					if (args[1].equalsIgnoreCase("ico")
							|| args[1].equalsIgnoreCase("ic")
							|| args[1].equalsIgnoreCase("iconomy")) {
						p.sendMessage("§7Deine Statistik für den " + args[2] + " :");
						p.sendMessage(new CraftConomyStatement(p.getName())
								.getProfit(args[2], false) + "");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("month") || args[0].equalsIgnoreCase("monat") || args[0].equalsIgnoreCase("m")) {
					if (args[1].equalsIgnoreCase("ico")
							|| args[1].equalsIgnoreCase("ic")
							|| args[1].equalsIgnoreCase("iconomy")) {
						p.sendMessage("§7Deine Statistik für den Monat: " + args[2]);
						p.sendMessage(new CraftConomyStatement(p.getName())
								.getProfit(args[2], true) + "");
						return true;
					}
				}
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("ico")
						|| args[0].equalsIgnoreCase("ic")
						|| args[0].equalsIgnoreCase("iconomy")) {
					p.sendMessage("§7Deine Statistik für heute:");
					p.sendMessage(new CraftConomyStatement(p.getName())
							.getProfit(getDate(), false) + "");
					return true;
				}
			}
			
			//-----SimpleRegionMarket-----//
			
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("tag") || args[0].equalsIgnoreCase("t")) {
					if (args[1].equalsIgnoreCase("gs")
							|| args[1].equalsIgnoreCase("srm")
							|| args[1].equalsIgnoreCase("simpleregionmarket")) {
						p.sendMessage("§7Deine Statistik für den " + args[2] + " :");
						p.sendMessage(new SimpleRegionMarketStatement(p.getName())
								.getProfit(args[2], false) + "");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("month") || args[0].equalsIgnoreCase("monat") || args[0].equalsIgnoreCase("m")) {
					if (args[1].equalsIgnoreCase("srm")
							|| args[1].equalsIgnoreCase("simpleregionmarket")
							|| args[1].equalsIgnoreCase("gs")) {
						p.sendMessage("§7Deine Statistik für den Monat: " + args[2]);
						p.sendMessage(new SimpleRegionMarketStatement(p.getName())
								.getProfit(args[2], true) + "");
						return true;
					}
				}
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("gs")
						|| args[0].equalsIgnoreCase("srm")
						|| args[0].equalsIgnoreCase("simpleregionmarket")) {
					p.sendMessage("§7Deine Statistik für heute:");
					p.sendMessage(new SimpleRegionMarketStatement(p.getName())
							.getProfit(getDate(), false) + "");
					return true;
				}
			}
			
			//-----ChestShop-----//
			
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("day") || args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("tag") || args[0].equalsIgnoreCase("t")) {
					if (args[1].equalsIgnoreCase("cs")
							|| args[1].equalsIgnoreCase("chest")
							|| args[1].equalsIgnoreCase("chestshop")) {
						p.sendMessage("§7Deine Statistik für den " + args[2] + " :");
						p.sendMessage(new ChestShopStatement(p.getName())
								.getProfit(args[2], false) + "");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("month") || args[0].equalsIgnoreCase("monat") || args[0].equalsIgnoreCase("m")) {
					if (args[1].equalsIgnoreCase("cs")
							|| args[1].equalsIgnoreCase("chest")
							|| args[1].equalsIgnoreCase("chestshop")) {
						p.sendMessage("§7Deine Statistik für den Monat: " + args[2]);
						p.sendMessage(new ChestShopStatement(p.getName())
								.getProfit(args[2], true) + "");
						return true;
					}
				}
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("cs")
						|| args[0].equalsIgnoreCase("chest")
						|| args[0].equalsIgnoreCase("chestshop")) {
					p.sendMessage("§7Deine Statistik für heute:");
					p.sendMessage(new ChestShopStatement(p.getName())
							.getProfit(getDate(), false) + "");
					return true;
				}
			}
			
		}
		return true;
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
}
