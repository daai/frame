package zwt.example.rabbit;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Author: zwt
 * @Date: Create in 10:22 2019-05-06
 */
@RabbitListener(queues = "order_queue")
@Slf4j
@Component
public class ConsumerReceive {

    @RabbitHandler
    public void receive(@Payload String data, @Headers Map<String, Object> heads, Channel channel) throws IOException {
        log.info("收到消费");
        log.info("接收值：{}",data);
        Long delivery = (Long) heads.get(AmqpHeaders.DELIVERY_TAG);

        channel.basicAck(delivery, false);
    }

}
