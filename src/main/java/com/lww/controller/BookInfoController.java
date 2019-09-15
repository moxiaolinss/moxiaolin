package com.lww.controller;

import com.lww.domain.BookInfo;
import com.lww.mapper.BookInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookInfoController {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @RequestMapping("/getBookInfo")
    public List<BookInfo> getBookInfo(){
        List<BookInfo> bookInfos = bookInfoMapper.queryBookInfoList();
        System.out.println(bookInfos.toString());
        return bookInfos;
    }
}
