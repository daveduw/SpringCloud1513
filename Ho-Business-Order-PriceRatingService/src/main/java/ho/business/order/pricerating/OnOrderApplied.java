package ho.business.order.pricerating;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "OrderApplied")
public class OnOrderApplied {
    @RabbitHandler
    public void process(String pid) {
        System.out.println("Receiver : " + pid);
    }
}