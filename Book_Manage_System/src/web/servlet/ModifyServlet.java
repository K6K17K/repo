package web.servlet;

import domain.Book;
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
 * @Time : 2020/8/7 14:53
 * @Author : KKK
 * @File : ${NAME}.java
 * @Software: IntelliJ IDEA
 **/
@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入修改界面！！！！");
        Map<String, String> bookMap1 = new HashMap<String, String>();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload fileUpload1 =
                new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> list1 = fileUpload1.parseRequest(request);
            for (FileItem fileItem : list1) {
                if (fileItem.isFormField()) {
                    String name1 = fileItem.getFieldName();
                    String value1 = fileItem.getString("UTF-8");
                    System.out.println(name1 + "  " + value1);
                    if (!value1.equals("")) {
                        bookMap1.put(name1, value1);
                    } else {
                        request.setAttribute("msg2", "请填写完整图书信息！！！");
                        request.getRequestDispatcher("/updateBook.jsp").forward(request,
                                response);
                        return;
                    }
                } else {
                    String fileName1 = fileItem.getName();
                    if (fileName1 == "") {
                        request.setAttribute("msg3", "请上传封面图片！！！");
                        request.getRequestDispatcher("/updateBook.jsp").forward(request,
                                response);
                        return;
                    } else {
                        //获得唯一文件名
                        String uuidFileName1 =
                                UploadUtils.getUuidFileName(fileName1);
                        //获得文件的输入流
                        InputStream is1 = fileItem.getInputStream();
                        //将文件写到服务器的某个路径
                        String path1 = getServletContext().getRealPath(
                                "/upload");
                        System.out.println(path1);
                        //显示图片<img src="路径">

                        //创建输出流与输入流对接
                        String url1 = path1 + "\\" + uuidFileName1;
                        bookMap1.put("path",
                                request.getContextPath() + "/upload/" + uuidFileName1);
                        OutputStream os1 = new FileOutputStream(url1);
                        int len = 0;
                        byte[] b = new byte[1024];
                        while ((len = is1.read(b)) != -1) {
                            os1.write(b, 0, len);
                        }
                        is1.close();
                        os1.close();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Book book1 = new Book();
        if (IsNumUtils.isNum(bookMap1.get("bookPrice"))) {
            //是数字
            //封装
            book1.setBookID(bookMap1.get("bookId"));
            book1.setBookName(bookMap1.get("bookName"));
            book1.setCategoryName(bookMap1.get("categoryId"));
            book1.setPrice(Float.valueOf(bookMap1.get("bookPrice")));
            book1.setCover(bookMap1.get("path"));
            book1.setRemarks(bookMap1.get("remarks"));
            BookService bookService = new BookServiceImpl();
            bookService.updateBook(book1);
            request.getSession().setAttribute("bookList", BookServiceImpl.getBooks());
            response.sendRedirect(request.getContextPath() + "/bookList.jsp");
        } else {
            request.setAttribute("msg2", "图书价格不能为非数字！！！");
            request.getRequestDispatcher("/updateBook.jsp").forward(request,
                    response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        doGet(request, response);
    }
}
