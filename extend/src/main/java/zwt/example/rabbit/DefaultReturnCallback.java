package zwt.example.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @Author: zwt
 * @Date: Create in 16:46 2019-04-30
 */
@Slf4j
public class DefaultReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        //路由不到队列时触发回调
        log.info("默认publisher返回message：{},{},{},{},{}",message,i,s,s1,s2);
    }
}
