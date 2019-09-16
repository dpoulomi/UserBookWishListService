package com.home.userbookwishlist.constants;

public final class Constants {

    public static class TableName {
        public static final String USERS_TABLE = "Users";
        public static final String BOOKS_TABLE = "Books";
        public static final String USER_WISH_LIST_TABLE = "UserWishList";
    }

    public static class UsersTableAttributeName {
        public static final String USERS_ID = "user_id";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
    }


    public static class BooksTableAttributeName {
        public static final String BOOK_ID = "book_id";
        public static final String ISBN = "isbn";
        public static final String AUTHOR = "author";
        public static final String TITLE = "title";
        public static final String DATE_OF_PUBLICATION = "date_of_publication";
    }

    public static class UserWishListTableAttributeName {
        public static final String USER_ID = "user_id";
        public static final String BOOK_ID = "book_id";
    }

}
