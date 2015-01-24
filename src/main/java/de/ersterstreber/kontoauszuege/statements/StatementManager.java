package de.ersterstreber.kontoauszuege.statements;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import de.ersterstreber.kontoauszuege.mysql.MySQL;

public class StatementManager {
	
	public static void onLoad() {
		MySQL.updateQuery("CREATE TABLE IF NOT EXISTS statements (uuid VARCHAR(50), statements TEXT, PRIMARY KEY (uuid))");
	}

	/**
	 * @param uuid - uuid of player
	 * @return - statements associated with this uuid
	 */
	public static List<String> getFormattedStatements(UUID uuid) {
		try {
			String unformatted = MySQL.executeQuery("SELECT statements FROM statements WHERE uuid='" + uuid.toString() + "'").getString("statements");
			List<String> toReturn = new ArrayList<String>();
			for (String s : unformatted.split(";;")) {
				toReturn.add(s);
			}
			Collections.reverse(toReturn);
			return toReturn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getUnformattedStatements(UUID uuid) throws SQLException {
		return MySQL.executeQuery("SELECT statements FROM statements WHERE uuid='" + uuid.toString() + "'").getString("statements");
	}
	
	
	/**
	 * @param uuid - uuid of player
	 * @param statement - statement to save
	 */
	public static void saveStatement(UUID uuid, String statement) {
		try {
			statement = getUnformattedStatements(uuid) + ";;" + statement;
			MySQL.updateQuery("UPDATE statements SET statements='" + statement + "' WHERE uuid='" + uuid.toString() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param p - Player to send the statements to
	 * @param amount - Amount of statements
	 * @param type - Type of statements
	 * @param date - date of statements
	 */
	public static void sendStatements(Player p, int amount, StatementType type, String date) {
		List<String> send = new ArrayList<String>();
		List<String> statements = getFormattedStatements(p.getUniqueId());
		for (int i = 0 ; i < amount ; i++) {
			if (!(statements.size() > i))
				break;
			if (statements.get(i).contains(type.getName()) && statements.get(i).contains(date)) {
				send.add(statements.get(i));
			} else {
				amount++;
			}
		}
		p.sendMessage("§7===[Kontoauszüge]===");
		List<String> alreadySent = new ArrayList<String>();
		for (String s : send) {
			if (alreadySent.contains(s))
				continue;
			alreadySent.add(s);
			p.sendMessage(s + "§7[x" + often(send, s) + "]");
		}
		p.sendMessage("§7===[ENDE]===");
	}
	
	/**
	 * @param end - List to search in
	 * @param searched - Object to search for
	 * @return - Number of times that object occurs
	 */
	public static int often(List<String> end, String searched) {
	    int numCount = 0;
	    for (String s : end) {
	        if (s.equalsIgnoreCase(searched)) {
	            numCount ++ ;
	        }
	    }
	    return numCount;
	}
	
}
