package com.qa.ims.utils;

import java.math.BigDecimal;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Money;

public class Utils {
	
	private static final Logger LOGGER = LogManager.getLogger();

	private static Scanner scanner;

	private static Utils util = null;
	
	private Utils() {
		scanner = new Scanner(System.in);
}
	
	public static Utils getInstance() {
		if(util == null) {
			util = new Utils();
		}
		return util;
	}

	public Long getLong() {
		String input = null;
		Long longInput = null;
		do {
			try {
				input = getString();
				longInput = Long.parseLong(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (longInput == null);
		return longInput;
	}

	public String getString() {
		return scanner.nextLine();
	}

	public Double getDouble() {
		String input = null;
		Double doubleInput = null;
		do {
			try {
				input = getString();
				doubleInput = Double.parseDouble(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (doubleInput == null);
		return doubleInput;
	}
	
	public Integer getInteger() {
		String input = null;
		Integer intInput = null;
		do {
			try {
				input = getString();
				intInput = Integer.parseInt(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while(intInput == null);
		return intInput;
	}
	
	public Money getValue() {
		Money money = null;
		String input = null;
		do {
			try {
				input = getString();
				money = Money.pounds(BigDecimal.valueOf(Double.valueOf(input)));
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a value");
			}
		} while(money == null);
		return money;	
	}
	public boolean getYesNo() {
		String input = null;
		boolean correctInput = false;
		boolean yesNo = false;
		do {
			try {
				input = getString();
				if(input.equalsIgnoreCase("yes")) {
					yesNo = true;
					correctInput = true;
				}else if(input.equals("no")) {
					yesNo = false;
					correctInput = true;
				} else {
					LOGGER.info("Error - Please enter yes or no");
				}
			} catch (Exception nfe) {
				LOGGER.info("Error - Please enter yes or no");
			}
		} while (!correctInput);
		return yesNo;
		
	}

}
