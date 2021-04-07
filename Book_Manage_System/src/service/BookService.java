package service;

import domain.Book;

import java.util.List;

/**
 * @Time : 2020/8/5 10:17
 * @Author : KKK
 * @File : BookService.java
 * @Software: IntelliJ IDEA
 **/
public interface BookService {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(String bookId);
    Book getBooksById(String bookID);
    List<Book> getBooksByCategoryName(String categoryName);
}
