package packt.java9.by.example.mybusiness.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import packt.java9.by.example.mybusiness.product.Order;
import packt.java9.by.example.mybusiness.product.OrderItem;
import packt.java9.by.example.mybusiness.product.ProductIsOutOfStock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicInteger;

public class InventoryKeeper implements Flow.Subscriber<Order> {
    private static final Logger log = LoggerFactory.getLogger(InventoryKeeper.class);
    private final Inventory inventory;

    public InventoryKeeper(@Autowired Inventory inventory) {
        this.inventory = inventory;
    }

    private Flow.Subscription subscription = null;
    private static final long WORKERS = 3;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        log.info("onSubscribe was called");
        subscription.request(3);
        this.subscription = subscription;
    }

    private ExecutorService service = Executors.newFixedThreadPool((int) WORKERS);

    @Override
    public void onNext(Order order) {
        service.submit(() -> {
                    log.info("Thread {}", Thread.currentThread().getName());
                    for (OrderItem item : order.getItems()) {
                        try {
                            inventory.remove(item.getProduct(), item.getAmount());
                            log.info("{} items removed from stock",item.getAmount());
                        } catch (ProductIsOutOfStock exception) {
                            log.error("Product out of stock");
                        }
                    }
                    subscription.request(1);
                }
        );
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError was called for {}", throwable);
    }

    @Override
    public void onComplete() {
        log.info("onComplete was called");
    }
}
