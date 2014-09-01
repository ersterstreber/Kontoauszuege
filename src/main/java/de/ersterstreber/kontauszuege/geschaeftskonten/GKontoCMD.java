package de.ersterstreber.kontauszuege.geschaeftskonten;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GKontoCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
//		if (!(sender instanceof Player))
//			return true;
//		if (cmd.getName().equalsIgnoreCase("gkonto")) {
//			Player p = (Player) sender;
//			if (p.hasPermission("gkonto.use")) {
//				if (Kontoauszug.hasGKonto(p)) {
//					if (args.length == 0) {
//						p.sendMessage("§2[§fMoney§a] §2Geschäftskonto: §f"
//								+ Kontoauszug.economy.bankBalance(p.getName() + "%gkonto").balance + " SD");
//						return true;
//					}
//					if (args.length == 2) {
//						if (args[0].equalsIgnoreCase("pay")) {
//							double amount;
//							try {
//								amount = Double.parseDouble(args[1]);
//							} catch (NumberFormatException ex) {
//								p.sendMessage("§c" + args[1] + " ist keine Zahl!");
//								return true;
//							}
//							if (Kontoauszug.economy.getBalance(p) >= amount) {
//								Kontoauszug.economy.withdrawPlayer(p, amount);
//								Kontoauszug.economy.bankDeposit(p.getName() + "%gkonto", amount);
//								p.sendMessage("§2Deinem Geschäftskonto wurden §f" + amount + " SD §2hinzugefügt");
//								return true;
//							} else {
//								p.sendMessage("§cDu hast nicht genügend Geld!");
//								return true;
//							}
//						}
//						if (args[0].equalsIgnoreCase("take")) {
//							double amount;
//							try {
//								amount = Double.parseDouble(args[1]);
//							} catch (NumberFormatException ex) {
//								p.sendMessage("§c" + args[1] + " ist keine Zahl!");
//								return true;
//							}
//							if (Kontoauszug.economy.bankBalance(p.getName() + "%gkonto").balance >= amount) {
//								Kontoauszug.economy.bankWithdraw(p.getName() + "%gkonto", amount);
//								Kontoauszug.economy.depositPlayer(p, amount);
//								p.sendMessage("§2Deinem Konto wurden §f" + amount + " SD §2hinzugefügt");
//								return true;
//							} else {
//								p.sendMessage("§cDein Geschäftskonto hat nicht genügend Geld!");
//								return true;
//							}
//						}
//					}
//					if (args.length == 3) {
//						if (args[0].equalsIgnoreCase("set")) {
//							if (args[1].equalsIgnoreCase("cs") || args[1].equalsIgnoreCase("chestshop")) {
//								boolean b;
//								try {
//									b = Boolean.parseBoolean(args[2]);
//								} catch (Exception ex) {
//									p.sendMessage("§c" + args[2] + " darf nur true/false sein!");
//									return true;
//								}
//								Kontoauszug.setChestShopEnabled(p.getName(), b);
//								if (b == true) {
//									p.sendMessage("§2Die Einnahmen aus deinen ChestShops werden nun deinem Geschäftskonto gutgeschrieben.");
//									return true;
//								} else {
//									p.sendMessage("§2Die Einnahmen aus deinen ChestShops werden nun nicht mehr deinem Geschäftskonto gutgeschrieben.");
//									return true;
//								}
//							}
//						}
//					}
//					
//					p.sendMessage("§cDer Kommand wurde nicht gefunden!");
//					p.sendMessage("§c/gk");
//					p.sendMessage("§c/gk get <betrag>");
//					p.sendMessage("§c/gk pay <betrag>");
//					p.sendMessage("§c/gk set cs <true/false>");
//				} else {
//					p.sendMessage("§cDu hast kein Geschäftskonto!\nBitte wende dich an ersterstreber!");
//					return true;
//				}
//
//			} else {
//				p.sendMessage("§cDu hast kein Recht dazu!");
//				return true;
//			}
//		}
		return false;
	}
}
