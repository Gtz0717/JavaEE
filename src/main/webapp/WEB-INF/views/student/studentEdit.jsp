<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Student Information</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      line-height: 1.6;
    }
    .container {
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 5px;
    }
    h1 {
      color: #333;
      text-align: center;
    }
    .form-group {
      margin-bottom: 15px;
    }
    label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }
    input[type="text"], input[type="number"], input[type="email"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
    }
    .btn {
      background-color: #4CAF50;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      margin-right: 10px;
    }
    .btn:hover {
      background-color: #45a049;
    }
    .error {
      color: red;
      font-size: 0.9em;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Edit Student Information</h1>

  <form id="studentForm" action="${pageContext.request.contextPath}/student/update" method="post">
    <input type="hidden" name="student_Id" value="${student.student_Id}">

    <div class="form-group">
      <label for="student_no">Student Number:</label>
      <input type="text" id="student_no" name="student_no" value="${student.student_no}" required>
    </div>

    <div class="form-group">
      <label for="gender">Gender:</label>
      <input type="text" id="gender" name="gender" value="${student.gender}" required>
    </div>

    <div class="form-group">
      <label for="birth_date">Birth Date (YYYY-MM-DD):</label>
      <input type="text" id="birth_date" name="birth_date"
             value="<fmt:formatDate value="${student.birth_date}" pattern="yyyy-MM-dd"/>" required>
    </div>

    <div class="form-group">
      <label for="major">Major:</label>
      <input type="text" id="major" name="major" value="${student.major}" required>
    </div>

    <div class="form-group">
      <label for="grade">Grade:</label>
      <input type="text" id="grade" name="grade" value="${student.grade}" required>
    </div>

    <div class="form-group">
      <label for="college">College:</label>
      <input type="text" id="college" name="college" value="${student.college}" required>
    </div>

    <div class="form-group">
      <label for="class_name">Class Name:</label>
      <input type="text" id="class_name" name="class_name" value="${student.class_name}" required>
    </div>

    <div class="form-group">
      <button type="submit" class="btn">Save Changes</button>
      <a href="${pageContext.request.contextPath}/student/home" class="btn">Cancel</a>
    </div>
  </form>
</div>

<script>
  // Simple client-side validation
  document.getElementById('studentForm').addEventListener('submit', function(e) {
    // 你可以在这里添加其他验证逻辑
  });
</script>
</body>
</html>