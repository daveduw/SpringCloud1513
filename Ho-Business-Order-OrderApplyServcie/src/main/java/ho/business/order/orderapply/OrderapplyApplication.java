package ho.business.order.orderapply;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableBinding(Sink.class)
public class OrderapplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderapplyApplication.class, args);

    }
    @Value("${server.port}")
    String port;

    @RequestMapping("/Order/OrderApply")
    public String OrderApply(@RequestParam String oid,String pid, String token) {
        //Call logic jar
        this.PublishOrderApplied(pid);
        return "Welcome to OrderApply Microservice " + oid + ",port:" + port;
    }

    @Autowired
    private AmqpTemplate rabbitTemplate;

//    @Autowired
//    private SinkSender sinkSender;

    public void PublishOrderApplied(String pid) {
        String context = pid;
        this.rabbitTemplate.convertAndSend("OrderApplied", context);
//        sinkSender.output().send(MessageBuilder.withPayload("OrderApplied").build());
    }

}