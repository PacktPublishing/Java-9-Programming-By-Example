package packt.java9.by.example.mybusiness.productinformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductInformationController {
    private final ProductLookup lookup;


    public ProductInformationController(@Autowired ProductLookup lookup) {
        this.lookup = lookup;
    }

    @RequestMapping("/pi/{productId}")
    public ProductInformation getProductInformation(@PathVariable String productId) {
        return lookup.byId(productId);
    }

    @RequestMapping("/query/{query}")
    public List<String> lookupProductByTitle(@PathVariable String query, HttpServletRequest request) {
        final List<String> piIds = lookup.byQuery(query);
        final List<String> urls = new ArrayList<>(piIds.size());
        for (String piId : piIds) {
            final String url = String.format("/pi/%s", piId);
            urls.add(url);
        }
        return urls;
    }
}
