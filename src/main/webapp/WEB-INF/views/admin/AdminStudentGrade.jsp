<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>成绩管理系统</title>
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
        .filter-form {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #f5f5f5;
            border-radius: 5px;
        }
        .filter-form .form-group {
            margin-bottom: 10px;
        }
        .no-scores {
            margin-top: 20px;
            color: #666;
        }
    </style>
</head>
<body>
<h2>成绩管理系统</h2>

<div class="filter-form">
    <form method="get" action="${pageContext.request.contextPath}/score/admin">
        <div class="form-group">
            <label for="semester">学期：</label>
            <select id="semester" name="semesterId">
                <option value="">全部学期</option>
                <c:forEach items="${semesters}" var="semester">
                    <option value="${semester.semesterId}"
                        ${semester.semesterId == selectedSemesterId ? 'selected' : ''}>
                            ${semester.semesterName}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="student">学生：</label>
            <input type="text" id="student" name="studentId" placeholder="学生ID" value="${selectedStudentId}">
        </div>

        <div class="form-group">
            <label for="course">课程：</label>
            <input type="text" id="course" name="courseId" placeholder="课程ID" value="${selectedCourseId}">
        </div>

        <button type="submit">筛选</button>
    </form>
</div>

<c:choose>
    <c:when test="${not empty scores}">
        <table class="score-table">
            <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>课程代码</th>
                <th>课程名称</th>
                <th>学期</th>
                <th>成绩</th>
                <th>学分</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${scores}" var="score">
                <tr>
                    <td>${score.student.studentNo}</td>
                    <td>${score.student.realName}</td>
                    <td>${score.course.courseCode}</td>
                    <td>${score.course.courseName}</td>
                    <td>${score.course.semester}</td>
                    <td>
                        <c:choose>
                            <c:when test="${score.score != null}">
                                ${score.score}
                            </c:when>
                            <c:otherwise>
                                <span style="color: #999;">未录入</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${score.course.credit}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/score/update" class="action-form">
                            <input type="hidden" name="selectionId" value="${score.selectionId}">
                            <input type="number" name="score" value="${score.score != null ? score.score : ''}"
                                   min="0" max="100" step="0.01" style="width: 60px;">
                            <button type="submit">更新</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <div class="no-scores">
            没有找到符合条件的成绩记录
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>