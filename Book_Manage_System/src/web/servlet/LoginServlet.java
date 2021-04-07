package web.servlet;

import com.google.code.kaptcha.Constants;
import com.sun.org.apache.regexp.internal.RE;
import domain.User;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Time : 2020/8/5 12:32
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //校验验证码
        String code1 = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String code2 = request.getParameter("verifyCode");
        if (code2==null||!code2.equalsIgnoreCase(code1)){
            request.setAttribute("msg", "验证码输入有误！！！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //校验用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        UserService userService=new UserServiceImpl();
        List<User> userList = (List<User>) getServletContext().getAttribute("userList");
        User existUser = userService.login(userList, user);
        if (existUser==null){
            //登录失败
        request.setAttribute("msg","用户名或密码错误！！！");
        request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else {
            //登录成功，保存信息
            request.getSession().setAttribute("username",user.getUsername());
            request.getSession().setAttribute("password",user.getPassword());
            request.getSession().setAttribute("existUser",existUser);
            response.sendRedirect(request.getContextPath()+"/categoryList.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
