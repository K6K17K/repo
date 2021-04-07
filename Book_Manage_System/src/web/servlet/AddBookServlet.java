package web.servlet;

import domain.Book;
import javafx.scene.control.Alert;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.BookService;
import service.Impl.BookServiceImpl;
import utils.IsNumUtils;
import utils.UploadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Time : 2020/8/6 12:53
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> bookMap = new HashMap<String, String>();
        //文件上传的代码
        //1 创建磁盘文件项工厂
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //2 创建核心解析类
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        //3 解析请求对象，将请求分成几个部分
        try {
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem fileItem : list) {
                //判断是普通项还是文件上传项
                if (fileItem.isFormField()) {
                    //获得普通项的名称(id name  price等)
                    String name = fileItem.getFieldName();
                    //获得普通项的值：
                    String value = fileItem.getString("UTF-8");
                    System.out.println(name + "  " + value);
                    //保存数据
                    if (!value.equals("")) {
                        bookMap.put(name, value);
                    } else {
                        request.setAttribute("msg2", "请填写完整图书信息！！！");
                        request.getRequestDispatcher("/addBook.jsp").forward(request,
                                response);
                        return;
                    }
                } else {
                    //文件上传项
                    //获得文件的名称
                    String fileName = fileItem.getName();
                    if (fileName == "") {
                        request.setAttribute("msg3", "请上传封面图片！！！");
                        request.getRequestDispatcher("/addBook.jsp").forward(request,
                                response);
                        return;
                    } else {
                        //获得唯一文件名
                        String uuidFileName = UploadUtils.getUuidFileName(fileName);
                        //获得文件的输入流
                        InputStream is = fileItem.getInputStream();
                        //将文件写到服务器的某个路径
                        String path = getServletContext().getRealPath("/upload");
                        System.out.println(path);
                        //显示图片<img src="路径">

                        //创建输出流与输入流对接
                        String url = path + "\\" + uuidFileName;
                        bookMap.put("path",
                                request.getContextPath() + "/upload/" + uuidFileName);
                        OutputStream os = new FileOutputStream(url);
                        int len = 0;
                        byte[] b = new byte[1024];
                        while ((len = is.read(b)) != -1) {
                            os.write(b, 0, len);
                        }
                        is.close();
                        os.close();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Book book = new Book();
        //调用是否非数字判断方法
        if (IsNumUtils.isNum(bookMap.get("bookPrice"))) {
            //是数字
            //封装
            book.setBookID(bookMap.get("bookId"));
            book.setBookName(bookMap.get("bookName"));
            book.setCategoryName(bookMap.get("categoryId"));
            book.setPrice(Float.valueOf(bookMap.get("bookPrice")));
            book.setCover(bookMap.get("path"));
            book.setRemarks(bookMap.get("remarks"));
            BookService bookService = new BookServiceImpl();
            bookService.addBook(book);
            request.getSession().setAttribute("bookList", BookServiceImpl.getBooks());
            response.sendRedirect(request.getContextPath() + "/bookList.jsp");
        } else {
            request.setAttribute("msg2", "图书价格不能为非数字！！！");
            request.getRequestDispatcher("/addBook.jsp").forward(request,
                    response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
