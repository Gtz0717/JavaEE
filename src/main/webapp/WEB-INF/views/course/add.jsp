<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加课程</title>
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
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #2c3e50;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            transition: border 0.3s;
        }
        input[type="text"]:focus, textarea:focus {
            border-color: #3498db;
            outline: none;
            box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
        }
        textarea {
            min-height: 120px;
            resize: vertical;
        }
        .btn {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 25px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
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
        .action-links {
            margin-top: 25px;
        }
        .action-links a {
            display: inline-block;
            margin-right: 15px;
            color: #3498db;
            text-decoration: none;
        }
        .action-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>添加新课程</h1>

    <form action="${pageContext.request.contextPath}/course/addCourse" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="form-group">
            <label for="courseName">课程名称:</label>
            <input type="text" id="courseName" name="courseName" required>
        </div>

        <div class="form-group">
            <label for="semester">学期:</label>
            <input type="text" id="semester" name="semester" required>
        </div>

        <div class="form-group">
            <label for="description">课程描述:</label>
            <textarea id="description" name="description"></textarea>
        </div>

        <button type="submit" class="btn">提交</button>
    </form>

    <div class="action-links">
        <a href="${pageContext.request.contextPath}/course/list">返回课程列表</a>
    </div>
</div>
</body>
</html>