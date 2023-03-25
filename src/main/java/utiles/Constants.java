package utiles;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class Constants {
    public static final String VALID_CUSTOMERS_FILE_NAME = "valid_customers.txt";
    public static final String INVALID_CUSTOMERS_FILE_NAME = "invalid_customers.txt";
    public static final int BATCH_SIZE = 100000;
    public static final String CUSTOMERS_DATA_FILE_PATH = "src/main/resources/data/1M-customers.txt";

    public static final String DB_NAME = "customers";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_INIT_SQL_FILE_PATH = "src/main/resources/sql/init.sql";
}
