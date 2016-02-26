package za.co.random.poc.db;

import za.co.dom.poc.connectionfactory.ConnectionFactory;

import java.util.Properties;

public class TestDBDataFileExporter {
	public static void main(String args[]){
		Properties prop = new Properties();
		prop.setProperty(ConnectionFactory.JDBC_DRIVER_PROP, "sun.jdbc.odbc.JdbcOdbcDriver");
		prop.setProperty(ConnectionFactory.JDBC_URL_PROP, "jdbc:odbc:Amar");
		prop.setProperty(ConnectionFactory.JDBC_USER_PROP, "Database1");
		prop.setProperty(ConnectionFactory.JDBC_PASSWORD_PROP, "amar");
		prop.setProperty(ConnectionFactory.CONNECTION_POOL_SIZE_PROP, "5");
		ConnectionFactory.configure(prop);	
		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		
		test1(connectionFactory);
		//test2(connectionFactory);
		//test3(connectionFactory);
		//test4(connectionFactory);
		//test5(connectionFactory);
		
	}	
		
	
	public static void test1(ConnectionFactory factory){
		DBDataFileExporter agent = new DBDataFileExporter();
		agent.setConnectionFactory(factory);
		agent.setQueryString("Select * from Employee");
		try {
			agent.export(System.out);
	     	agent.export("D:/HTTPServer/amar.txt");
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public static void test2(ConnectionFactory factory){
		DBDataFileExporter agent = new DBDataFileExporter();
		agent.setConnectionFactory(factory);
		agent.setQueryString("Select ID,SURNAME,FORENAME,SALARY,BRANCH,EMAIL,PHONE from Employee");
		agent.setFieldDelimeter("|");
		agent.setRecordDelimeter(";\n");
		try {
			agent.export(System.out);
	     	agent.export("C:/Employee_2.txt");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void test3(ConnectionFactory factory){
		DBDataFileExporter agent = new DBDataFileExporter();
		agent.setConnectionFactory(factory);
		agent.setQueryString("Select ID,SURNAME,FORENAME,SALARY,BRANCH,EMAIL,PHONE from Employee");
		agent.setFieldDelimeter("|");
		agent.setRecordDelimeter(";\n");
		agent.setFieldPrefix("(");
		agent.setFieldSuffix(")");
		agent.setUsingLabel(true);
		agent.setLabelAssigner("=");
		try {
			agent.export(System.out);
	     	agent.export("C:/Employee_3.txt");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void test4(ConnectionFactory factory){
		DBDataFileExporter agent = new DBDataFileExporter();
		agent.setConnectionFactory(factory);
		agent.setQueryString("Select ID,SURNAME,FORENAME,SALARY,BRANCH,EMAIL,PHONE from Employee");
		agent.setFieldDelimeter("|");
		agent.setRecordDelimeter(";\n");
		agent.setFieldPrefix("(");
		agent.setFieldSuffix(")");
		agent.setUsingLabel(true);
		agent.setLabelAssigner(",");
		agent.setRecordPrefix("[ ");
		agent.setRecordSuffix(" ]");
		try {
			agent.export(System.out);
	     	agent.export("C:/Employee_4.txt");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void test5(ConnectionFactory factory){
		DBDataFileExporter agent = new DBDataFileExporter("Employee");
		agent.setConnectionFactory(factory);
		agent.setFieldDelimeter("\n");
		agent.setFieldPrefix("");
		agent.setFieldSuffix("");
		agent.setRecordDelimeter("\n");
		agent.setRecordPrefix("#\n");
		agent.setRecordSuffix("\n$");
		agent.setUsingLabel(true);
		agent.setLabelAssigner("->");
		try {
			agent.export(System.out);
	     	agent.export("C:/Employee_5.txt");
		} catch(Exception e){
			e.printStackTrace();
		}		
	}

}
