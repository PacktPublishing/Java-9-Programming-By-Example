package packt.java9.by.example.mybusiness.bulkorder;

import packt.java9.by.example.mybusiness.bulkorder.dtos.ProductInformation;

import java.util.List;

public interface ProductLookup {

    ProductInformation byId(String id);

    List<String> byQuery(String query);
}
