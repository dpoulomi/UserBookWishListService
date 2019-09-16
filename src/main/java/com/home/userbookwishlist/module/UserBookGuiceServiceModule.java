package com.home.userbookwishlist.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.home.userbookwishlist.activity.admin.BookActivity;
import com.home.userbookwishlist.activity.admin.UserActivity;
import com.home.userbookwishlist.activity.user.UserWishListActivity;
import com.home.userbookwishlist.dao.BookDao;
import com.home.userbookwishlist.dao.UserDao;
import com.home.userbookwishlist.dao.UserWishListDao;
import com.home.userbookwishlist.jdbc.BookJdbcHelper;
import com.home.userbookwishlist.jdbc.UserJdbcHelper;
import com.home.userbookwishlist.jdbc.UserWishListJdbcHelper;
import com.home.userbookwishlist.service.BookService;
import com.home.userbookwishlist.service.UserService;
import com.home.userbookwishlist.service.UserWishListService;
import com.home.userbookwishlist.sqlitedao.BookSqliteDao;
import com.home.userbookwishlist.sqlitedao.UserSqliteDao;
import com.home.userbookwishlist.sqlitedao.UserWishListSqliteDao;
import com.home.userbookwishlist.sqlitedb.SqliteConnection;

@Singleton
public class UserBookGuiceServiceModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    @Named("JdbcDriverName")
    public String provideJdbcDriverName() {
        return "org.sqlite.JDBC";
    }

    @Provides
    @Singleton
    @Named("DatabaseUrl")
    public String provideDatabaseUrl() {
        return "jdbc:sqlite:/Users/neo/sqllite_db/sqllite_db.db";
    }

    @Provides
    @Singleton
    public SqliteConnection provideSqliteConnection(@Named("DatabaseUrl") final String databaseUrl,
                                                    @Named("JdbcDriverName") final String jdbcDriverName) {
        return new SqliteConnection(databaseUrl, jdbcDriverName);
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
    public BookService provideBookService(final UserWishListDao userWishListDao,
                                          final BookDao bookDao) {
        return new BookService(userWishListDao, bookDao);
    }

    @Provides
    @Singleton
    public BookActivity provideAddBookActivity(final BookService bookService) {
        return new BookActivity(bookService);
    }


    @Provides
    @Singleton
    public UserActivity provideUserActivity(final UserService userService) {
        return new UserActivity(userService);
    }

    @Provides
    @Singleton
    public UserService provideUserService(final UserWishListDao userWishListDao,
                                          final UserDao userDao) {
        return new UserService(userWishListDao, userDao);
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(final UserJdbcHelper userJdbcHelper) {
        return new UserSqliteDao(userJdbcHelper);
    }

    @Provides
    @Singleton
    public UserJdbcHelper provideUserJdbcHelper(final SqliteConnection sqliteConnection) {
        return new UserJdbcHelper(sqliteConnection.connect());
    }

    @Provides
    @Singleton
    public UserWishListActivity provideUserWishListActivity(final UserWishListService userWishListService) {
        return new UserWishListActivity(userWishListService);
    }

    @Provides
    @Singleton
    public UserWishListService provideUserWishListService(final UserWishListDao userWishListDao,
                                                          final UserDao userDao,
                                                          final BookDao bookDao) {
        return new UserWishListService(userWishListDao, userDao, bookDao);
    }

    @Provides
    @Singleton
    public UserWishListDao provideUserWishListDao(final UserWishListJdbcHelper userWishListJdbcHelper) {
        return new UserWishListSqliteDao(userWishListJdbcHelper);
    }

    @Provides
    @Singleton
    public UserWishListJdbcHelper provideUserWishListJdbcHelper(final SqliteConnection sqliteConnection) {
        return new UserWishListJdbcHelper(sqliteConnection.connect());
    }

}
