package packt.java9.by.example.mybusiness.productinformation.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import packt.java9.by.example.mybusiness.productinformation.ProductInformation;
import packt.java9.by.example.mybusiness.productinformation.ProductInformationServiceUrlBuilder;
import packt.java9.by.example.mybusiness.productinformation.ProductLookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestClientProductLookup implements ProductLookup {
    private static Logger log = LoggerFactory.getLogger(RestClientProductLookup.class);

    final private ProductInformationServiceUrlBuilder piSUBuilder;

    public RestClientProductLookup(ProductInformationServiceUrlBuilder piSUBuilder) {
        this.piSUBuilder = piSUBuilder;
    }

    @Override
    public ProductInformation byId(String id) {
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("id", id);
        RestTemplate rest = new RestTemplate();
        InventoryItemAmount amount =
                rest.getForObject(piSUBuilder.url("inventory"),
                        InventoryItemAmount.class,
                        uriParameters);
        log.info("amount {}.",amount);
        if ( amount.getAmount() > 0) {
            log.info("There items from {}. We are offering",id);
            return rest.getForObject(piSUBuilder.url("pi"),
                    ProductInformation.class,
                    uriParameters);
        } else {
            log.info("There are no items from {}. Amount is {}",id,amount);
            return ProductInformation.emptyProductInformation;
        }
    }

    @Override
    public List<String> byQuery(String query) {
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("query", query);
        RestTemplate rest = new RestTemplate();
        return rest.getForObject(piSUBuilder.url("query"), List.class, uriParameters);
    }
}
