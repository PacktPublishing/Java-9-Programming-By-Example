package packt.java9.by.example.mybusiness.product;

public class ProductIsOutOfStock extends Exception {
    public ProductIsOutOfStock(Product product) {
        super(product.toString());
    }
}
