package utils;

import java.sql.Connection;

public class ConnectionUtil {
	private static final ThreadLocal<Connection> connections = new ThreadLocal<>();
	private ConnectionUtil() {	
	}
	public static Connection getConnection() {
		return connections.get();
	}
	public static void setConnection(Connection connection) {
		connections.set(connection);
	}
	public static void clearConnection() {
		connections.remove();
	}
}
