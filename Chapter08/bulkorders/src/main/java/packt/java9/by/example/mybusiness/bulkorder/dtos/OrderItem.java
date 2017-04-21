package packt.java9.by.example.mybusiness.bulkorder.dtos;

public class OrderItem {
    private double amount;
    private String unit;
    private String productId;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public String getProductId() {
        return productId;
    }
}
