package de.ersterstreber.kontauszuege.commands;

import java.text.DateFormat;
import java.text.DecimalFormat;
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

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("month")) {
					if (!args[1].equalsIgnoreCase("in") && !args[1].equalsIgnoreCase("out")) {
						if (args[1].equalsIgnoreCase("all")) {
							//In und Out
							try {
								Integer.parseInt(args[2]);
							} catch (NumberFormatException ex) {
								p.sendMessage("§c" + args[2] + " ist keine Zahl!");
								return true;
							}
							CraftConomyStatement cst = new CraftConomyStatement(p.getName());
							ChestShopStatement chst = new ChestShopStatement(p.getName());
							SimpleRegionMarketStatement sst = new SimpleRegionMarketStatement(p.getName());
							p.sendMessage("§7Deine Statistiken für den Monat " + args[2] + ":");
							double ico = 0;
							double cs = 0;
							double srm = 0;
							ico = Math.abs(cst.getProfitAsDouble(args[2], true));
							cs = Math.abs(chst.getProfitAsDouble(args[2], true));
							srm = Math.abs(sst.getProfitAsDouble(args[2], true));
							
							double gesamt = ico + cs + srm;
							if (gesamt != 0) {
								double icoanteil = Double.parseDouble(new DecimalFormat("#.##").format((ico / gesamt) * 100));
								double csanteil = Double.parseDouble(new DecimalFormat("#.##").format((cs / gesamt) * 100));
								double srmanteil = Double.parseDouble(new DecimalFormat("#.##").format((srm / gesamt) * 100));
								p.sendMessage("§7[ICO] §f" + cst.getProfit(args[2], true) + "§7[" + icoanteil + "%]");
								p.sendMessage("§7[CS] §f" + chst.getProfit(args[2], true) + "§7[" + csanteil + "%]");
								p.sendMessage("§7[GS] §f" + sst.getProfit(args[2], true) + "§7[" + srmanteil + "%]");
									return true;
							} else {
								p.sendMessage("§7Du hast in diesem Monat weder Einnahmen, noch Ausgaben.");
								return true;
							}
						} else {
							p.sendMessage("§cCommand nicht bekannt.");
							return true;
						}
					}
					String s = args[1];
					int i = 0;
					try {
						i = Integer.parseInt(args[2]);
					} catch (NumberFormatException ex) {
						p.sendMessage("§c" + args[2] + " ist keine Zahl!");
						return true;
					}
					CraftConomyStatement cst = new CraftConomyStatement(p.getName());
					ChestShopStatement chst = new ChestShopStatement(p.getName());
					SimpleRegionMarketStatement sst = new SimpleRegionMarketStatement(p.getName());
					if (s.equalsIgnoreCase("in")) {
						p.sendMessage("§7Deine Einnahmen vom Monat " + i + ":");
					} else {
						p.sendMessage("§7Deine Ausgaben vom Monat " + i + ":");
					}
					double ico = 0;
					double cs = 0;
					double srm = 0;
					if (s.equalsIgnoreCase("out")) {
						ico = cst.getExpenses(args[2], true);
						cs = chst.getExpenses(args[2], true);
						srm = sst.getExpenses(args[2], true);
					} else {
						ico = cst.getEarnings(args[2], true);
						cs = chst.getEarnings(args[2], true);
						srm = sst.getEarnings(args[2], true);
					}
					
					double gesamt = ico + cs + srm;
					if (gesamt != 0) {
						double icoanteil = Double.parseDouble(new DecimalFormat("#.##").format((ico / gesamt) * 100));
						double csanteil = Double.parseDouble(new DecimalFormat("#.##").format((cs / gesamt) * 100));
						double srmanteil = Double.parseDouble(new DecimalFormat("#.##").format((srm / gesamt) * 100));
						if (s.equalsIgnoreCase("out")) {
							p.sendMessage("§7[ICO] §f" + cst.getExpenses(args[2], true) + "§7[" + icoanteil + "%]");
							p.sendMessage("§7[CS] §f" + chst.getExpenses(args[2], true) + "§7[" + csanteil + "%]");
							p.sendMessage("§7[GS] §f" + sst.getExpenses(args[2], true) + "§7[" + srmanteil + "%]");
							return true;
						} else {
							p.sendMessage("§7[ICO] §f" + cst.getEarnings(args[2], true) + "§7[" + icoanteil + "%]");
							p.sendMessage("§7[CS] §f" + chst.getEarnings(args[2], true) + "§7[" + csanteil + "%]");
							p.sendMessage("§7[GS] §f" + sst.getEarnings(args[2], true) + "§7[" + srmanteil + "%]");
							return true;
						}
					} else {
						if (s.equalsIgnoreCase("out")) {
							p.sendMessage("§7Du hast in diesem Monat keine Ausgaben.");
							return true;
						} else {
							p.sendMessage("§7Du hast in diesem Monat keine Einnahmen.");
							return true;
						}
					}
				}
			}
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("in") && args[0].equalsIgnoreCase("out")) {
					String s = args[0];
					CraftConomyStatement cst = new CraftConomyStatement(p.getName());
					ChestShopStatement chst = new ChestShopStatement(p.getName());
					SimpleRegionMarketStatement sst = new SimpleRegionMarketStatement(p.getName());
					if (s.equalsIgnoreCase("in")) {
						p.sendMessage("§7Deine Einnahmen von heute:");
					} else {
						p.sendMessage("§7Deine Ausgaben von heute:");
					}
					double ico = 0;
					double cs = 0;
					double srm = 0;
					if (s.equalsIgnoreCase("out")) {
						ico = cst.getExpenses(getDate(), false);
						cs = chst.getExpenses(getDate(), false);
						srm = sst.getExpenses(getDate(), false);
					} else {
						ico = cst.getEarnings(getDate(), false);
						cs = chst.getEarnings(getDate(), false);
						srm = sst.getEarnings(getDate(), false);
					}
					
					double gesamt = ico + cs + srm;
					if (gesamt != 0) {
						double icoanteil = Double.parseDouble(new DecimalFormat("#.##").format((ico / gesamt) * 100));
						double csanteil = Double.parseDouble(new DecimalFormat("#.##").format((cs / gesamt) * 100));
						double srmanteil = Double.parseDouble(new DecimalFormat("#.##").format((srm / gesamt) * 100));
						if (s.equalsIgnoreCase("out")) {
							p.sendMessage("§7[ICO] §f" + cst.getExpenses(getDate(), false) + "§7[" + icoanteil + "%]");
							p.sendMessage("§7[CS] §f" + chst.getExpenses(getDate(), false) + "§7[" + csanteil + "%]");
							p.sendMessage("§7[GS] §f" + sst.getExpenses(getDate(), false) + "§7[" + srmanteil + "%]");
							return true;
						} else {
							p.sendMessage("§7[ICO] §f" + cst.getEarnings(getDate(), false) + "§7[" + icoanteil + "%]");
							p.sendMessage("§7[CS] §f" + chst.getEarnings(getDate(), false) + "§7[" + csanteil + "%]");
							p.sendMessage("§7[GS] §f" + sst.getEarnings(getDate(), false) + "§7[" + srmanteil + "%]");
							return true;
						}
					} else {
						if (s.equalsIgnoreCase("out")) {
							p.sendMessage("§7Du hast heute noch keine Ausgaben.");
							return true;
						} else {
							p.sendMessage("§7Du hast heute noch keine Einnahmen.");
							return true;
						}
					}
				}
			}
			
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("all")) {
					CraftConomyStatement cst = new CraftConomyStatement(p.getName());
					ChestShopStatement chst = new ChestShopStatement(p.getName());
					SimpleRegionMarketStatement sst = new SimpleRegionMarketStatement(p.getName());
					p.sendMessage("§7Deine Statistiken für heute:");
					double ico = 0;
					double cs = 0;
					double srm = 0;
					ico = Math.abs(cst.getProfitAsDouble(getDate(), false));
					cs = Math.abs(chst.getProfitAsDouble(getDate(), false));
					srm = Math.abs(sst.getProfitAsDouble(getDate(), false));
					
					double gesamt = ico + cs + srm;
					if (gesamt != 0) {
						double icoanteil = Double.parseDouble(new DecimalFormat("#.##").format((ico / gesamt) * 100));
						double csanteil = Double.parseDouble(new DecimalFormat("#.##").format((cs / gesamt) * 100));
						double srmanteil = Double.parseDouble(new DecimalFormat("#.##").format((srm / gesamt) * 100));
						p.sendMessage("§7[ICO] §f" + cst.getProfit(getDate(), false) + "§7[" + icoanteil + "%]");
						p.sendMessage("§7[CS] §f" + chst.getProfit(getDate(), false) + "§7[" + csanteil + "%]");
						p.sendMessage("§7[GS] §f" + sst.getProfit(getDate(), false) + "§7[" + srmanteil + "%]");
						return true;
					} else {
						p.sendMessage("§7Du hast heute weder Einnahmen, noch Ausgaben.");
						return true;
					}
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
