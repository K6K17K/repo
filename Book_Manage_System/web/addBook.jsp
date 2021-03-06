<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>新建图书信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/add.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/dept/list.do">
                图书信息管理
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <h1>Hello, ${username}!</h1>
        <p>请小心地新增图书信息，要是建了一个错误的就不好了。。。</p>
        <p style="color: red;font-weight: bold">${msg2}</p>
        <p style="color: red;font-weight: bold">${msg3}</p>
    </div>
    <div class="page-header">
        <h3><small>新建</small></h3>
    </div>
    <form class="form-horizontal"
          action="${pageContext.request.contextPath}/AddBookServlet"
          method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label for="bookId" class="col-sm-2 control-label">图书编号 ：</label>
            <div class="col-sm-8">
                <input name="bookId" class="form-control" id="bookId">
            </div>
        </div>
        <div class="form-group">
            <label for="bookName" class="col-sm-2 control-label">图书名称 ：</label>
            <div class="col-sm-8">
                <input name="bookName" class="form-control" id="bookName">
            </div>
        </div>
        <div class="form-group">
            <label for="categoryId" class="col-sm-2 control-label">分类 ：</label>
            <select id="categoryId" name="categoryId"
                    class="col-sm-2 form-control"
                    style="width: auto;margin-left: 15px">
                <%--                       <option value="ca0001" selected="">计算机</option>--%>
                <%--                       <option value="ca0002">文学</option>--%>
                <%--                       <option value="ca0003">历史</option>--%>
                <!-- 下拉列表的内容要从分类中进行读取，value值是分类id -->
                <c:forEach items="${categoryDb}"
                           var="ca" varStatus="idx">
                    <option value="${ca.classificationID}">
                            ${ca.classificationName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="bookPrice" class="col-sm-2 control-label">价格 ：</label>
            <div class="col-sm-8">
                <input name="bookPrice" class="form-control" id="bookPrice">
            </div>
        </div>

        <div class="form-group">
            <label for="bookPic" class="col-sm-2 control-label">图书封面 ：</label>
            <input type="file" id="bookPic" name="bookPic"
                   style="padding-left: 15px">
        </div>

        <div class="form-group">
            <label for="remarks" class="col-sm-2 control-label">备注 ：</label>
            <div class="col-sm-8">
                <input name="remarks" class="form-control" id="remarks">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </form>
</div>
<footer class="text-center">
    copy@imooc
</footer>
</body>
</html>
