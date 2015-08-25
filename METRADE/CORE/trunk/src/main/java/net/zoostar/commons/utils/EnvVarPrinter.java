package net.zoostar.commons.utils;

import java.util.Map;
import java.util.Properties;

public class EnvVarPrinter {

	public static void printEnvVar() {
		System.out.println("+---------------------------------------+");
		System.out.println("| Printing System Environment Variables |");
		System.out.println("+---------------------------------------+");
		Map<String, String> map = System.getenv();
		
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key));
		}

		System.out.println();
		System.out.println("+-------------------------------------+");
		System.out.println("| Printing Java Environment Variables |");
		System.out.println("+-------------------------------------+");
		Properties properties = System.getProperties();
		
		for(Object key : properties.keySet()) {
			System.out.println(key.toString() + ": " + properties.get(key));
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printEnvVar();
	}

}
