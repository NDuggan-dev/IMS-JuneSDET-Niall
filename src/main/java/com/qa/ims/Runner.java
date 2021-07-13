package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

public class Runner {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final IMS ims = new IMS();
	
	public static void main(String[] args) {
		ims.imsSystem();
		LOGGER.info("SO LONG!");
	}

}
