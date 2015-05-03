package de.timc.mcorelib.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MCoreSQL {
	// Hostname
	private String dbHost;
	// Port -- Standard: 3306
	private String dbPort;
	// Datenbankname
	private String database;
	// Datenbankuser
	private String dbUser;
	// Datenbankpasswort
	private String dbPassword;

	public Connection connection;

	public MCoreSQL(String dbHost,String port,String database,String dbUser,String dbPasswort) {
		this.dbHost = dbHost;
		this.dbPort = port;
		this.database = database;
		this.dbUser = dbUser;
		this.dbPassword = dbPasswort;
		connect();
	}

	public void connect() {
		try {
			if (connection == null || connection.isClosed()) {
				System.out.println("Connect....(SQL)");
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("[MCoreLib-SQL]Verbinde mit Datenbank " + dbHost + "@" + dbPort + " ...");
				this.connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + database + "?" + "user=" + dbUser + "&" + "password=" + dbPassword);
				System.out.println("[MCoreLib-SQL]Verbindung mit Datenbank erfolgreich!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("ERROR Connect nicht moeglich");
			System.out.println("ERROR " + e.getMessage());
		}
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public Connection getConnection() {
		if(connection != null){
			connect();
			return connection;
		}
		return null;
	}

	
}