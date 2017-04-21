package packt.java9.by.example.mybusiness.product;

import java.util.List;
import packt.java9.by.example.mybusiness.product.*;
public class Order {
    private String orderId;
    private List<OrderItem> items;
    private String customerId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getCustomerId() {
        return customerId;
    }
}
