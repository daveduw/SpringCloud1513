package ho.business.order.orderapply;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SinkSender {
    String OUTPUT = "input";
    @Output("OrderApplied")
    MessageChannel output();
}
