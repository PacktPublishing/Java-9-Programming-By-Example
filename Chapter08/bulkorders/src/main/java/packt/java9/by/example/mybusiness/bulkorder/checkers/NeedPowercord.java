package packt.java9.by.example.mybusiness.bulkorder.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import packt.java9.by.example.mybusiness.bulkorder.ConsistencyChecker;
import packt.java9.by.example.mybusiness.bulkorder.dtos.Order;

@Component
@PoweredDevice
public class NeedPowercord implements ConsistencyChecker {
    private static final Logger log = LoggerFactory.getLogger(NeedPowercord.class);

    @Override
    public boolean isInconsistent(Order order) {
        log.info("checking order {}", order);
        CheckHelper helper = new CheckHelper(order);
        return !helper.containsOneOf("126", "127", "128");
    }
}
