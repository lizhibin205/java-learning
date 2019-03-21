package com.example.cachedemo.mapper;

import com.example.cachedemo.domain.BookInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {

    @Select("SELECT BOOK_ID, BOOK_NAME, BOOK_AUTHOR, BOOK_DATE FROM T_BOOK")
    List<BookInfo> listBook();
}
