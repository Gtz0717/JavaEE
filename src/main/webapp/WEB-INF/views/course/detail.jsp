<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>课程详情</title>
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
      max-width: 800px;
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
    .course-info {
      margin: 25px 0;
    }
    .info-item {
      margin-bottom: 15px;
      padding-bottom: 15px;
      border-bottom: 1px solid #eee;
    }
    .info-item:last-child {
      border-bottom: none;
    }
    .info-label {
      font-weight: 600;
      color: #2c3e50;
      display: inline-block;
      width: 100px;
    }
    .info-value {
      color: #555;
    }
    .action-links {
      margin-top: 30px;
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
  </style>
</head>
<body>
<div class="container">
  <h1>${course.courseName}</h1>

  <div class="course-info">
    <div class="info-item">
      <span class="info-label">课程ID:</span>
      <span class="info-value">${course.courseId}</span>
    </div>

    <div class="info-item">
      <span class="info-label">教师ID:</span>
      <span class="info-value">${course.teacherId}</span>
    </div>

    <div class="info-item">
      <span class="info-label">学期:</span>
      <span class="info-value">${course.semester}</span>
    </div>

    <div class="info-item">
      <span class="info-label">描述:</span>
      <span class="info-value">${course.description}</span>
    </div>
  </div>

  <div class="action-links">
    <a href="${pageContext.request.contextPath}/course/list" class="btn">返回课程列表</a>
    <a href="${pageContext.request.contextPath}/course/update/${course.courseId}" class="btn">编辑课程</a>
  </div>
</div>
</body>
</html>