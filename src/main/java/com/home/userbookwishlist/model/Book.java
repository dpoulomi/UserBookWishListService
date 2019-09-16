package com.home.userbookwishlist.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Book {

    /**
     *  PRIMARY KEY
     */
    private int bookId;

    private String title;
    private String author;
    private int isbn;
    private String dateOfPublication;

    public Book(){
    }

    public Book(final int bookId,
                final String title,
                final String author,
                final int isbn,
                final String dateOfPublication){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.dateOfPublication = dateOfPublication;
        this.bookId = bookId;
    }

    /*public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private int bookId;
        private String title;
        private String author;
        private int isbn;
        private String dateOfPublication;

        private Builder() {
        }

        public static Builder aBook() {
            return new Builder();
        }

        public Builder bookId(int bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder isbn(int isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder dateOfPublication(String dateOfPublication) {
            this.dateOfPublication = dateOfPublication;
            return this;
        }

        public Book build() {
            return new Book(bookId, title, author, isbn, dateOfPublication);
        }
    }*/
}
