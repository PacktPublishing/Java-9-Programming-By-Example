package packt.java9.by.example.ch03.main.bubble;

public class NonStringElementInCollectionException extends RuntimeException {
    public NonStringElementInCollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
