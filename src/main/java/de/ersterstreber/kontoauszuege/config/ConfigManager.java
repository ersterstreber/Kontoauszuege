package de.ersterstreber.kontoauszuege.config;

import de.ersterstreber.kontoauszuege.Main;

public class ConfigManager {

	public ConfigManager() {
		if (!Main.getInstance().getConfig().contains("mysql.host")) {
			Main.getInstance().getConfig().addDefault("mysql.host", "host");
			Main.getInstance().getConfig().options().copyDefaults(true);
			Main.getInstance().saveConfig();
		}
		
		if (!Main.getInstance().getConfig().contains("mysql.port")) {
			Main.getInstance().getConfig().addDefault("mysql.port", 3306);
			Main.getInstance().getConfig().options().copyDefaults(true);
			Main.getInstance().saveConfig();
		}
		
		if (!Main.getInstance().getConfig().contains("mysql.database")) {
			Main.getInstance().getConfig().addDefault("mysql.database", "database");
			Main.getInstance().getConfig().options().copyDefaults(true);
			Main.getInstance().saveConfig();
		}
		
		if (!Main.getInstance().getConfig().contains("mysql.username")) {
			Main.getInstance().getConfig().addDefault("mysql.username", "username");
			Main.getInstance().getConfig().options().copyDefaults(true);
			Main.getInstance().saveConfig();
		}
		
		if (!Main.getInstance().getConfig().contains("mysql.password")) {
			Main.getInstance().getConfig().addDefault("mysql.password", "password");
			Main.getInstance().getConfig().options().copyDefaults(true);
			Main.getInstance().saveConfig();
		}
	}
	
	public String getMySQLHost() {
		return Main.getInstance().getConfig().getString("mysql.host");
	}
	
	public int getMySQLPort() {
		return Main.getInstance().getConfig().getInt("mysql.port");
	}
	
	public String getMySQLDatabase() {
		return Main.getInstance().getConfig().getString("mysql.database");
	}
	
	public String getMySQLUsername() {
		return Main.getInstance().getConfig().getString("mysql.username");
	}
	
	public String getMySQLPassword() {
		return Main.getInstance().getConfig().getString("mysql.password");
	}
	
}
