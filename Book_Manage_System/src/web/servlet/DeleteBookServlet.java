package web.servlet;

import service.BookService;
import service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Time : 2020/8/6 12:28
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dBook = request.getParameter("bookId");
        BookService bookService=new BookServiceImpl();
        bookService.deleteBook(dBook);
        request.getSession().setAttribute("bookList",BookServiceImpl.getBooks());
        request.getRequestDispatcher("/bookList.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
