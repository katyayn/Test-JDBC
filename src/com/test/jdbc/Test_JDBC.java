package com.test.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jdbc.model.Test;
import com.jdbc.utility.JDBCUtility;;

public class Test_JDBC {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306?autoReconnect=true&useSSL=false&allowMultiQueries=true";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "password-1";

    private Connection connection;

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void createNewConnection() throws ClassNotFoundException, SQLException {

        Class.forName(Test_JDBC.JDBC_DRIVER);
        setConnection(DriverManager.getConnection(Test_JDBC.JDBC_URL, Test_JDBC.USERNAME, Test_JDBC.PASSWORD));

    }

    public void closeConnection() throws SQLException {
        getConnection().close();
    }

    public void updateStructure(String sqlPath, Statement statement) throws IOException, SQLException {

        System.out.println("IN executeUpdate");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(sqlPath));

        String string;

        StringBuilder stringBuilder = new StringBuilder();

        while ((string = bufferedReader.readLine()) != null) {
            stringBuilder.append(string + "\n");
        }

        bufferedReader.close();

        statement.executeUpdate(stringBuilder.toString());

        System.out.println("OUT executeUpdate");
    }

    public String getStatement(String sqlPath) throws IOException {
        System.out.println("IN getStatement");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(sqlPath));

        String string;

        StringBuilder stringBuilder = new StringBuilder();

        while ((string = bufferedReader.readLine()) != null) {
            stringBuilder.append(string + "\n");
        }

        bufferedReader.close();

        System.out.println("OUT getStatement");

        return stringBuilder.toString();
    }

    public void createBlankTables() throws IOException, SQLException, ClassNotFoundException, URISyntaxException {

        System.out.println("IN createBlankTables");

        createNewConnection();

        Statement statement = getConnection().createStatement();

        String sqlPath = JDBCUtility.getPath("/sql/create-schema.sql");

        updateStructure(sqlPath, statement);

        closeConnection();

        System.out.println("OUT createBlankTables");

    }

    public void insertNames() throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
        System.out.println("IN insertNames");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter name: ");
        String temp_name = bufferedReader.readLine();

        createNewConnection();

        String sqlPath = JDBCUtility.getPath("/sql/insert-names.sql");

        PreparedStatement preparedStatement = getConnection().prepareStatement(getStatement(sqlPath));

        preparedStatement.setString(1, temp_name);

        preparedStatement.executeUpdate();

        closeConnection();

        System.out.println("OUT insertNames");
    }

    public ArrayList<Test> getNamesList() throws IOException, SQLException, ClassNotFoundException, URISyntaxException {

        System.out.println("IN getNamesList");

        ArrayList<Test> arrayList = new ArrayList<Test>();

        createNewConnection();

        Statement statement = getConnection().createStatement();

        String sqlPath = JDBCUtility.getPath("/sql/view-names-list.sql");

        ResultSet resultSet = statement.executeQuery(getStatement(sqlPath));

        while (resultSet.next()) {
            arrayList.add(new Test(resultSet.getInt(1), resultSet.getString(2)));
        }

        closeConnection();

        System.out.println("OUT getNamesList");

        return arrayList;

    }

}
