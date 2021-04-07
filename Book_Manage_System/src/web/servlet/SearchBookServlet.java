package web.servlet;

import com.alibaba.fastjson.JSON;
import domain.Book;
import service.BookService;
import service.Impl.BookServiceImpl;
import service.Impl.CategoryServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Time : 2020/8/6 10:27
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebServlet("/SearchBookServlet")
public class SearchBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ServletContext context = request.getServletContext();
        String categoryName = request.getParameter("searchContent");
        System.out.println(categoryName);
        BookService bookService = new BookServiceImpl();
        List<Book> searchList = bookService.getBooksByCategoryName(categoryName);
        if (searchList.isEmpty()) {
            searchList =
                    (List<Book>) request.getServletContext().getAttribute(
                            "book1");
        }
        String json = JSON.toJSONString(searchList);
        System.out.println(json);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(json);
//        request.getRequestDispatcher("/bookList.jsp").forward(request,response);
//        response.sendRedirect(request.getContextPath() + "/bookList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
