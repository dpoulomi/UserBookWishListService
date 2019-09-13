package com.home.userbookwishlist.externalobject;

import lombok.Builder;
import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Builder
@XmlRootElement
public class ExternalBook {

    private final String title;
    private final String author;
    private final int isbn;
    private final String dateOfPublication;
    private final int bookId;

    private ExternalBook(final String title,
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
