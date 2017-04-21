package packt.java9.by.example.mybusiness.productinformation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import packt.java9.by.example.mybusiness.productinformation.lookup.ResourceBasedProductLookup;
import packt.java9.by.example.mybusiness.productinformation.lookup.RestClientProductLookup;

@Configuration
@Profile("production")
public class SpringConfiguration {

    @Bean
    @Primary
    public ProductLookup productLookup() {
        return new RestClientProductLookup(urlBuilder());
    }

    @Bean
    public ProductInformationServiceUrlBuilder urlBuilder(){
        return new ProductInformationServiceUrlBuilder("http://localhost");
    }
}
