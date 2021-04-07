package web.filter;

import domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Time : 2020/8/5 11:09
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("existUser");
        if (user == null || "".equals(user)) {
            System.out.println("未登录");
            request.setAttribute("msg", "请先登录！！！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } else {
            System.out.println("已登录");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
