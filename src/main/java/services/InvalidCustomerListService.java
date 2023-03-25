package services;

import models.Customer;
import utiles.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class InvalidCustomerListService {
    private static InvalidCustomerListService instance = null;
    private final List<Customer> invalidCustomers;
    private final Object lock = new Object();

    private InvalidCustomerListService() {
        invalidCustomers = new ArrayList<>();
    }

    public static InvalidCustomerListService getInstance() {
        if (instance == null) {
            synchronized (InvalidCustomerListService.class) {
                if (instance == null) {
                    instance = new InvalidCustomerListService();
                }
            }
        }
        return instance;
    }

    public void addCustomer(Customer customer) {
        synchronized (lock) {
            invalidCustomers.add(customer);
            if (invalidCustomers.size() >= Constants.BATCH_SIZE) {
                exportToFile(null);
            }
        }
    }

    public synchronized List<Customer> getAll() {
        return this.invalidCustomers;
    }

    public void exportToFile(String fileName) {
        synchronized (lock) {
            String finalFileName = fileName == null ? "Invalid-customers-" + System.currentTimeMillis() + ".txt" : fileName;
            File file = new File(finalFileName);
            try (FileWriter writer = new FileWriter(file)) {
                System.out.println("---------------------------------- Invalid Customer file exporting started -----------------------------------------");
                for (Customer customer : invalidCustomers) {
                    System.out.println(customer);
                    writer.write(customer + "\n");
                }
                invalidCustomers.clear();
                System.out.println("Invalid customers exported to file: " + finalFileName);
            } catch (IOException e) {
                System.out.println("Error exporting invalid customers to file: " + e.getMessage());
            }
        }
    }
}
