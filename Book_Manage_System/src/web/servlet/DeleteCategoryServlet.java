package web.servlet;

import service.CategoryService;
import service.Impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Time : 2020/8/5 21:49
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebServlet("/DeleteCategoryServlet")
public class DeleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        CategoryService categoryService = new CategoryServiceImpl();
        categoryService.deleteCategory(categoryId);
        request.getServletContext().setAttribute("categoryDb",
                CategoryServiceImpl.getCategoryDb());
        request.getRequestDispatcher("/categoryList.jsp").forward(request,
                    response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
