<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>我的成绩</title>
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
    .semester-selector {
      margin-bottom: 20px;
    }
    .no-scores {
      margin-top: 20px;
      color: #666;
    }
  </style>
</head>
<body>
<h2>我的成绩</h2>

<div class="semester-selector">
  <form method="get" action="${pageContext.request.contextPath}/score/student">
    <label for="semester">选择学期：</label>
    <select id="semester" name="semesterId" onchange="this.form.submit()">
      <c:forEach items="${semesters}" var="semester">
        <option value="${semester.semesterId}"
          ${semester.semester_id == selectedSemesterId ? 'selected' : ''}>
            ${semester.semester_name}
        </option>
      </c:forEach>
    </select>
  </form>
</div>

<c:choose>
  <c:when test="${not empty scores}">
    <table class="score-table">
      <thead>
      <tr>
        <th>课程代码</th>
        <th>课程名称</th>
        <th>学分</th>
        <th>成绩</th>
        <th>课程类型</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${scores}" var="score">
        <tr>
          <td>${score.course.courseCode}</td>
          <td>${score.course.courseName}</td>
          <td>${score.course.credit}</td>
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
          <td>${score.course.courseType}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <div style="margin-top: 20px;">
      <strong>GPA：</strong>
      <c:set var="totalCredits" value="0" />
      <c:set var="totalGradePoints" value="0" />

      <c:forEach items="${scores}" var="score">
        <c:if test="${score.score != null}">
          <c:set var="credits" value="${score.course.credit}" />
          <c:set var="gradePoint" value="${score.score >= 60 ? (score.score-50)/10 : 0}" />
          <c:set var="totalCredits" value="${totalCredits + credits}" />
          <c:set var="totalGradePoints" value="${totalGradePoints + gradePoint * credits}" />
        </c:if>
      </c:forEach>

      <c:choose>
        <c:when test="${totalCredits > 0}">
          <fmt:formatNumber value="${totalGradePoints / totalCredits}" pattern="0.00" />
        </c:when>
        <c:otherwise>
          暂无
        </c:otherwise>
      </c:choose>
    </div>
  </c:when>
  <c:otherwise>
    <div class="no-scores">
      当前学期没有成绩记录
    </div>
  </c:otherwise>
</c:choose>
</body>
</html>