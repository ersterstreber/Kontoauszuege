package de.ersterstreber.kontauszuege;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.ersterstreber.kontauszuege.commands.KontoCMD;
import de.ersterstreber.kontauszuege.commands.StatementCMD;
import de.ersterstreber.kontauszuege.listeners.AuszugListener;
import de.ersterstreber.kontauszuege.listeners.PlayerJoinListener;

public class Kontoauszug extends JavaPlugin {

	public static FileConfiguration pconfig;
	public static File pfile;

	// public static FileConfiguration kconfig;
	// public static File kfile;

	// public static net.milkbowl.vault.economy.Economy economy = null;

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new AuszugListener(), this);
		// Bukkit.getPluginManager().registerEvents(new GeschaeftListener(),
		// this);
		Bukkit.getPluginManager()
				.registerEvents(new PlayerJoinListener(), this);

		getCommand("konto").setExecutor(new KontoCMD());
		// getCommand("gkonto").setExecutor(new GKontoCMD());
		getCommand("statement").setExecutor(new StatementCMD());

		pfile = new File("plugins/Auszuege/", "players.yml");
		pconfig = YamlConfiguration.loadConfiguration(pfile);

		// kfile = new File("plugins/Auszuege/", "konten.yml");
		// kconfig = YamlConfiguration.loadConfiguration(kfile);

		// setupEconomy();
	}

	public static void add(String player, String s) {
		List<String> auszuege = pconfig.getStringList("players." + player);
		auszuege.add(s);
		pconfig.set("players." + player, auszuege);
		savePConfig();
		return;
	}

	public static void send(Player sendTo, int amount, String type,
			boolean isDate, String date) {
		List<String> auszug = pconfig.getStringList("players."
				+ sendTo.getName());
		if (!type.equalsIgnoreCase("ALL")) {
			sendTo.sendMessage("§7------" + type + " - Auszüge------");
		} else {
			sendTo.sendMessage("§7---- [Kontoauszüge] ----");
		}
		if (isDate)
			sendTo.sendMessage("§7------§7[" + date + "]§7------");
		List<Integer> ints = new ArrayList<Integer>();
		int b = auszug.size() - amount;
		if (b < 0)
			b = 0;
		for (int i = b; i < auszug.size(); i++) {
			int a = 1;
			if (ints.contains(i))
				continue;
			if (i >= auszug.size())
				break;
			if (!type.equalsIgnoreCase("ALL")) {
				if (isDate) {
					if (!auszug.get(i).contains("§7[" + date + "]")
							|| !auszug.get(i).contains(type)) {
						continue;
					}
				} else {
					if (!auszug.get(i).contains(type)) {
						continue;
					}
				}
			}
			int ii = i + 1;
			while (ii < auszug.size()) {
				if (auszug.get(i).equalsIgnoreCase(auszug.get(ii))) {
					a++;
					ints.add(ii);
					ii++;
				} else {
					break;
				}
			}
			sendTo.sendMessage(auszug.get(i) + "§7[x" + a + "]");
		}
		sendTo.sendMessage("§7----------------------");
	}

	public static void savePConfig() {
		try {
			pconfig.save(pfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public static FileConfiguration getKConfig() {
	// return kconfig;
	// }
	//
	// public static void saveKConfig() {
	// try {
	// kconfig.save(kfile);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public static void addEntry(String s) {
	// if (!hasEntry(s)) {
	// getKConfig().addDefault("players." + s + ".chestshop", false);
	// getKConfig().addDefault("players." + s + ".srm", false);
	// getKConfig().options().copyDefaults(true);
	// saveKConfig();
	// }
	// }
	//
	// public static boolean hasEntry(String s) {
	// if (getKConfig().contains("players." + s))
	// return true;
	// return false;
	// }
	//
	// public static boolean isChestShopEnabled(String s) {
	// if (hasEntry(s)) {
	// if (getKConfig().getBoolean("players." + s + ".chestshop") == true)
	// return true;
	// return false;
	// } else {
	// addEntry(s);
	// return false;
	// }
	// }
	//
	// public static void setChestShopEnabled(String s, boolean b) {
	// if (hasEntry(s)) {
	// getKConfig().set("players." + s + ".chestshop", b);
	// saveKConfig();
	// } else {
	// addEntry(s);
	// getKConfig().set("players." + s + ".chestshop", b);
	// saveKConfig();
	// }
	// }
	//
	// public static boolean isSRMEnabled(String s) {
	// if (hasEntry(s)) {
	// if (getKConfig().getBoolean("players." + s + ".srm") == true)
	// return true;
	// return false;
	// } else {
	// addEntry(s);
	// return false;
	// }
	// }
	//
	// public static void setSRMEnabled(String s, boolean b) {
	// if (hasEntry(s)) {
	// getKConfig().set("players." + s + ".srm", b);
	// saveKConfig();
	// } else {
	// addEntry(s);
	// getKConfig().set("players." + s + ".srm", b);
	// saveKConfig();
	// }
	// }
	//
	// private boolean setupEconomy() {
	// RegisteredServiceProvider<net.milkbowl.vault.economy.Economy>
	// economyProvider = getServer()
	// .getServicesManager().getRegistration(
	// net.milkbowl.vault.economy.Economy.class);
	// if (economyProvider != null) {
	// economy = economyProvider.getProvider();
	// }
	//
	// return (economy != null);
	// }
	//
	// public static boolean hasGKonto(Player p) {
	// if (economy.isBankOwner(p.getName() + "%gkonto", p) != null)
	// return true;
	// return false;
	// }
	//
	// public static void addGKonto(Player p) {
	// if (!hasGKonto(p)) {
	// economy.createBank(p.getName() + "%gkonto", p);
	// }
	// }

}
