package packt.java9.by.example.mybusiness.bulkorder.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import packt.java9.by.example.mybusiness.bulkorder.dtos.Order;
import packt.java9.by.example.mybusiness.bulkorder.dtos.OrderItem;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static packt.java9.by.example.mybusiness.bulkorder.checkers.Tuple.tuple;

public class CheckHelper {
    private static final Logger log = LoggerFactory.getLogger(CheckHelper.class);
    final private Order order;

    public CheckHelper(Order order) {
        this.order = order;
    }

    public boolean containsOneOf(String... ids) {
        return order.getItems().parallelStream()
                .map(OrderItem::getProductId)
                .flatMap(itemId -> Arrays.stream(ids)
                        .map(id -> tuple(itemId, id)))
                .filter(t -> Objects.equals(t.s, t.r))
                .collect(Collectors.counting()) > 0;
    }
}
