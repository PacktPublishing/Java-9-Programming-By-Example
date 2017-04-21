package packt.java9.by.example.mybusiness.inventory;

import java.util.concurrent.atomic.AtomicLong;

public class InventoryItem {
    private final AtomicLong amountOnStock =
            new AtomicLong(0);
    void store(long n) {
        amountOnStock.accumulateAndGet(n,
                (stock, delta) -> stock + delta);
    }
    long remove(long delta) {
        class ClosureData {
            long actNr;
        }
        ClosureData d = new ClosureData();
        amountOnStock.accumulateAndGet(delta,
                (stock, n) ->
                        stock >= n ?
                                stock - (d.actNr = n)
                                :
                                stock - (d.actNr = 0)
        );
        return d.actNr;
    }
}
