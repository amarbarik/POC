package za.co.random.poc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class AccessingXmlFile {

    public static void main(String argv[]) {

        try {
            File file = new File("C:/amar1.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            System.out.println("Root element " + document.getDocumentElement().getNodeName());
            NodeList node = document.getElementsByTagName("EMPLOYEE");
            System.out.println("Information of the Employee");

            for (int i = 0; i < node.getLength(); i++) {
                Node firstNode = node.item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) firstNode;
                    NodeList firstNameElemntList = element.getElementsByTagName("EMPID");
                    Element firstNameElement = (Element) firstNameElemntList.item(0);
                    NodeList firstName = firstNameElement.getChildNodes();
                    String Eid = (firstName.item(0).getNodeValue());
                    System.out.println("Employee ID:" + (firstName.item(0).getNodeValue()));

                    NodeList lastNameElementList = element.getElementsByTagName("ENAME");
                    Element lastNameElement = (Element) lastNameElementList.item(0);
                    NodeList lastName = lastNameElement.getChildNodes();
                    String Ename = (lastName.item(0).getNodeValue());
                    System.out.println("Employee Name :" + (lastName.item(0).getNodeValue()));

                    NodeList addressList = element.getElementsByTagName("SAL");
                    Element addressElement = (Element) addressList.item(0);
                    NodeList address = addressElement.getChildNodes();
                    String Salary = ((Node) address.item(0)).getNodeValue();
                    System.out.println("Salary : " + ((Node) address.item(0)).getNodeValue());

                    NodeList cityList = element.getElementsByTagName("ADDRESS");
                    Element cityElement = (Element) cityList.item(0);
                    NodeList city = cityElement.getChildNodes();
                    String Address = ((Node) city.item(0)).getNodeValue();
                    System.out.println("Address : " + ((Node) city.item(0)).getNodeValue());
                    NodeList cityList1 = element.getElementsByTagName("DESG");
                    Element cityElement1 = (Element) cityList1.item(0);
                    NodeList city1 = cityElement1.getChildNodes();
                    String Desg = ((Node) city1.item(0)).getNodeValue();
                    System.out.println("Designamtion : " + ((Node) city1.item(0)).getNodeValue());
                    String url = "jdbc:odbc:Amar";
                    Connection con;
                    Statement stmt;
                    try {
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

                    } catch (ClassNotFoundException e) {
                        System.err.print("ClassNotFoundException: ");
                        System.err.println(e.getMessage());
                    }
                    try {

                        con = DriverManager.getConnection(url,
                                "Database1", "amar");

                        stmt = con.createStatement();
                        stmt.executeUpdate("insert into Employee " +
                                "values(" + Eid + ", '" + Ename + "'," + Salary + ", '" + Address + "','" + Desg + "')");
                        stmt.close();
                        con.close();

                    } catch (SQLException ex) {
                        System.err.println("SQLException: " + ex.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}