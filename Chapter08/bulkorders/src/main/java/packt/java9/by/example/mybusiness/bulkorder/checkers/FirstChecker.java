package packt.java9.by.example.mybusiness.bulkorder.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import packt.java9.by.example.mybusiness.bulkorder.ConsistencyChecker;
import packt.java9.by.example.mybusiness.bulkorder.dtos.Order;

@Component
public class FirstChecker implements ConsistencyChecker {
    private static final Logger log = LoggerFactory.getLogger(FirstChecker.class);
    @Override
    public boolean isInconsistent(Order order) {
        log.info("FirstChecker checking order {}", order);
        return false;
    }
}
