package service.Impl;

import domain.Book;
import service.BookService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time : 2020/8/5 22:38
 * @Author : KKK
 * @File : BookServiceImpl.java
 * @Software: IntelliJ IDEA
 **/
public class BookServiceImpl implements BookService {
    private static final List<Book> books = new ArrayList<Book>();

    public static List<Book> getBooks(){
        return books;
    };

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void updateBook(Book book) {
        deleteBook(book.getBookID());
        books.add(book);
    }

    @Override
    public void deleteBook(String bookId) {
        for (Book book : books) {
            if (book.getBookID().equals(bookId)) {
                books.remove(book);
                break;
            }
        }
    }


    @Override
    public Book getBooksById(String bookID) {
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getBooksByCategoryName(String categoryName) {
        List<Book> books1 = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getCategoryName().equals(categoryName)) {
                books1.add(book);
            }
        }
        return books1;
    }
}
