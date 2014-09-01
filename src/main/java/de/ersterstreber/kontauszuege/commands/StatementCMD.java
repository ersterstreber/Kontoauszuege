package de.ersterstreber.kontauszuege.commands;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ersterstreber.kontauszuege.statement.IConomyStatement;

public class StatementCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player))
			return true;
		if (cmd.getName().equalsIgnoreCase("statement")) {
			Player p = (Player) sender;
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("day")) {
					if (args[1].equalsIgnoreCase("ico")
							|| args[1].equalsIgnoreCase("ic")
							|| args[1].equalsIgnoreCase("iconomy")) {
						p.sendMessage("§7Deine Statistik für den " + args[2] + " :");
						p.sendMessage(new IConomyStatement(p.getName())
								.getProfit(args[2], false) + "");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("month")) {
					if (args[1].equalsIgnoreCase("ico")
							|| args[1].equalsIgnoreCase("ic")
							|| args[1].equalsIgnoreCase("iconomy")) {
						p.sendMessage("§7Deine Statistik für den Monat: " + args[2]);
						p.sendMessage(new IConomyStatement(p.getName())
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
					p.sendMessage(new IConomyStatement(p.getName())
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
