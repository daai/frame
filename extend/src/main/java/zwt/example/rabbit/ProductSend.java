package zwt.example.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author: zwt
 * @Date: Create in 09:23 2019-05-06
 */
@Component
public class ProductSend {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitSettings settings;

    public void psSend(Object data){
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(settings.getOrderExchange(),settings.getOrderKey(),data,correlationData);
    }
}
