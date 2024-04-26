package com.example.projectp3sem1.Database;

import java.sql.*;

import com.example.projectp3sem1.Book;

import java.util.ArrayList;
import java.util.List;

public abstract class MySQL implements Database {

    private MySQLSettings settings;

    public MySQL(MySQLSettings settings) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.settings = settings;
        testConnection();
        defaultTables();
    }

    public abstract void defaultTables();

    public Book getBook(int id) {
        Book book = null;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(String.format("SELECT * FROM `books` WHERE `ID`='%d';", id));

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                String description = rs.getString("description");
                String file = rs.getString("cover");
                book = new Book(id, bookName, author, description, file);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `books`;");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                String description = rs.getString("description");
                String file = rs.getString("cover");
                books.add(new Book(id, bookName, author, description, file));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean exists(String sql) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        boolean b = false;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return b;
    }

    @Override
    public String getString(String str, String sql) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        String s = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = rs.getString(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return s;
    }

    @Override
    public int getInt(String str, String sql) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        int s = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                s = rs.getInt(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return s;
    }

    @Override
    public Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + settings.getHost() + ":" + settings.getPort() + "/" + settings.getDatabase() + "?useSSL=false", settings.getUser(), settings.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public void execute(String qry) {
        Connection con = null;
        PreparedStatement p = null;

        con = getConnection();
        try {
            p = con.prepareStatement(qry);
            p.execute();
        } catch (SQLException e) {
            System.err.println("Error executing SQL(" + qry + ") " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (p != null) {
                    p.close();
                }
            } catch (Exception ignored) {

            }
        }
    }

    @Override
    public void testConnection() {
        boolean using_password = true;
        if (settings.getPassword().equalsIgnoreCase("") || settings.getPassword() == null) using_password = false;
        System.out.println("Checking the connection to database('" + settings.getDatabase() + "') hosted as '" + settings.getUser() + "@" + settings.getHost() + ":" + settings.getPort() + "' using password: " + (using_password ? "YES" : "NO"));

        try {
            DriverManager.getConnection("jdbc:mysql://" + settings.getHost() + ":" + settings.getPort() + "/" + settings.getDatabase() + "?useSSL=false", settings.getUser(), settings.getPassword());
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.err.println("An error occurred trying connecting to database! " + e.getMessage());
        }

    }


}