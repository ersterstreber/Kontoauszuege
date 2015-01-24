package de.ersterstreber.kontoauszuege.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ersterstreber.kontoauszuege.Main;
import de.ersterstreber.kontoauszuege.statements.StatementManager;
import de.ersterstreber.kontoauszuege.statements.StatementType;

public class KontoCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cDiesen Befehl können nur Spieler ausführen!");
			return true;
		}
		if (!cmd.getName().equalsIgnoreCase("konto")) {
			sender.sendMessage("§c/k [-a anzahl] [-d datum] [-t typ]");
			return true;
		}
		Player p = (Player) sender;
		int amount = 10;
		StatementType type = StatementType.ALL;
		String date = Main.date();
		if (args.length == 0) {
			StatementManager.sendStatements(p, amount, type, date);
			return true;
		}
		if (args.length > 0) {
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
					type = StatementType.fromString(args[i + 1]);
					if (type == null) {
						p.sendMessage("§c/k [-a anzahl] [-d datum] [-t typ]");
						return true;
					}
				}
			}
			StatementManager.sendStatements(p, amount, type, date);
			return true;
		}
		p.sendMessage("§c/k [-a anzahl] [-d datum] [-t typ]");
		return true;
	}
}
