<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书后台管理</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
<header>
    <div class="container">
        <nav>
            <a href="bookList.jsp">图书信息管理</a>
        </nav>
        <nav>
            <a href="categoryList.jsp">分类管理</a>
        </nav>

    </div>
</header>
<section class="banner">
    <div class="container">
        <div>
            <h1>图书管理系统</h1>
            <p>图书分类管理</p>
        </div>
    </div>
</section>
<section class="main">
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>序号</th>
                <th>分类编号</th>
                <th>分类名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categoryDb}"
                       var="ca" varStatus="idx">
                <tr>
                    <td>${idx.index+1}</td>
                    <td>${ca.classificationID}</td>
                    <td>${ca.classificationName}</td>
                    <td><a href="${pageContext.request.contextPath}/DeleteCategoryServlet?categoryId=${ca.classificationID}">删除</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<section class="page">
    <div class="container">
        <div id="fatie">
            <a href="addCategory.jsp">
                <button>新建</button>
            </a>
        </div>
    </div>
</section>
<footer>
    copy@慕课网
</footer>
</body>
</html>