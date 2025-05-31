
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程列表</title>
</head>
<body>
<h1>课程列表</h1>

<form action="/course/TeacherCourseList" method="get">
    <input type="text" name="keyword" value="${keyword}" placeholder="搜索课程">
    <input type="text" name="semester" value="${semester}" placeholder="学期">
    <button type="submit">搜索</button>
</form>

<c:if test="${not empty courses}">
    <table border="1">
        <tr>
            <th>课程ID</th>
            <th>课程名称</th>
            <th>教师</th>
            <th>学期</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.courseId}</td>
                <td><a href="/course/detail/${course.courseId}">${course.courseName}</a></td>
                <td>${course.teacherId}</td>
                <td>${course.semester}</td>
                <td>
                    <a href="/course/update/${course.courseId}">编辑</a>
                    <a href="/course/delete/${course.courseId}" onclick="return confirm('确定删除吗？')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="/course/add">添加新课程</a>
<a href="/teacher/my-courses">查看我的课程</a>
</body>
</html>