package za.co.dom.poc.test.exporter;//package org.rss.test;

import za.co.dom.poc.connectionfactory.ConnectionFactory;
import za.co.dom.poc.connectionfactory.DBConnectionFactory;
import za.co.dom.poc.exporter.DBXMLFileExporter;

import java.util.Properties;

public class TestDBXMLFileExporter {
	public static void main(String args[]){
		Properties prop = new Properties();
		prop.setProperty(ConnectionFactory.JDBC_DRIVER_PROP, "sun.jdbc.odbc.JdbcOdbcDriver");
		prop.setProperty(ConnectionFactory.JDBC_URL_PROP, "jdbc:odbc:Amar");
		prop.setProperty(ConnectionFactory.JDBC_USER_PROP, "Database1");
		prop.setProperty(ConnectionFactory.JDBC_PASSWORD_PROP, "amar");
		prop.setProperty(ConnectionFactory.CONNECTION_POOL_SIZE_PROP, "5");
		DBConnectionFactory.configure(prop);
		DBConnectionFactory connectionFactory = DBConnectionFactory.getInstance();
		
		//test1(connectionFactory);
		test2(connectionFactory);
		
	}
	
	public static void test1(DBConnectionFactory connectionFactory){
		DBXMLFileExporter agent = null;
		try {
			agent = new DBXMLFileExporter(connectionFactory,"Select * from Employee");
			agent.export(System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test2(DBConnectionFactory connectionFactory){
		DBXMLFileExporter agent = null;
		try {
			agent = new DBXMLFileExporter(connectionFactory,"Employee");
			agent.setRootNodeName("EMPLOYEELIST");
			agent.setRowNodeName("EMPLOYEE");
			agent.export(System.out);
			agent.export("C:/amar1.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
