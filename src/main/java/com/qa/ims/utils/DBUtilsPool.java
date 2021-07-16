package com.qa.ims.utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtilsPool {
	private static final Logger LOGGER = LogManager.getLogger();
	private static BasicDataSource basicDS;

	
	private DBUtilsPool(String properties) { 
			Properties dbProps = new Properties();
			basicDS = new BasicDataSource();
			try {
			InputStream inputStream = ClassLoader.getSystemResourceAsStream(properties);
			if(inputStream == null) {
				throw new IOException("File not found");
			}
			dbProps.load(inputStream);
			basicDS.setUrl(dbProps.getProperty("db.url", ""));
			basicDS.setUsername(dbProps.getProperty("db.user", ""));
			basicDS.setPassword(dbProps.getProperty("db.password", ""));
			} catch (IOException e) {
				LOGGER.error(e);
			}
	}
	private DBUtilsPool() {
		this("db.properties");
	}

	public int executeSQLFile(String file) {
		int modified = 0;
		try (Connection connection = getDataSource().getConnection();
				BufferedReader br = new BufferedReader(new FileReader(file));) {
			String fileAsString = br.lines().reduce((acc, next) -> acc + next).orElse("");
			String[] queries = fileAsString.split(";");
			modified += Stream.of(queries).map(string -> {
				try (Statement statement = connection.createStatement();) {
					return statement.executeUpdate(string);
				} catch (Exception e) {
					LOGGER.debug(e);
					return 0;
				}
			}).reduce((acc, next) -> acc + next).orElse(0);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return modified;
	}
	
	public int init(String... paths) {
		int modified = 0;

		for (String path : paths) {
			modified += executeSQLFile(path);
		}
		return modified;
	}
	
	
	private static DBUtilsPool instance = null;
	
	public static DataSource getDataSource() {
		if (instance == null) {
			try {
				instance = new DBUtilsPool();
			} catch (Exception e) {
				LOGGER.debug(e);
			}
		}
		return basicDS;
	}
	
	public static DBUtilsPool getInstance() {
		if (instance == null) {
			try {
				instance = new DBUtilsPool();
			} catch (Exception e) {
				LOGGER.debug(e);
			}
		}
		return instance;
	}
	
}


