package com.lww.mapper;

import com.lww.domain.BookInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface BookInfoMapper {
    List<BookInfo> queryBookInfoList();
}
