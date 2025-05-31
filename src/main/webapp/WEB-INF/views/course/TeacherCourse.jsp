<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>我的课程</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      line-height: 1.6;
      color: #333;
      background-color: #f5f5f5;
      margin: 0;
      padding: 20px;
    }
    .container {
      max-width: 1000px;
      margin: 30px auto;
      background: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
    }
    h1 {
      color: #2c3e50;
      border-bottom: 2px solid #3498db;
      padding-bottom: 10px;
      margin-top: 0;
    }
    .action-buttons {
      margin: 20px 0;
    }
    .btn {
      display: inline-block;
      padding: 10px 20px;
      background-color: #3498db;
      color: white;
      text-decoration: none;
      border-radius: 4px;
      margin-right: 10px;
      transition: background-color 0.3s;
    }
    .btn:hover {
      background-color: #2980b9;
    }
    .btn-secondary {
      background-color: #95a5a6;
    }
    .btn-secondary:hover {
      background-color: #7f8c8d;
    }
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
    .action-link {
      display: inline-block;
      padding: 5px 10px;
      margin-right: 5px;
      border-radius: 3px;
      text-decoration: none;
      font-size: 0.8em;
    }
    .edit-link {
      background-color: #2ecc71;
      color: white;
    }
    .delete-link {
      background-color: #e74c3c;
      color: white;
    }
    .edit-link:hover {
      background-color: #27ae60;
    }
    .delete-link:hover {
      background-color: #c0392b;
    }
    .no-courses {
      padding: 20px;
      background-color: #f8f9fa;
      border-radius: 4px;
      text-align: center;
      color: #6c757d;
    }
    .teacher-info {
      background-color: #e8f4fc;
      padding: 15px;
      border-radius: 4px;
      margin-bottom: 20px;
      border-left: 4px solid #3498db;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>我的课程</h1>

  <div class="teacher-info">
    <strong>教师ID:</strong> ${teacherId} | <strong>姓名:</strong> ${teacherName}
  </div>

  <div class="action-buttons">
    <a href="/course/add" class="btn">添加新课程</a>
    <a href="/course/list" class="btn btn-secondary">查看所有课程</a>
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
              <a href="/course/update/${course.courseId}" class="action-link edit-link">编辑</a>
              <a href="/course/delete/${course.courseId}" class="action-link delete-link" onclick="return confirm('确定删除吗？')">删除</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <div class="no-courses">
        您目前没有教授任何课程
      </div>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>