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
@Profile("local")
public class SpringConfigurationLocal {

    @Bean
    @Primary
    public ProductLookup productLookup() {
        return new ResourceBasedProductLookup();
    }

    @Bean
    public ProductInformationServiceUrlBuilder urlBuilder(){
        return null;
    }
}
