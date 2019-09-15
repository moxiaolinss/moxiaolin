package com.lww;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lww.domain.BookInfo;
import com.lww.mapper.BookInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class RedisTest {

    //只要导入redis坐标了，配置了信息，springBoot会自动创建一个redis模板存储在容器中
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Test
    public void testRedis() throws JsonProcessingException {
        //我们一般会先从缓存中拿数据，如果没有才会从数据库去查，然后再存入缴存中。
        //1、从redis中获取数据，数据形式json字符串
        String bookInfoJson = redisTemplate.boundValueOps("bookInfo.findAll").get();
        //2、判断redis中是否存在数据
        if (null==bookInfoJson){
              //3、不存在数据，从数据库中查
            List<BookInfo> bookInfos = bookInfoMapper.queryBookInfoList();
            //4、将查询到的数据存储到redis缴存中
            //因查到的是List,所以我要再转成json字符串,因springBoot集成了web，我就用jackson转换
            ObjectMapper objectMapper = new ObjectMapper();
            bookInfoJson = objectMapper.writeValueAsString(bookInfos);
            redisTemplate.boundValueOps("bookInfo.findAll").set(bookInfoJson);
            System.out.println("============从数据库中查============");
        }else {
            System.out.println("============从redis缓存中查============");
        }
        //4、将数据在控制台打印
        System.out.println("数据："+bookInfoJson);
    }


}
