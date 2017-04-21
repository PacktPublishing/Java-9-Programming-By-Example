package packt.java9.by.example.mybusiness.inventory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import packt.java9.by.example.mybusiness.product.Order;
import packt.java9.by.example.mybusiness.product.OrderItem;
import packt.java9.by.example.mybusiness.product.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class TestInventoryKeeper {
    private static final Logger log = LoggerFactory.getLogger(TestInventoryKeeper.class);

    @Test
    public void testInventoryRemoval() throws InterruptedException {
        Inventory inventory = new Inventory();
        SubmissionPublisher<Order> p = new SubmissionPublisher<>();//Executors.newFixedThreadPool(6), 20);
        p.subscribe(new InventoryKeeper(inventory));
        Product product = new Product();
        inventory.store(product, 20);
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setAmount(10);
        Order order = new Order();
        List<OrderItem> items = new LinkedList<>();
        items.add(item);
        order.setItems(items);
        for (int i = 0; i < 10; i++)
            p.submit(order);
        log.info("All orders were submitted");
        for (int j = 0; j < 10; j++) {
            log.info("Sleeping a bit...");
            Thread.sleep(50);
        }
        p.close();
        log.info("Publisher was closed");
    }
}
