package cms.brixo.se;

import cms.brixo.se.service.CreditManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BrixoApplication {
    @Autowired
    private CreditManageService creditManageService;

    public static void main(String[] args) {
        SpringApplication.run(BrixoApplication.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return args -> {
        };
    }

    @Bean
    public void getAll() {
        creditManageService.getDebtors();
    }

    ;

}
