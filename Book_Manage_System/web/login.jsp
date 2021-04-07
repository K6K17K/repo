<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/login.css">
    <script type="text/javascript">
        function changeImg() {
            var codeImg = document.getElementById("codeImg");
            codeImg.src = "${pageContext.request.contextPath}/KaptchaServlet?time=" + new Date().getTime()
        }
    </script>
</head>
<body>
<div class="login">
    <div class="header">
        <h1>
            <a href="${pageContext.request.contextPath}/LoginServlet">登录</a>
            <p style="color: red;font-weight: bold">${msg}</p>
        </h1>
        <button></button>
    </div>
    <form action="${pageContext.request.contextPath}/LoginServlet"
          method="post">
        <div class="name">
            <input type="text" id="name" name="username">
            <p></p>
        </div>
        <div class="pwd">
            <input type="password" id="pwd" name="password">
            <p></p>
        </div>
        <div class="code">
            <input type="text" id="code" name="verifyCode" style="width: 150px">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <img id="codeImg" onclick="changeImg()"
                 src="${pageContext.request.contextPath}/KaptchaServlet"
                 style="width: 150px;height: 42px;vertical-align: middle;">
            <p></p>
        </div>
        <div class="btn-red">
            <input type="submit" value="登录" id="login-btn">
        </div>
    </form>
</div>
</body>
</html>