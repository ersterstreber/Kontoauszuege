package de.ersterstreber.kontauszuege;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.ersterstreber.kontauszuege.commands.KontoCMD;
import de.ersterstreber.kontauszuege.commands.StatementCMD;
import de.ersterstreber.kontauszuege.listeners.AuszugListener;
import de.ersterstreber.kontauszuege.listeners.PlayerJoinListener;

public class Kontoauszug extends JavaPlugin {

	public static FileConfiguration pconfig;
	public static File pfile;

	private static Kontoauszug ka;

	public static Map<String, List<String>> toAdd;

	// public static FileConfiguration kconfig;
	// public static File kfile;

	// public static net.milkbowl.vault.economy.Economy economy = null;

	@Override
	public void onEnable() {

		ka = this;
		
		toAdd = new HashMap<String, List<String>>();

		Bukkit.getPluginManager().registerEvents(new AuszugListener(), this);
		// Bukkit.getPluginManager().registerEvents(new GeschaeftListener(),
		// this);
		Bukkit.getPluginManager()
				.registerEvents(new PlayerJoinListener(), this);

		getCommand("konto").setExecutor(new KontoCMD());
		// getCommand("gkonto").setExecutor(new GKontoCMD());
		getCommand("statement").setExecutor(new StatementCMD());

		add();

		pfile = new File("plugins/Auszuege/", "players.yml");
		pconfig = YamlConfiguration.loadConfiguration(pfile);

		// kfile = new File("plugins/Auszuege/", "konten.yml");
		// kconfig = YamlConfiguration.loadConfiguration(kfile);

		// setupEconomy();
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Writing into file...");
		add();
		getLogger().info("Done!");
	}

	public static void prepareAdding(final String player, final String s) {
		System.out.println(player.toLowerCase());
		if (!toAdd.containsKey(player.toLowerCase())) {
			toAdd.put(player.toLowerCase(), new ArrayList<String>());
		}
		List<String> list = toAdd.get(player.toLowerCase());
		list.add(s);
		toAdd.remove(player.toLowerCase());
		toAdd.put(player.toLowerCase(), list);
	}

	@SuppressWarnings("deprecation")
	public static void add() {
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(Kontoauszug.getInstance(), new BukkitRunnable() {

			public void run() {
				Kontoauszug.getInstance().getLogger().warning("Started adding to file.");
				Kontoauszug.getInstance().getLogger().info("Current TPS: ");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tps");
				Iterator<Entry<String, List<String>>> it = toAdd.entrySet().iterator();
				while (it.hasNext()) {
					@SuppressWarnings("rawtypes")
					Map.Entry pairs = (Map.Entry) it.next();
					List<String> auszuege = null;
					if (pconfig.contains("players." + pairs.getKey().toString().toLowerCase())) {
						auszuege = pconfig.getStringList("players." + pairs.getKey().toString().toLowerCase());
					} else {
						pconfig.addDefault("players." + pairs.getKey().toString().toLowerCase(), new ArrayList<String>());
						pconfig.options().copyDefaults(true);
						savePConfig();
						auszuege = new ArrayList<String>();
					}
					for (String s : toAdd.get(pairs.getKey().toString().toLowerCase())) {
						auszuege.add(s);
					}
					pconfig.set("players." + pairs.getKey().toString().toLowerCase(), auszuege);
					savePConfig();
					it.remove();
				}
				toAdd.clear();
				for (Player p : Bukkit.getOnlinePlayers()) {
					toAdd.put(p.getName().toLowerCase(), new ArrayList<String>());
				}
				Kontoauszug.getInstance().getLogger().info("Finished adding to file.");
				Kontoauszug.getInstance().getLogger().info("Current TPS: ");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tps");
			}
		}, 300 * 20L, 300 * 20L);
		
	}

	@SuppressWarnings("deprecation")
	public static void send(final Player sendTo, final int amount,
			final String type, final boolean isDate, final String date) {
		Bukkit.getScheduler().scheduleAsyncDelayedTask(getInstance(), new BukkitRunnable() {

			public void run() {
				List<String> auszug = pconfig.getStringList("players."
						+ sendTo.getName().toLowerCase());
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
		});
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

	public static Kontoauszug getInstance() {
		return ka;
	}

}
