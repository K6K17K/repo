package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Time : 2020/8/5 11:10
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebFilter(filterName = "EncodeFilter")
public class EncodeFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        System.out.println("字符编码过滤器已启用！。。。。。");
        System.out.println("initencoding:"+encoding);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        request.setCharacterEncoding(encoding);
        HttpServletResponse response= (HttpServletResponse) resp;
        response.setContentType("text/html;charset="+ encoding);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }

}
