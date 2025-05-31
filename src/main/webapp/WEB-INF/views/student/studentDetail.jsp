<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>学生详情</title>
  <style>
    /* 全局样式 */
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      line-height: 1.6;
      color: #333;
      background-color: #f8f9fa;
      margin: 0;
      padding: 20px;
    }

    /* 容器样式 */
    .container {
      max-width: 1200px;
      margin: 0 auto;
      background: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    /* 标题样式 */
    h1 {
      color: #2c3e50;
      border-bottom: 2px solid #3498db;
      padding-bottom: 10px;
      margin-top: 0;
    }

    /* 导航链接样式 */
    .nav-links {
      margin: 20px 0;
    }

    .nav-links a {
      display: inline-block;
      padding: 8px 16px;
      margin-right: 10px;
      background-color: #3498db;
      color: white;
      text-decoration: none;
      border-radius: 4px;
      transition: background-color 0.3s;
    }

    .nav-links a:hover {
      background-color: #2980b9;
    }

    /* 表格样式 */
    table {
      width: 100%;
      border-collapse: collapse;
      margin: 25px 0;
      font-size: 0.9em;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }

    table thead tr {
      background-color: #3498db;
      color: #ffffff;
      text-align: left;
    }

    table th, table td {
      padding: 12px 15px;
    }

    table tbody tr {
      border-bottom: 1px solid #dddddd;
    }

    table tbody tr:nth-of-type(even) {
      background-color: #f3f3f3;
    }

    table tbody tr:last-of-type {
      border-bottom: 2px solid #3498db;
    }

    table tbody tr:hover {
      background-color: #f1f9ff;
    }

    /* 操作按钮样式 */
    .action-btn {
      display: inline-block;
      padding: 5px 10px;
      margin: 0 5px;
      border-radius: 3px;
      font-size: 0.85em;
    }

    .detail-btn {
      background-color: #3498db;
      color: white;
    }

    .edit-btn {
      background-color: #2ecc71;
      color: white;
    }

    .delete-btn {
      background-color: #e74c3c;
      color: white;
    }

    .detail-btn:hover {
      background-color: #2980b9;
    }

    .edit-btn:hover {
      background-color: #27ae60;
    }

    .delete-btn:hover {
      background-color: #c0392b;
    }

    /* 空状态提示 */
    .empty-message {
      padding: 20px;
      text-align: center;
      color: #6c757d;
      background-color: #f8f9fa;
      border-radius: 4px;
      margin: 20px 0;
    }

    /* 响应式设计 */
    @media (max-width: 768px) {
      .container {
        padding: 15px;
      }

      table {
        display: block;
        overflow-x: auto;
      }
    }
  </style>
</head>
<body>
<div class="container">
  <h1>学生详细信息</h1>

  <div class="nav-links">
    <a href="${pageContext.request.contextPath}/student/home">返回主页</a>
    <a href="${pageContext.request.contextPath}/student/edit">编辑信息</a>
  </div>

  <table>
    <thead>
    <tr>
      <th>字段</th>
      <th>值</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>学号</td>
      <td>${student.student_no}</td> <!-- 修正为student_no -->
    </tr>
    <tr>
      <td>姓名</td>
      <td>${student.user.realName}</td>
    </tr>
    <tr>
      <td>用户名</td>
      <td>${student.user.username}</td>
    </tr>
    <tr>
      <td>性别</td>
      <td>
        <c:choose>
          <c:when test="${student.gender == 'male'}">男</c:when>
          <c:when test="${student.gender == 'female'}">女</c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
    </tr>
    <tr>
      <td>出生日期</td>
      <td>${student.birth_date}</td> <!-- 修正为birth_date -->
    </tr>
    <tr>
      <td>专业</td>
      <td>${student.major}</td>
    </tr>
    <tr>
      <td>班级</td>
      <td>${student.class_name}</td> <!-- 修正为class_name -->
    </tr>
    <tr>
      <td>学院</td>
      <td>${student.college}</td>
    </tr>
    <tr>
      <td>邮箱</td>
      <td>${student.user.email}</td>
    </tr>
    <tr>
      <td>电话</td>
      <td>${student.user.phone}</td>
    </tr>
    </tbody>
</div>
</body>
</html>