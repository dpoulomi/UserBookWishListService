package com.home.userbookwishlist.model;

import com.google.inject.spi.Toolable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;


@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Book {

    private String title;
    private String author;
    private int isbn;
    private String dateOfPublication;
    private int bookId;

    public Book(){

    }

    public Book(final String title,
                final String author,
                final int isbn,
                final String dateOfPublication,
                final int bookId){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.dateOfPublication = dateOfPublication;
        this.bookId = bookId;
    }
}
