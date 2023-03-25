package dbconfig;

import utiles.Constants;

import java.sql.*;

/**
 * @author mir00r on 24/3/23
 * @project IntelliJ IDEA
 */
public class DatabaseConnection {
    //private static instance of the class
    private static DatabaseConnection instance;
    private Connection connection;

    //private constructor to prevent other classes from creating an instance
    private DatabaseConnection() throws SQLException {
        try {
            Class.forName(Constants.DB_DRIVER);
            this.connection = DriverManager.getConnection(Constants.DB_URL + Constants.DB_NAME, Constants.DB_USER, Constants.DB_PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    //public static method to get the instance of the class
    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    //method to execute a query and return a ResultSet
    public ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    //method to execute an update statement
    public int executeUpdate(String update) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(update);
        int rowsAffected = statement.executeUpdate();
        return rowsAffected;
    }

    //method to close the database connection
    public void close() throws SQLException {
        connection.close();
    }

    //getter method for the connection object
    public Connection getConnection() {
        return connection;
    }
}
