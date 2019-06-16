package test;

import alpha.RedisUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class TestRedis_Spring {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        RedisUtil redisUtil= (RedisUtil) context.getBean("redisUtil");

        redisUtil.set("name","alpha");
        System.out.println(redisUtil.get("name"));
        redisUtil.del("name");
        System.out.println(redisUtil.get("name"));

        long incr=redisUtil.incr("number",1);
        System.out.println(incr);
        incr=redisUtil.incr("number",1);
        System.out.println(incr);

        Map<String,Object> map=new HashMap<>();
        map.put("name","meepo");
        map.put("pwd","password");
        redisUtil.hmset("user",map);
        System.out.println(redisUtil.hget("user","name"));
    }
}
