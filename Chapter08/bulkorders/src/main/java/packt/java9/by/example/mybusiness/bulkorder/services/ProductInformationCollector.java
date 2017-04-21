package packt.java9.by.example.mybusiness.bulkorder.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import packt.java9.by.example.mybusiness.bulkorder.dtos.ProductInformation;
import packt.java9.by.example.mybusiness.bulkorder.ProductLookup;
import packt.java9.by.example.mybusiness.bulkorder.dtos.Order;
import packt.java9.by.example.mybusiness.bulkorder.dtos.OrderItem;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static packt.java9.by.example.mybusiness.bulkorder.checkers.Tuple.tuple;

@Component
@RequestScope
public class ProductInformationCollector {
    private static final Logger log = LoggerFactory.getLogger(ProductsCheckerCollector.class);
    private final ProductLookup lookup;

    public ProductInformationCollector(ProductLookup lookup) {
        this.lookup = lookup;
    }

    private Map<OrderItem, ProductInformation> map = null;

    public Map<OrderItem, ProductInformation> _collectProductInformation(Order order) {
        if (map == null) {
            log.info("Collecting product information");
            map = new HashMap<>();
            for (OrderItem item : order.getItems()) {
                final ProductInformation pi = lookup.byId(item.getProductId());
                if (!pi.isValid()) {
                    map = null;
                    return null;
                }
                map.put(item, pi);
            }
        }
        return map;
    }

    public Map<OrderItem, ProductInformation> collectProductInformation(Order order) {
        if (map == null) {
            log.info("Collecting product information");
            map =
            order.getItems()
                    .stream()
                    .map(item -> tuple(item, item.getProductId()))
                    .map(t -> tuple(t.r, lookup.byId((String) t.s)))
                    .filter(t -> ((ProductInformation)t.s).isValid())
                    .collect(Collectors.toMap(t -> (OrderItem)t.r, t -> (ProductInformation)t.s));
            if (map.keySet().size() != order.getItems().size()) {
                log.error("Some of the products in the order do not have product information, {} != {} ",map.keySet().size(),order.getItems().size());
                map = null;
            }
        }
        return map;
    }

}
