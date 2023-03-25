package dbconfig;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class Database {
    private static Database instance;
    private List<Customer> customers;

    private Database() {
        customers = new ArrayList<>();
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public synchronized void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public synchronized List<Customer> getCustomers() {
        return customers;
    }
}
