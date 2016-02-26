package za.co.random.poc.db;

import za.co.dom.poc.connectionfactory.ConnectionFactory;

import java.io.*;
import java.sql.*;
//import java.util.Properties;
import java.util.StringTokenizer;
public class DBDataFileImport 
{
	private String  fieldDelimeter = ",";	
	private String  fieldPrefix ="";
	private String  fieldSuffix ="";
	private String  recordDelimeter = "\n";	
	private String  recordPrefix ="";
	private String  recordSuffix ="";
	private String  labelAssigner = "=";
	private ConnectionFactory connectionFactory;
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	public String getFieldDelimeter() {
		return fieldDelimeter;
	}
	public void setFieldDelimeter(String fieldDelimeter) {
		this.fieldDelimeter = fieldDelimeter;
	}
	public String getFieldPrefix() {
		return fieldPrefix;
	}
	public void setFieldPrefix(String fieldPrefix) {
		this.fieldPrefix = fieldPrefix;
	}
	public String getFieldSuffix() {
		return fieldSuffix;
	}
	public void setFieldSuffix(String fieldSuffix) {
		this.fieldSuffix = fieldSuffix;
	}
	public String getRecordDelimeter() {
		return recordDelimeter;
	}
	public void setRecordDelimeter(String recordDelimeter) {
		this.recordDelimeter = recordDelimeter;
	}	
	public String getRecordPrefix() {
		return recordPrefix;
	}
	public void setRecordPrefix(String recordPrefix) {
		this.recordPrefix = recordPrefix;
	}
	public String getRecordSuffix() {
		return recordSuffix;
	}
	public void setRecordSuffix(String recordSuffix) {
		this.recordSuffix = recordSuffix;
	}
	public String getLabelAssigner() {
		return labelAssigner;
	}
	public void setLabelAssigner(String labelAssigner) {
		this.labelAssigner = labelAssigner;
	}
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	public String fileReader(String filename)
	{
		StringBuffer buf = new StringBuffer();
		String line;
	try
	{
		FileInputStream fis=new FileInputStream(filename);
		BufferedReader br=new BufferedReader(new InputStreamReader(fis));
	while ((line = br.readLine()) != null)
	{
		buf.append(line + "\n");
     }
     System.out.println(buf.toString());
     
	}
	catch(Exception ex)
	{
		System.out.println("Error:"+ex);
	}
	return buf.toString();
	}
	public String[] removeDelimeters(String data,String delimiter)
	{
		String data1="";
		//StringTokenizer tokens =new StringTokenizer(data, ",");
		StringTokenizer tokens =new StringTokenizer(data,delimiter);
		int length=data.length();
		String[] fields = new String[length];
		for (int i = 0; i < fields.length; i++) 
		{
			if(tokens.hasMoreTokens()){
		fields[i] =stripQuotes(tokens.nextToken());
		data1+=fields[i];
		System.out.println(fields[i]+"->"+i);
		}
			else{
				break;
				}
		}
		return fields;
	}
	public String[] getData(String data,String delimiter)
	{
		StringTokenizer tokens =new StringTokenizer(data,delimiter);
		int length=data.length();
		String[] fields = new String[length];
		for (int i = 0; i < fields.length; i++) 
		{
			if(tokens.hasMoreTokens())
			{
				fields[i] =stripQuotes(tokens.nextToken());
				//System.out.println(fields[i]);
		}
			else{
				break;
				}
		}
		return fields;
	}
	private String stripQuotes(String input) {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < input.length(); i++) 
		{
		if (input.charAt(i) != '\"') 
			{
			output.append(input.charAt(i));
			}
		}
		return output.toString();
	}
	public String getString(String[] fields)
	{
		String data="";
		System.out.println("\n");
		for(int i=0;i<fields.length;i++)
		{
			if(fields[i]!=null){
			System.out.println(fields[i]+"--"+i);
			data+=fields[i];
			}
			else
			{
				break;
			}
		}
		return data;
	}
	public static void main(String args[])
	{
		try{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the filename");
		String filename=br.readLine();
		System.out.println("Enter the record prefix if any?, or press n");
		String rprefix=null;rprefix=br.readLine();
		//System.out.println(rprefix.equals("n"));
		System.out.println("Enter the record suffix if any? or press n");
		String rsuffix=null;rsuffix=br.readLine();
		System.out.println("Enter the record delimeter if any? press n");
		String rdelimeter=null;rdelimeter=br.readLine();
		System.out.println("Enter the field prefix if any? or press n");
		String fprefix=null;fprefix=br.readLine();
		System.out.println("Enter the field suffix if any? or press n");
		String fsuffix=null;fsuffix=br.readLine();
		System.out.println("Enter the field delimeter if any? or press n");
		String fdelimeter=null;fdelimeter=br.readLine();		
		System.out.println("Enter the Label assigner if any? or press n");
		String labelassigner=null;labelassigner=br.readLine();
		System.out.println(filename);		
		filename=filename.replace('/',File.separatorChar);
		System.out.println(filename);
		DBDataFileImport dfi=new DBDataFileImport();
		String data=dfi.fileReader(filename);
		String[] fields;
		fields=dfi.removeDelimeters(data,"\n");
		data=dfi.getString(fields);
		if(!rdelimeter.equals("n"))
		{
			System.out.println("A");
			fields=dfi.removeDelimeters(data, rdelimeter);
			data=dfi.getString(fields);
		}
		if(!rprefix.equals("n"))
		{
			System.out.println("B");
			fields=dfi.removeDelimeters(data, rprefix);
			data=dfi.getString(fields);
			
		}
		if(!rsuffix.equals("n"))
		{
			System.out.println("C");
			fields=dfi.removeDelimeters(data, rsuffix);
			data=dfi.getString(fields);
		}		
		if(!fdelimeter.equals("n"))
		{
			System.out.println("D");
			fields=dfi.removeDelimeters(data,fdelimeter);
			data=dfi.getString(fields);
		}
		if(!fprefix.equals("n"))
		{
			System.out.println("E");
			fields=dfi.removeDelimeters(data, fprefix);
			data=dfi.getString(fields);
		}		
		if(!fsuffix.equals("n"))
		{
			System.out.println("F");
			fields=dfi.removeDelimeters(data,fsuffix);
			data=dfi.getString(fields);
		}
		String url = "jdbc:odbc:Amar";
		Connection con;
		Statement stmt;	
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	
		} catch(ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: "); 
			System.err.println(e.getMessage());
		}
		try {

			con = DriverManager.getConnection(url, 
									 "Database1", "amar");
	
			stmt = con.createStatement();
			System.out.println(fields.length);
			for(int i=0;i<70;i+=7){
				if(fields[i]!=null){
				String f1[]=fields[i].split(labelassigner);
				String f2[]=fields[i+1].split(labelassigner);
				String f3[]=fields[i+2].split(labelassigner);
				String f4[]=fields[i+3].split(labelassigner);
				String f5[]=fields[i+4].split(labelassigner);
				String f6[]=fields[i+5].split(labelassigner);
				String f7[]=fields[i+6].split(labelassigner);
			stmt.executeUpdate("insert into Employee1 " +
	         "values("+f1[1]+", '"+f2[1]+"','"+f3[1]+"', '"+f4[1]+"','"+f5[1]+"','"+f6[1]+"','"+f7[1]+"')");
				}
				else{
					break;
				}}stmt.close();
			con.close();
	
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		}
		catch(IOException e)
		{
			System.out.println("Error:"+e);
		}
		
	}

}
