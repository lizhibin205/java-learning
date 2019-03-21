package com.example.cachedemo.service.impl;

import com.example.cachedemo.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void listBook() {
        bookService.listBook();
        bookService.listBook();
        bookService.listBook();
        bookService.listBook();
        bookService.listBook();
    }

    @Test
    public void listBook1() throws Exception {
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bookService.listBook();
                    latch.countDown();
                }
            }).start();
        }

        latch.await();

        Thread.currentThread().join(10000);
    }
}