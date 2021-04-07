<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
              method="get">
            <div class="form-group" style="float: right;">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="search" type="button" class="btn btn-primary">查询
                    </button>&nbsp;&nbsp;&nbsp;
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
            <c:forEach items="${bookList}" var="b" varStatus="idx">
                <tr id="tr1">
                    <td>${idx.index+1}</td>
                    <td>${b.bookID}</td>
                    <td>${b.bookName}</td>
                    <td>${b.categoryName}</td>
                    <td>${b.price}</td>
                    <td><img src="${b.cover}"></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/UpdateBookServlet?bookId=${b.bookID}">修改</a>
                        <a href="${pageContext.request.contextPath}/DeleteBookServlet?bookId=${b.bookID}">删除</a>
                    </td>
                    <!--在循环显示数据时，此处的book0001可以用EL表达式进行替换-->
                </tr>
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
<script type="text/javascript" src="JS/jquery-3.5.1.js"></script>
<script type="text/javascript">
    $("#search").click(function(){
        $.ajax({
            url: "/Book_Manage_System/SearchBookServlet",
            data: {"searchContent": $("#searchContent").val()},
            dataType: "json",
            type: "post",
            success: function (json) {
                // alert(json);
                if ($("#searchContent").val() == "") {
                    alert("要查询的图书分类不能为空！")
                } else {
                    $("#cont-Search>tr").remove();
                    var content = "";
                    for (var i = 0; i < json.length; i++) {
                        var ch = json[i];//ch接收的是json对象
                        // alert(ch);
                        // console.log(ch);
                        content = content + "<tr id='tr" + (i + 1) + "'>" +
                            "<td>" + (i + 1) + "</td>" +
                            "<td>" + ch.bookID + "</td>" +
                            "<td>" + ch.bookName + "</td>" +
                            "<td>" + ch.categoryName + "</td>" +
                            "<td>￥" + ch.price + "</td>" +
                            "<td><img src=" + ch.cover + "></td>" +
                            "<td><a href='${pageContext.request.contextPath}/UpdateBookServlet?bookId='" + ch.bookID + ">修改</a>" +
                            "<a href='${pageContext.request.contextPath}/DeleteBookServlet?bookId='" + ch.bookID + ">删除</a></td></tr>";
                    }
                    $("#cont-Search").html(content);//设置<tbody>中的内容
                }
            }
        })
    })
</script>

</html>