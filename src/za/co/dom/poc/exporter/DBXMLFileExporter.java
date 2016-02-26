package za.co.dom.poc.exporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//import org.rss.interfaces.Exporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import za.co.dom.poc.connectionfactory.DBConnectionFactory;

public class DBXMLFileExporter implements Exporter {

    private DBConnectionFactory connectionFactory = null;
    private String queryString = "";
    private String rootNodeName = "ROOT";
    private String rowNodeName = "ROW";

    public DBXMLFileExporter(DBConnectionFactory connectionFactory, String queryORtable) {
        this.connectionFactory = connectionFactory;
        if (!queryORtable.contains(" ")) {
            queryORtable = "Select * from " + queryORtable;
        }
        this.queryString = queryORtable;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setTableName(String tableName) {
        this.queryString = "Select * from " + tableName;
    }

    public DBConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(DBConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public String getRootNodeName() {
        return rootNodeName;
    }

    public void setRootNodeName(String rootNodeName) {
        this.rootNodeName = rootNodeName;
    }

    public String getRowNodeName() {
        return rowNodeName;
    }

    public void setRowNodeName(String rowNodeName) {
        this.rowNodeName = rowNodeName;
    }

    public void export(OutputStream out) throws SQLException, ParserConfigurationException, TransformerException {

        DocumentBuilderFactory documentBuilderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document document = null;
        Element rootNode = null;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        Source source;
        Result result = null;

        try {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            rootNode = document.createElement(rootNodeName);


            System.out.println(documentBuilder.getDOMImplementation().getClass().getCanonicalName());

            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            metaData = resultSet.getMetaData();

            Node rowNode = null;
            String cellName = null;
            String cellValue = null;

            while (resultSet.next()) {
                rowNode = document.createElement(rowNodeName);
                Node dataNode = null;
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    cellName = metaData.getColumnName(i);
                    cellValue = resultSet.getString(cellName);
                    dataNode = document.createElement(cellName);
                    dataNode.setTextContent(cellValue);
                    rowNode.appendChild(dataNode);
                }
                rootNode.appendChild(rowNode);
            }

            document.appendChild(rootNode);

            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            result = new StreamResult(new OutputStreamWriter(out));
            source = new DOMSource(document);
            transformer.transform(source, result);

        } catch (SQLException e) {
            throw e;
        } catch (ParserConfigurationException e) {
            throw e;
        } catch (TransformerConfigurationException e) {
            throw e;
        } catch (TransformerException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void export(File file) throws SQLException, IOException, ParserConfigurationException, TransformerException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            export(out);
        } catch (SQLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null)
                out.close();
        }


    }

    public void export(String filePath) throws SQLException, IOException, ParserConfigurationException, TransformerException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            export(out);
        } catch (SQLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (out != null)
                out.close();
        }
    }

}

