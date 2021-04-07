package web.servlet;

import domain.Category;
import service.CategoryService;
import service.Impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Time : 2020/8/5 19:45
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        CategoryService categoryService = new CategoryServiceImpl();
        Pattern p = Pattern.compile("^ca[0-9]{4}$");
        Matcher m = p.matcher(categoryId);
        if (m.find()) {
            categoryService.addCategory(categoryId, categoryName);
            request.getServletContext().setAttribute("categoryDb",
                    CategoryServiceImpl.getCategoryDb());
            response.sendRedirect(request.getContextPath()+"/categoryList.jsp");
        }else {
            request.setAttribute("msg1","分类编号的格式必须为‘ca’+'xxxx'(x为数字),请重新输入！！！");
            request.getRequestDispatcher("/addCategory.jsp").forward(request,
                    response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
