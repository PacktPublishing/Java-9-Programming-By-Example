package packt.java9.by.example.mybusiness.bulkorder.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import packt.java9.by.example.mybusiness.bulkorder.ConsistencyChecker;
import packt.java9.by.example.mybusiness.bulkorder.dtos.Order;
import packt.java9.by.example.mybusiness.bulkorder.dtos.OrderItem;
import packt.java9.by.example.mybusiness.bulkorder.dtos.ProductInformation;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@Component()
@RequestScope
public class Checker {
    private static final Logger log = LoggerFactory.getLogger(Checker.class);

    private final Collection<ConsistencyChecker> checkers;
    private final ProductInformationCollector piCollector;
    private final ProductsCheckerCollector pcCollector;
    private final CheckerScriptExecutor executor;


    public Checker(@Autowired Collection<ConsistencyChecker> checkers,
                   @Autowired ProductInformationCollector piCollector,
                   @Autowired ProductsCheckerCollector pcCollector,
                   @Autowired CheckerScriptExecutor executor
    ) {
java.util.concurrent.Flow.Processor<Object,Object> processor;
        this.checkers = checkers;
        this.piCollector = piCollector;
        this.pcCollector = pcCollector;
        this.executor = executor;
    }



    /**
     * Check that amn order is consistent calling all consistency checkers that are relevant for the order.
     *
     * @param order
     * @return true if the order is consistent by all checkers
     */
    public boolean isConsistent(Order order) {
        final Map<OrderItem, ProductInformation> map =
                piCollector.collectProductInformation(order);
        if (map == null) {
            return false;
        }
        final Set<Class<? extends Annotation>> annotations =
                pcCollector.getProductAnnotations(order);
        Predicate<Annotation> annotationIsNeeded = annotation ->
                annotations.contains(annotation.annotationType());
        Predicate<ConsistencyChecker> productIsConsistent = checker ->
                Arrays.stream(checker.getClass().getAnnotations())
                        .parallel()
                        .unordered()
                        .filter(annotationIsNeeded)
                        .anyMatch(x -> checker.isInconsistent(order));
        final boolean checkersSayConsistent = !checkers.stream().
                anyMatch(productIsConsistent);
        final boolean scriptsSayConsistent =
                !map.values().
                        parallelStream().
                        map(ProductInformation::getCheckScript).
                        filter(Objects::nonNull).
                        anyMatch(s -> executor.notConsistent(s,order));
        return checkersSayConsistent && scriptsSayConsistent;
    }


}
