package de.ersterstreber.kontoauszuege.statements;

public enum StatementType {

	ALL(""),
	LOTTERY("[LOT]"),
	ECONOMY("[ECO]"),
	REGION("[GS]"),
	CHESTSHOP("[CS]");
	
	private String s;
	
	private StatementType(String s) {
		this.s = s;
	}
	
	public String getName() {
		return s;
	}
	
	public static StatementType fromString(String s) {
		if (s.equalsIgnoreCase("gs")) {
			return REGION;
		}
		if (s.equalsIgnoreCase("eco") || s.equalsIgnoreCase("economy")) {
			return ECONOMY;
		}
		if (s.equalsIgnoreCase("cs") || s.equalsIgnoreCase("chestshop")) {
			return CHESTSHOP;
		}
		if (s.equalsIgnoreCase("lot") || s.equalsIgnoreCase("lottery")) {
			return LOTTERY;
		}
		return null;
	}
	
}
