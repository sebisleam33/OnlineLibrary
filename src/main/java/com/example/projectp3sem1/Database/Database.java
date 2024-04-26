package com.example.projectp3sem1.Database;

import java.sql.*;
import com.example.projectp3sem1.Book;
import java.util.List;

public interface Database {

    boolean exists(String sql);

    String getString(String str, String sql);

    int getInt(String str, String sql);

    Connection getConnection();

    void execute(String qry);

    void testConnection();

    List<Book> getBooks();

    Book getBook(int id);

}
