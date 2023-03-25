package services;

import models.Customer;
import utiles.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class ValidCustomerListService {
    private static ValidCustomerListService instance = null;
    private List<Customer> customerList = new ArrayList<>();

    private ValidCustomerListService() {
    }

    public static ValidCustomerListService getInstance() {
        if (instance == null) {
            synchronized (ValidCustomerListService.class) {
                if (instance == null) {
                    instance = new ValidCustomerListService();
                }
            }
        }
        return instance;
    }

    public synchronized void addCustomer(Customer customer) {
        customerList.add(customer);
        if (customerList.size() >= Constants.BATCH_SIZE) {
            exportToFile(null);
        }
    }

    public synchronized List<Customer> getAll() {
        return this.customerList;
    }

    public synchronized void exportToFile(String fileName) {
        if (customerList.isEmpty()) {
            return;
        }
        String finalFileName = fileName == null ? "valid-customers-" + System.currentTimeMillis() + ".txt" : fileName;
        try (FileWriter writer = new FileWriter(finalFileName)) {
            System.out.println("---------------------------------- Valid Customer file exporting started -----------------------------------------");
            for (Customer customer : customerList) {
                System.out.println(customer);
                writer.write(customer + "\n");
            }
            customerList.clear();
            System.out.println("Valid customers exported to file: " + finalFileName);
        } catch (IOException e) {
            System.out.println("Error exporting valid customers to file: " + e.getMessage());
        }
    }
}
