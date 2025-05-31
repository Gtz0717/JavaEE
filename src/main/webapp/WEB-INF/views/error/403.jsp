<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>403 - 权限不足</title>
    <style>
        .error-container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            text-align: center;
            font-family: Arial, sans-serif;
        }
        .error-code {
            font-size: 72px;
            color: #dc3545;
        }
        .error-message {
            font-size: 24px;
            margin: 20px 0;
        }
        .back-link {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="error-code">403</div>
    <div class="error-message">
        ${not empty errorMessage ? errorMessage : '您没有访问此页面的权限'}
    </div>
    <div class="back-link">
        <a href="/">返回首页</a> |
        <a href="javascript:history.back()">返回上一页</a>
    </div>
</div>
</body>
</html>