package zwt.example.rabbit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;

import java.util.UUID;

/**
 * @Author: zwt
 * @Date: Create in 16:53 2019-05-05
 */
public class BeforePublishPostProcessors implements MessagePostProcessor {
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setCorrelationId(UUID.randomUUID().toString());
        return message;
    }

    @Override
    public Message postProcessMessage(Message message, Correlation correlation) {
        return this.postProcessMessage(message);
    }
}
