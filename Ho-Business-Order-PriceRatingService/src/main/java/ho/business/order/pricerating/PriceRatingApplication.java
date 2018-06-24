package ho.business.order.pricerating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableBinding(Sink.class)
public class PriceRatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PriceRatingApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/Order/PriceRating")
    public String PriceRating(@RequestParam String pid) {
        //Call logic jar
        return "Welcome to PriceRating Microservice " + pid + ",port:" + port;
    }

    private static Logger logger = LoggerFactory.getLogger(PriceRatingApplication.class);

//    @StreamListener(Sink.INPUT)
//    public void receive(Object payload) {
//        logger.info("Received: " + payload);
//    }


}