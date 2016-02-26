package za.co.random.poc.db;

import za.co.dom.poc.connectionfactory.ConnectionFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class DBDataFileExporter {

    private String queryString;
    private String fieldDelimeter = ",";
    private String fieldPrefix = "";
    private String fieldSuffix = "";
    private String recordDelimeter = "\n";
    private String recordPrefix = "";
    private String recordSuffix = "";
    private boolean usingLabel = false;
    private String labelAssigner = "=";
    private ConnectionFactory connectionFactory;

    public DBDataFileExporter() {
    }

    public DBDataFileExporter(String tableName) {
        this.queryString = "Select * from " + tableName;
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

    public boolean isUsingLabel() {
        return usingLabel;
    }

    public void setUsingLabel(boolean usingLabel) {
        this.usingLabel = usingLabel;
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

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void export(OutputStream out) throws SQLException, IOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;

        String cellName = null;
        String cellValue = null;

        try {

            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryString);
            metaData = resultSet.getMetaData();

            String buffer = null;

            while (resultSet.next()) {
                buffer = "";
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    cellName = metaData.getColumnName(i);
                    if (usingLabel) {
                        cellValue = cellName + labelAssigner + resultSet.getString(cellName);
                    } else {
                        cellValue = resultSet.getString(cellName);
                    }

                    cellValue = fieldPrefix + cellValue + fieldSuffix;
                    if (i > 1) cellValue = fieldDelimeter + cellValue;
                    buffer = buffer + cellValue;
                }
                buffer = recordPrefix + buffer + recordSuffix + recordDelimeter;
                out.write(buffer.getBytes());
            }

        } catch (SQLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void export(File file) throws SQLException, IOException {
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

    public void export(String filePath) throws SQLException, IOException {
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

