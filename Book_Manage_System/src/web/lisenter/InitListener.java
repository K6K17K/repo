package web.lisenter; /**
 * @Time : 2020/8/5 9:14
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/

import domain.User;
import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;
import java.util.List;

@WebListener()
public class InitListener implements ServletContextListener{

    public InitListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("项目启动成功!.......");
        List<User> userList=new ArrayList<>();
        User user=new User();
        user.setUsername("admin");
        user.setPassword("admin");
        UserService userService=new UserServiceImpl();
        userService.regist(userList, user);
        sce.getServletContext().setAttribute("userList",userList);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
