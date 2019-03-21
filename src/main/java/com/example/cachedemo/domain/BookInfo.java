package com.example.cachedemo.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookInfo {
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private Date bookDate;

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookDate=" + bookDate +
                '}';
    }
}
