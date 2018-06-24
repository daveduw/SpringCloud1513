package ho.business.user.userregister;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class UserregisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserregisterApplication.class, args);

    }
    @Value("${server.port}")
    String port;

    @RequestMapping("/User/UserRegister")
    public String UserRegister(@RequestParam String uid, String Password) {
        //Call logic jar
        return "Welcome to UserLogin Microservice " + uid + ",port:" + port;
    }
}
