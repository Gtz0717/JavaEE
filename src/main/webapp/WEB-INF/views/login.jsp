<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>系统登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background: #fff;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 350px;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
        .error-message {
            color: #f44336;
            text-align: center;
            margin-bottom: 15px;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
            color: #666;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>系统登录</h2>

    <c:if test="${not empty error}">
        <div class="error-message">
                ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="username">用户名</label>
            <input type="text" id="username" name="username" required placeholder="请输入用户名">
        </div>

        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" id="password" name="password" required placeholder="请输入密码">
        </div>

        <button type="submit">登录</button>
    </form>

    <div class="footer">
        <p>没有账号？请联系管理员</p>
    </div>
</div>
</body>
</html>