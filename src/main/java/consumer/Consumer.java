package consumer;

import models.Customer;
import services.InvalidCustomerListService;
import services.ValidCustomerListService;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class Consumer implements Runnable {
    private final BlockingQueue<Customer> queue;
    private final InvalidCustomerListService invalidCustomerExporter;
    private final ValidCustomerListService validCustomerExporter;

    private final Map<String, Boolean> phoneMap = new ConcurrentHashMap<>();
    private final Map<String, Boolean> emailMap = new ConcurrentHashMap<>();


    public Consumer(BlockingQueue<Customer> queue, InvalidCustomerListService invalidCustomerExporter, ValidCustomerListService validCustomerExporter) {
        this.queue = queue;
        this.invalidCustomerExporter = invalidCustomerExporter;
        this.validCustomerExporter = validCustomerExporter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Customer customer = queue.take();
                if (customer == null) {
                    // End of file
                    break;
                }
                this.phoneNumberProcess(customer);
                this.emailProcess(customer);
                System.out.println("------------------ Found valid customer ----------------- \n" + customer);
                validCustomerExporter.addCustomer(customer);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private synchronized void phoneNumberProcess(Customer customer) {
        if (phoneMap.containsKey(customer.getPhone())) {
            invalidCustomerExporter.addCustomer(customer);
            System.out.println("------------------ Found invalid customer for duplicate phone or email ----------------- \n" + customer);
        } else {
            phoneMap.put(customer.getPhone(), true);
        }
    }

    private synchronized void emailProcess(Customer customer) {
        if (emailMap.containsKey(customer.getEmail())) {
            invalidCustomerExporter.addCustomer(customer);
            System.out.println("------------------ Found invalid customer for duplicate phone or email ----------------- \n" + customer);
        } else {
            emailMap.put(customer.getEmail(), true);
        }
    }
}
