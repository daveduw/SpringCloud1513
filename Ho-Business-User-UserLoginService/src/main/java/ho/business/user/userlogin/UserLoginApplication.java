package ho.business.user.userlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@EnableEurekaClient
@SpringBootApplication
public class UserLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserLoginApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @Bean
    @LoadBalanced
    RestTemplate initRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate initRestTemplate;



    @RequestMapping("/User/UserLogin")
    public String UserLogin(@RequestParam String uid, String Password,String Type) {
        //Call logic jar
        if(Type.equals("WeChart"))
        {
            RestTemplate rt = new RestTemplate();
//            ResponseEntity<String> responseEntity1 = rt.getForEntity("http://localhost:10202/UserRegister?uid=" + uid, String.class);

            ResponseEntity<String> responseEntity = initRestTemplate.getForEntity("http://USERREGISTERSERVICE/User/UserRegister?uid=" + uid, String.class);
            String body = responseEntity.getBody();
            HttpStatus statusCode = responseEntity.getStatusCode();
            int statusCodeValue = responseEntity.getStatusCodeValue();
            HttpHeaders headers = responseEntity.getHeaders();
            StringBuffer result = new StringBuffer();
            result.append("responseEntity.getBody()：").append(body).append("<hr>")
                    .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                    .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                    .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
            return result.toString();

        }
        else{
            return "Welcome to UserLogin Microservice " + uid + ",port:" + port;

        }
    }
}

