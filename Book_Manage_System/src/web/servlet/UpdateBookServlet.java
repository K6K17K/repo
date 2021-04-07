package web.servlet;

import domain.Book;
import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Time : 2020/8/6 12:27
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateBookId = request.getParameter("bookId");
        BookService bookService=new BookServiceImpl();
        Book book=bookService.getBooksById(updateBookId);
        request.getSession().setAttribute("book",book);
        response.sendRedirect(request.getContextPath() + "/updateBook.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
