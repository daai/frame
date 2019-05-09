package zwt.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zwt.example.entity.TUser;
import zwt.example.mapper.TUserMapper;
import zwt.example.rabbit.ProductSend;

import java.util.Date;

/**
 * @Author: zwt
 * @Date: Create in 09:51 2019-05-07
 */
@RestController
public class HelloController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProductSend productSend;
    @Autowired(required = false)
    private TUserMapper userMapper;

    @GetMapping("hello")
    public Object hello(){
        return "hello";
    }

    @GetMapping("redis")
    public Object redis(){
        redisTemplate.opsForValue().set("hello","hello");
        return "hello";
    }

    @GetMapping("rabbit")
    public Object rabbit(){
        productSend.psSend("hello");
        return "hello";
    }

    @GetMapping("mybatis")
    public Object mybatis(){
        userMapper.insert(new TUser("test",11,new Date(),new Date()));
        return "hello";
    }
}
