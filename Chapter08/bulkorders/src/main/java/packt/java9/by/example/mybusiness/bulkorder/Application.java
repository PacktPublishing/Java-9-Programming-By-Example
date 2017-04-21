package packt.java9.by.example.mybusiness.bulkorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import packt.java9.by.example.mybusiness.ClassLister;

@SpringBootApplication(
        scanBasePackageClasses =
                packt.java9.by.example.mybusiness.SpringScanBase.class)
public class Application {
    public static void main(String[] args) {
        //new ClassLister().listClasses();
        SpringApplication.run(Application.class, args);
    }
}
