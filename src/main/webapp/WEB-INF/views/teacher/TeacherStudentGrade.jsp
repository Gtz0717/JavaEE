<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生成绩管理</title>
    <style>
        .score-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .score-table th, .score-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        .score-table th {
            background-color: #f2f2f2;
        }
        .course-selector {
            margin-bottom: 20px;
        }
        .no-scores {
            margin-top: 20px;
            color: #666;
        }
        .action-form {
            display: inline;
        }
    </style>
</head>
<body>
<h2>学生成绩管理</h2>

<div class="course-selector">
    <form method="get" action="${pageContext.request.contextPath}/score/teacher">
        <label for="course">选择课程：</label>
        <select id="course" name="courseId" onchange="this.form.submit()">
            <option value="">-- 请选择课程 --</option>
            <c:forEach items="${courses}" var="course">
                <option value="${course.courseId}"
                    ${course.courseId == selectedCourseId ? 'selected' : ''}>
                        ${course.courseName} (${course.courseCode})
                </option>
            </c:forEach>
        </select>
    </form>
</div>

<c:if test="${not empty course}">
    <h3>${course.courseName} (${course.courseCode}) - 成绩录入</h3>

    <c:choose>
        <c:when test="${not empty scores}">
            <form method="post" action="${pageContext.request.contextPath}/score/update">
                <input type="hidden" name="courseId" value="${course.courseId}">
                <table class="score-table">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>成绩</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${scores}" var="score">
                        <tr>
                            <td>${score.student.studentNo}</td>
                            <td>${score.student.realName}</td>
                            <td>
                                <input type="number" name="score_${score.selectionId}"
                                       value="${score.score != null ? score.score : ''}"
                                       min="0" max="100" step="0.01">
                            </td>
                            <td>
                                <button type="submit" name="selectionId" value="${score.selectionId}">
                                    更新
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>

            <div style="margin-top: 20px;">
                <form method="post" action="${pageContext.request.contextPath}/score/export" class="action-form">
                    <input type="hidden" name="courseId" value="${course.courseId}">
                    <button type="submit">导出成绩</button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div class="no-scores">
                该课程暂无学生选课记录
            </div>
        </c:otherwise>
    </c:choose>
</c:if>
</body>
</html>