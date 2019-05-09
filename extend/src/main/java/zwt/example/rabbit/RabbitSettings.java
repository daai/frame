package zwt.example.rabbit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: zwt
 * @Date: Create in 09:35 2019-05-06
 */
@ConfigurationProperties(prefix = "spring.rabbitmq")
@PropertySource("classpath:rabbit.properties")
@Component
public class RabbitSettings {

    @Value("${rabbit.order.exchange}")
    private String orderExchange;
    @Value("${rabbit.order.queue}")
    private String orderQueue;
    @Value("${rabbit.order.key}")
    private String orderKey;

    public String getOrderExchange() {
        return orderExchange;
    }

    public void setOrderExchange(String orderExchange) {
        this.orderExchange = orderExchange;
    }

    public String getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(String orderQueue) {
        this.orderQueue = orderQueue;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }
}
