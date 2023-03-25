import consumer.Consumer;
import dbconfig.Database;
import dbconfig.DatabaseConnection;
import dbconfig.SqlScriptExecutor;
import models.Customer;
import models.CustomerAddress;
import producer.Producer;
import services.InvalidCustomerListService;
import services.ValidCustomerListService;
import utiles.Constants;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException, IOException {

        // initialize the database and tables scripts
//        new SqlScriptExecutor().executeSqlScript(Constants.DB_INIT_SQL_FILE_PATH);

        long startTime = System.currentTimeMillis();
        // Initialize the database singleton
//        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
//        Database database = Database.getInstance();

        // Initialize the invalid customer list and exporter
//        List<Customer> invalidCustomers = new ArrayList<>();
        InvalidCustomerListService invalidCustomerExporter = InvalidCustomerListService.getInstance();

        // Initialize the valid customer list and exporter
//        List<Customer> validCustomers = new ArrayList<>();
        ValidCustomerListService validCustomerExporter = ValidCustomerListService.getInstance();

        // Initialize the producer-consumer system
        BlockingQueue<Customer> queue = new ArrayBlockingQueue<>(Constants.BATCH_SIZE);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue, invalidCustomerExporter, validCustomerExporter);

        // Start the producer and consumer threads
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();

        // Wait for the threads to complete
        producerThread.join();
        consumerThread.join();

        // Export the lists of invalid and valid customers
//        invalidCustomerExporter.exportToFile(Constants.INVALID_CUSTOMERS_FILE_NAME);
//        validCustomerExporter.exportToFile(Constants.VALID_CUSTOMERS_FILE_NAME);

        // Print the execution time
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
}
