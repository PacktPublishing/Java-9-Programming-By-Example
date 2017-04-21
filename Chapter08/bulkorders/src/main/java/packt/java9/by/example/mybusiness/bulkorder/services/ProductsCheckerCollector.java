package packt.java9.by.example.mybusiness.bulkorder.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import packt.java9.by.example.mybusiness.bulkorder.dtos.Order;
import packt.java9.by.example.mybusiness.bulkorder.dtos.OrderItem;
import packt.java9.by.example.mybusiness.bulkorder.dtos.ProductInformation;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequestScope
public class ProductsCheckerCollector {
    private static final Logger log = LoggerFactory.getLogger(ProductsCheckerCollector.class);

    private final ProductInformationCollector pic;

    public ProductsCheckerCollector(@Autowired ProductInformationCollector pic) {
        this.pic = pic;
    }

    public Set<Class<? extends Annotation>> _getProductAnnotations(Order order) {
        Map<OrderItem, ProductInformation> piMap = pic.collectProductInformation(order);
        final Set<Class<? extends Annotation>> annotations = new HashSet<>();
        for (OrderItem item : order.getItems()) {
            final ProductInformation pi = piMap.get(item);
            if (pi != null && pi.getCheck() != null) {
                for (Class<? extends Annotation> check : pi.getCheck()) {
                    log.info("Product {} is annotated with class {}", pi.getId(), pi.getCheck().get(0));
                    annotations.addAll(pi.getCheck());
                }
            } else {
                log.info("Product {} has no annotation", pi.getId());
            }
        }
        return annotations;
    }

    public Set<Class<? extends Annotation>> getProductAnnotations(Order order) {
        Map<OrderItem, ProductInformation> piMap = pic.collectProductInformation(order);
        return order.getItems().stream()
                .map(piMap::get)
                .filter(Objects::nonNull)
                .peek(pi -> {
                    if (pi.getCheck() == null) {
                        log.info("Product {} has no annotation", pi.getId());
                    }
                })
                .filter(ProductInformation::hasCheck)
                .peek(pi -> log.info("Product {} is annotated with class {}", pi.getId(), pi.getCheck()))
                .flatMap(pi -> pi.getCheck().stream())
                .collect(Collectors.toSet());
    }
}
