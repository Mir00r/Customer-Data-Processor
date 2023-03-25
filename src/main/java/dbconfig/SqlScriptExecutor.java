package dbconfig;

import utiles.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author mir00r on 24/3/23
 * @project IntelliJ IDEA
 */
public class SqlScriptExecutor {

    public void executeSqlScript(String pathToFile) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
        Connection conn = null;
        Statement stmt = null;
        String tableSql = "create table if not exists customers\n" +
                "(\n" +
                "    id         bigint auto_increment primary key,\n" +
                "    updated_at datetime     null,\n" +
                "    updated_by varchar(255) null,\n" +
                "    first_name varchar(255) not null,\n" +
                "    phone      varchar(255) not null,\n" +
                "    email      varchar(255) not null,\n" +
                "    ip_address varchar(255) null,\n" +
                "    city_name  varchar(255) null,\n" +
                "    city_code  varchar(511) null,\n" +
                "    zip_code   varchar(255) null,\n" +
                "    created_at datetime     null,\n" +
                "    created_by varchar(255) null,\n" +
                "    is_valid   bit          not null,\n" +
                "    deleted    bit          not null\n" +
                ")";

        try {
            conn = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
            stmt = conn.createStatement();

            // Create the database if it does not exist
            String line;
            while ((line = reader.readLine()) != null) {
                stmt.execute(line);
            }
            System.out.println("Database SQL script executed successfully.");

            // Execute the table SQL script by calling the execute() method on the statement object
            stmt.execute(tableSql);

            System.out.println("Table SQL script executed successfully.");

        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
