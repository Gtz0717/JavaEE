<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>我的课程</title>
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

    /* 链接样式 */
    a {
      color: #3498db;
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
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
  <h1>我的课程</h1>

  <div class="nav-links">
    <a href="${pageContext.request.contextPath}/course/add">添加新课程</a>
    <a href="${pageContext.request.contextPath}/course/list">查看所有课程</a>
  </div>

  <c:choose>
    <c:when test="${not empty courses}">
      <table>
        <thead>
        <tr>
          <th>课程ID</th>
          <th>课程名称</th>
          <th>学期</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${courses}" var="course">
          <tr>
            <td>${course.courseId}</td>
            <td><a href="/course/detail/${course.courseId}">${course.courseName}</a></td>
            <td>${course.semester}</td>
            <td>
              <a href="${pageContext.request.contextPath}/course/detail/${course.courseId}" class="action-btn detail-btn">详情</a>
              <a href="${pageContext.request.contextPath}/course/update/${course.courseId}" class="action-btn edit-btn">编辑</a>
              <a href="${pageContext.request.contextPath}/course/delete/${course.courseId}" class="action-btn delete-btn" onclick="return confirm('确定删除吗？')">删除</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <div class="empty-message">
        您目前没有教授任何课程
      </div>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>