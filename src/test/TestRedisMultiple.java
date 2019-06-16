package test;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestRedisMultiple {
    JedisPool pool;
    Jedis jedis;

    @Before
    public void setUp(){
        jedis=new Jedis("localhost");
    }

    @Test
    public void testBasicString(){
        jedis.set("name","meepo");
        System.out.println(jedis.get("name"));

        jedis.append("name","dota");
        System.out.println(jedis.get("name"));

        jedis.set("name","poofu");
        System.out.println(jedis.get("name"));

        jedis.del("name");
        System.out.println(jedis.get("name"));

        jedis.mset("name","meepo","dota","poofu");
        System.out.println(jedis.mget("name","dota"));

        System.out.println("---------------------------------->");
    }

    @Test
    public void testMap(){
        Map<String,String> user=new HashMap<>();
        user.put("name","meepo");
        user.put("pwd","password");
        user.put("hobby","program");
        jedis.hmset("user",user);

        List<String> rsmap=jedis.hmget("user","name");
        System.out.println(rsmap);

        jedis.hdel("user","pwd");
        System.out.println(jedis.hmget("user","pwd"));
        System.out.println(jedis.hlen("user"));
        System.out.println(jedis.exists("user"));
        System.out.println(jedis.hkeys("user"));
        System.out.println(jedis.hvals("user"));

        Iterator<String> iter=jedis.hkeys("user").iterator();
        while (iter.hasNext()){
            String key=iter.next();
            System.out.println(key+":"+jedis.hmget("user",key));
        }

        System.out.println("---------------------------------->");

    }

    @Test
    public void testList(){
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework",0,-1));

        jedis.lpush("java framework","spring");
        jedis.lpush("java framework","struts");
        jedis.lpush("java framework","hibernate");

        System.out.println(jedis.lrange("java framework",0,-1));
        System.out.println("---------------------------------->");

    }

    @Test
    public void testSet(){
        jedis.sadd("sname","meepo");
        jedis.sadd("sname","dota");
        jedis.sadd("sname","poofu");
        jedis.sadd("sname","noname");

        jedis.srem("sname","noname");
        System.out.println(jedis.smembers("sname"));
        System.out.println(jedis.sismember("sname","meepo"));
        System.out.println(jedis.srandmember("sname"));
        System.out.println(jedis.scard("sname"));
    }

    @Test
    public void test() throws InterruptedException{
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.keys("*name"));
        System.out.println(jedis.del("sanmdde"));
        System.out.println(jedis.ttl("sname"));
        jedis.setex("timekey",10,"min");
        Thread.sleep(5000);
        System.out.println(jedis.ttl("timekey"));
        jedis.setex("timekey",1,"min");
        System.out.println(jedis.ttl("timekey"));
        System.out.println(jedis.exists("key"));
        System.out.println(jedis.rename("timekey","time"));
        System.out.println(jedis.get("timekey"));
        System.out.println(jedis.get("time"));

        jedis.del("a");
        jedis.rpush("a","1");
        jedis.lpush("a","6");
        jedis.lpush("a","3");
        jedis.lpush("a","9");
        System.out.println(jedis.lrange("a",0,-1));
        System.out.println(jedis.sort("a"));
        System.out.println(jedis.lrange("a",0,-1));

    }


}
