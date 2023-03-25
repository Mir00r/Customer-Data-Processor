package producer;

import models.Customer;
import models.CustomerAddress;
import utiles.Constants;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * @author mir00r on 23/3/23
 * @project IntelliJ IDEA
 */
public class Producer implements Runnable {

    private final BlockingQueue<Customer> queue;

    public Producer(BlockingQueue<Customer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(new File(Constants.CUSTOMERS_DATA_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                if (fields.length < 8) {
                    continue;
                }
                String firstName = fields[0].trim();
                String lastName = fields[1].trim();
                String city = fields[2].trim();
                String cityCode = fields[3].trim();
                String zipCode = fields[4].trim();
                String phone = fields[5].trim();
                String email = fields[6].trim();
                String ip = fields[7].trim();

                Customer customer = Customer.builder()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withAddress(CustomerAddress.builder().withCity(city).withCityCode(cityCode).withZipCode(zipCode).build())
                        .withPhone(phone)
                        .withEmail(email)
                        .withIpAddress(ip)
                        .build();
                queue.put(customer);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
