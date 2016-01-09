package parser.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection used to store data on items
 * 
 * @project GEParser
 * @author Alexander Sniffin
 * @date Jan 8, 2016
 */
public class MySQLConnection {

	private Connection conn = null;
	private String IP, user, pass;

	public MySQLConnection(String IP, String user, String pass) {
		this.IP = IP;
		this.user = user;
		this.pass = pass;
	}

	public boolean establish() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + IP + ":3306/OSRS", user, pass);
			return true;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
