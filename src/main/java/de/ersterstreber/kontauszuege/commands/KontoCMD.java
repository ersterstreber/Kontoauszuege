package de.ersterstreber.kontauszuege.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ersterstreber.kontauszuege.Kontoauszug;

public class KontoCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cDu musst ein Spieler sein, um das zu tun!");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("auszug.read")) {
			p.sendMessage("§cDu hast kein Recht dazu!");
			return true;
		}
		String date = null;
		String type = "all";
		int amount = 10;
		for (int i = 0 ; i < args.length ; i++) {
			if (args[i].equalsIgnoreCase("-a")) {
				try {
					amount = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException ex) {
					p.sendMessage("§c" + args[i + 1] + " ist keine Zahl oder zu groß!");
					return true;
				}
			}
			if (args[i].equalsIgnoreCase("-d")) {
				date = args[i + 1];
			}
			if (args[i].equalsIgnoreCase("-t")) {
				type = args[i + 1];
			}
		}
		if (cmd.getName().equalsIgnoreCase("konto")) {
			if (date == null) {
				if (type.equalsIgnoreCase("all")) {
					Kontoauszug.send(p, amount, "ALL", false, "");
					return true;
				}
				if (type.equalsIgnoreCase("iconomy")
						|| type.equalsIgnoreCase("ico")
						|| type.equalsIgnoreCase("ic")) {
					Kontoauszug.send(p, amount, "[IC]", false, "");
					return true;
				}
				if (type.equalsIgnoreCase("chestshop")
						|| type.equalsIgnoreCase("cs")) {
					Kontoauszug.send(p, amount, "[CS]", false, "");
					return true;
				}
				if (type.equalsIgnoreCase("gs")
						|| type.equalsIgnoreCase("srm")) {
					Kontoauszug.send(p, amount, "[GS]", false, "");
					return true;
				}
			} else {
				if (type.equalsIgnoreCase("all")) {
					Kontoauszug.send(p, amount, "ALL", true, date);
					return true;
				}
				if (type.equalsIgnoreCase("iconomy")
						|| type.equalsIgnoreCase("ico")
						|| type.equalsIgnoreCase("ic")) {
					Kontoauszug.send(p, amount, "[IC]", true, date);
					return true;
				}
				if (type.equalsIgnoreCase("chestshop")
						|| type.equalsIgnoreCase("cs")) {
					Kontoauszug.send(p, amount, "[CS]", true, date);
					return true;
				}
				if (type.equalsIgnoreCase("gs")
						|| type.equalsIgnoreCase("srm")) {
					Kontoauszug.send(p, amount, "[GS]", true, date);
					return true;
				}
			}
			p.sendMessage("§c/k <parameter>");
			return true;
		}
		return true;
	}
}
