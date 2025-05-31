<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>500 - 服务器错误</title>
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
    .error-details {
      margin: 20px 0;
      padding: 15px;
      background-color: #f8f9fa;
      border: 1px solid #ddd;
      border-radius: 4px;
      text-align: left;
      font-family: monospace;
    }
    .back-link {
      margin-top: 20px;
    }
  </style>
</head>
<body>
<div class="error-container">
  <div class="error-code">500</div>
  <div class="error-message">
    ${not empty error ? error : '系统繁忙，请稍后再试'}
  </div>

  <!-- Only show error details in development environment -->
  <c:if test="${not empty exception and pageContext.request.serverName == 'localhost'}">
    <div class="error-details">
      <strong>错误详情:</strong><br>
        ${exception.message}
    </div>
  </c:if>

  <div class="back-link">
    <a href="/">返回首页</a> |
    <a href="javascript:history.back()">返回上一页</a>
  </div>
</div>
</body>
</html>