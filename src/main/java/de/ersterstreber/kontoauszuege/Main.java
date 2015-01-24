package de.ersterstreber.kontoauszuege;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.ersterstreber.kontoauszuege.commands.KontoCMD;
import de.ersterstreber.kontoauszuege.config.ConfigManager;
import de.ersterstreber.kontoauszuege.listeners.ChestShopListener;
import de.ersterstreber.kontoauszuege.listeners.CraftConomyListener;
import de.ersterstreber.kontoauszuege.listeners.LotteryListener;
import de.ersterstreber.kontoauszuege.listeners.PlayerJoinListener;
import de.ersterstreber.kontoauszuege.listeners.SimpleRegionMarketListener;
import de.ersterstreber.kontoauszuege.mysql.MySQL;
import de.ersterstreber.kontoauszuege.statements.StatementManager;

public class Main extends JavaPlugin {

	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		ConfigManager config = new ConfigManager();
		
		new MySQL(config.getMySQLHost(), config.getMySQLPort(), config.getMySQLDatabase(), config.getMySQLUsername(), config.getMySQLPassword());
		
		Bukkit.getPluginManager().registerEvents(new SimpleRegionMarketListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChestShopListener(), this);
		Bukkit.getPluginManager().registerEvents(new CraftConomyListener(), this);
		Bukkit.getPluginManager().registerEvents(new LotteryListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		
		getCommand("konto").setExecutor(new KontoCMD());
		
		StatementManager.onLoad();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public static String date() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
