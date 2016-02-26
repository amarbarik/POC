package za.co.dom.poc.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Stack;


public class ConnectionFactory {

	public static final String JDBC_DRIVER_PROP          = "JDBC_DRIVER_PROP";
	public static final String JDBC_URL_PROP             = "JDBC_URL_PROP";
	public static final String JDBC_USER_PROP            = "JDBC_USER_PROP";
	public static final String JDBC_PASSWORD_PROP        = "JDBC_PASSWORD_PROP";
	public static final String CONNECTION_POOL_SIZE_PROP = "CONNECTION_POOL_SIZE_PROP";
	
	private static String JDBC_DRIVER ;
	private static String JDBC_URL ;
	private static String JDBC_USER ;
	private static String JDBC_PASSWORD ;
	private static String CONNECTION_POOL_SIZE;
	
	private Stack<Connection> pool;
	private int maxSize = 10;
	private Supervisor supervisor;
	
	private static ConnectionFactory _instance;	
	
	private ConnectionFactory() {
		try {
			Class.forName(JDBC_DRIVER);
			maxSize = Integer.parseInt(CONNECTION_POOL_SIZE);
			pool = new Stack<Connection>();		
			supervisor = new Supervisor();
			supervisor.start();
			supervisor.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void configure(Properties prop){
		JDBC_DRIVER   = prop.getProperty(JDBC_DRIVER_PROP);
		JDBC_URL      = prop.getProperty(JDBC_URL_PROP);
		JDBC_USER     = prop.getProperty(JDBC_USER_PROP);
		JDBC_PASSWORD = prop.getProperty(JDBC_PASSWORD_PROP);
		CONNECTION_POOL_SIZE =prop.getProperty(CONNECTION_POOL_SIZE_PROP);
	}

	public static synchronized ConnectionFactory getInstance() {
		if (_instance == null) {
			_instance = new ConnectionFactory();
		}
		return _instance;
	}

	public synchronized Connection getConnection() {
		Connection aConnection = null;
		if (!supervisor.isAlive()) { 
			aConnection = pool.pop();
			if (pool.size()==0) {
				supervisor = new Supervisor();
				supervisor.start();				
			}
		}
		return aConnection;
	}
	
	
	private class Supervisor extends Thread {

		public synchronized void run() {
			Connection connection = null;
			for (int i = 0; i < maxSize; i++) {
				try {
					connection = DriverManager.getConnection(JDBC_URL,
							JDBC_USER, JDBC_PASSWORD);
					pool.push(connection);
				} catch (SQLException e) {
				}
			}
		}

	}

}
