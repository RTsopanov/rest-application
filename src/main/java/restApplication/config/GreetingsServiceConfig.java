package restApplication.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import restApplication.bussines.GreetingsService;
import restApplication.bussines.GreetingsService2;
import restApplication.bussines.GreetingsServiceInterface;
import restApplication.data.Employee;


@Configuration
@Slf4j
public class GreetingsServiceConfig {

    @Bean
    @Profile("!test")
    GreetingsServiceInterface getGS(){
      return new GreetingsService(log);
    }

    @Bean
    @Profile("test")
    GreetingsServiceInterface getGS2(){
      return new GreetingsService2(log);
    }

//    @Bean
//    Employee makDefaultEmployee(){
//        return new Employee(null, "Anton", "Antonov", null, null, null, null);
//    }


}
