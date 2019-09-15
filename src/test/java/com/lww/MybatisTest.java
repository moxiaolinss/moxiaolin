package com.lww;

import com.lww.domain.BookInfo;
import com.lww.mapper.BookInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//单元测试两个常用注解
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisApplication.class)
public class MybatisTest {

    @Autowired
    private BookInfoMapper bookInfoMapper;//要测谁就把谁注入进来

    @Test
    public void test(){
        List<BookInfo> bookInfos = bookInfoMapper.queryBookInfoList();
        System.out.println(bookInfos);
    }
}
