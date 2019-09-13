package com.home.userbookwishlist.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.home.userbookwishlist.activity.admin.BookActivity;
import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.jdbc.BookJdbcHelper;
import com.home.userbookwishlist.service.BookService;
import com.home.userbookwishlist.sqlitedao.BookSqliteDao;
import com.home.userbookwishlist.sqlitedb.SqliteConnection;

public class UserBookGuiceServiceModule extends AbstractModule {

    @Provides
    @Singleton
    public SqliteConnection provideSqliteConnection() {
        return new SqliteConnection("jdbc:sqlite:/Users/neo/sqllite_db/sqllite_db.db");
    }

    @Provides
    @Singleton
    public BookJdbcHelper provideAddBookJdbcHelper(final SqliteConnection sqliteConnection) {
        return new BookJdbcHelper(sqliteConnection.connect());
    }

    @Provides
    @Singleton
    public BookDao provideBookDao(final BookJdbcHelper addBookJdbcHelper) {
        return new BookSqliteDao(addBookJdbcHelper);
    }

    @Provides
    @Singleton
    public BookService provideBookService(final BookDao bookDao) {
        return new BookService(bookDao);
    }

    @Provides
    @Singleton
    public BookActivity provideAddBookActivity(final BookService bookService) {
        return new BookActivity(bookService);
    }

    @Override
    protected void configure() {
    }
}
