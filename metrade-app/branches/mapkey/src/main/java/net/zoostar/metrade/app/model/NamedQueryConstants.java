package net.zoostar.metrade.app.model;

public interface NamedQueryConstants {
	String FIND_CLIENT_BY_EMAIL = "Client.findByEmail";
	String FIND_ACTIVITIES_BY_CLIENT = "Activity.findByClient";
	
	String FIND_STOCK_BY_SYMBOL = "Stock.findBySymbol";
	String ARG_SYMBOL = "symbol";
}
