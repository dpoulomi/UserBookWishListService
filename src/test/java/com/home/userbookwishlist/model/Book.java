package com.home.userbookwishlist.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book {

    private String title;

    private String author;

    private Integer isbn;

    private String dateOfPublication;

    private String user_email;

    public Book(){

    }

    public Book(int isbn , String title , String author , String dateOfPublication , String user_email){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.dateOfPublication = dateOfPublication;
        this.user_email = user_email;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {

        this.dateOfPublication = dateOfPublication;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
