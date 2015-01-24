package de.ersterstreber.kontoauszuege.listeners;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ersterstreber.kontoauszuege.mysql.MySQL;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		ResultSet rs = MySQL.executeQuery("SELECT * FROM statements WHERE uuid='" + e.getPlayer().getUniqueId().toString() + "'");
		try {
			if (!rs.first()) {
				String empty = "";
				MySQL.updateQuery("INSERT INTO statements (uuid, statements) VALUES ('" + e.getPlayer().getUniqueId().toString() + "', '" + empty + "')");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
