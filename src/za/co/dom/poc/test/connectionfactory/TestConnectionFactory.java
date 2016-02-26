package za.co.dom.poc.test.connectionfactory;

import za.co.dom.poc.connectionfactory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class TestConnectionFactory extends Thread {
	
    public static int failureCounter = 0;
	
	public int idNumber ;
	
	public TestConnectionFactory(int idNumber){
		this.idNumber = idNumber;
	}
	public void run() {		
		
		ConnectionFactory factory = ConnectionFactory.getInstance();
		Connection con  = factory.getConnection();
		System.out.println(idNumber + " : " + con);
		if(con==null) failureCounter++;
		try {
			if(con!=null)	con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String args[]) {
		
		Properties prop = new Properties();
		prop.setProperty(ConnectionFactory.JDBC_DRIVER_PROP, "sun.jdbc.odbc.JdbcOdbcDriver");
		prop.setProperty(ConnectionFactory.JDBC_URL_PROP, "jdbc:odbc:Amar");
		prop.setProperty(ConnectionFactory.JDBC_USER_PROP, "Database1");
		prop.setProperty(ConnectionFactory.JDBC_PASSWORD_PROP, "amar");
		prop.setProperty(ConnectionFactory.CONNECTION_POOL_SIZE_PROP, "5");
		ConnectionFactory.configure(prop);
		
		TestConnectionFactory tc ;
		
		for(int i=0; i<30; i++) {
			tc = new TestConnectionFactory(i);
			tc.start();
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		
		System.out.println("Number of failures : " + failureCounter);
	}

	
	
	
}
