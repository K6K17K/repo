<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
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
            <p>图书信息管理</p>
        </div>
    </div>
</section>
<section class="main">


    <div class="container">
        <form class="form-horizontal"
              action="${pageContext.request.contextPath}/SearchBookServlet"
              method="post">
            <div class="form-group" style="float: right;">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="search" type="button" class="btn btn-primary"
                            onclick="query()">查询
                    </button>&nbsp;&nbsp;&nbsp;
                    <!-- <button type="submit" class="btn btn-primary" onclick="query()">查询</button>&nbsp;&nbsp;&nbsp; -->
                </div>
            </div>
            <div class="form-group" style="float: right;width: 300px;">
                <div class="col-sm-8">
                    <input name="searchContent" class="form-control"
                           id="searchContent"
                           placeholder="输入要查询的分类" style="width: 250px">
                </div>
            </div>
        </form>
    </div>
    <div class="container">

        <table class="table table-striped">
            <thead>
            <tr>
                <th>序号</th>
                <th>图书编号</th>
                <th>图书名称</th>
                <th>分类</th>
                <th>价格</th>
                <th>图书封面</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="cont-Search">
            <c:forEach items="${applicationScope.books }" var="bo"
                       varStatus="idx">
            <tr id="tr1">
                <td>${idx.index+1 }</td>
                <td>${bo.bookId }</td>
                <td>${bo.bookName }</td>
                <td>${bo.bookCategory }</td>
                <td>￥${bo.bookPrice }</td>
                <td><img src="${bo.bookCoverPath }"></td>
                <td>
                    <a href="${pageContext.request.contextPath}/GetBookIdServlet?bookId=${bo.bookId }">修改</a>
                        <%-- <a href="${pageContext.request.contextPath}/UpdateBookServlet?bookId=${bo.bookId }">修改</a> --%>
                    <a href="${pageContext.request.contextPath}/DeleteBookServlet?bookId=${bo.bookId }">删除</a>
                </td>

                </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<section class="page">
    <div class="container">
        <div id="fatie">
            <a href="addBook.jsp">
                <button>新建</button>
            </a>
        </div>
    </div>
</section>
<footer>
    copy@慕课网
</footer>
</body>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
    function query() {
        $.ajax({
            "url": "/book-manage/SearchBookServlet",
            //用json对象的形式设置向服务器发送的请求参数。请求参数的名为searchContent，值为$("#searchContent").val()
            "data": {"searchContent": $("#searchContent").val()},
            "type": "get",
            "dataType": "json",//jQuery的ajax函数会自动将服务器返回的文本解析成json对象
            "success": function (json) {
                console.log(json);
                $("#cont-Search>tr").remove();
                var content = "";
                for (var i = 0; i < json.length; i++) {
                    var ch = json[i];//ch接收的是json对象，json对象可以点出属性
                    content = content + "<tr id='tr" + (i + 1) + "'>" +
                        "<td>" + i + 1 + "</td>" +
                        "<td>" + ch.bookId + "</td>" +
                        "<td>" + ch.bookName + "</td>" +
                        "<td>" + ch.bookCategory + "</td>" +
                        "<td>￥" + ch.bookPrice + "</td>" +
                        "<td><img src=" + ch.bookCoverPath + "></td>" +
                        "<td><a href='${pageContext.request.contextPath}/GetBookIdServlet?bookId='" + ch.bookId + ">修改</a>" +
                        "<a href='${pageContext.request.contextPath}/DeleteBookServlet?bookId='" + ch.bookId + ">删除</a></td></tr>";
                }
                $("#cont-Search").html(content);//设置cont也就是<tbody>中的内容
            }
        })
    }

    /* query(); */
</script>
</html>