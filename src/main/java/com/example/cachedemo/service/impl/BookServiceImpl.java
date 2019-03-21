package com.example.cachedemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.cachedemo.common.service.RedisService;
import com.example.cachedemo.domain.BookInfo;
import com.example.cachedemo.mapper.BookMapper;
import com.example.cachedemo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    public static final String LIST_BOOK = "book:list_book";

    @Resource
    private BookMapper bookMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public synchronized List<BookInfo> listBook() {

        // 判断缓存中是否存在查询结果
        String data = redisService.get(LIST_BOOK);

        if (data != null) {
            log.info("-------------data from cache");
            return JSONObject.parseArray(data, BookInfo.class);
        } else {
            log.info("-------------data from db");
            // 从数据库中获取
            List<BookInfo> listBook = bookMapper.listBook();
            // 存储到缓存中
            redisService.set(LIST_BOOK, JSON.toJSONString(listBook));
            // 设置缓存时间
            redisService.expire(LIST_BOOK, 8);

            return listBook;
        }
    }
}
