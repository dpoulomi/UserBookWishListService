package com.home.userbookwishlist.externalobject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@Builder
@XmlRootElement(name = "ExternalBook")
public class ExternalBook implements Serializable {

    private String title;
    private String author;
    private int isbn;
    private String dateOfPublication;
    private int bookId;

    public ExternalBook() {
    }

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
