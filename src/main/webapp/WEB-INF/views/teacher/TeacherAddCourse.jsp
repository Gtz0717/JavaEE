<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加课程</title>
    <style>
        /* 全局样式 */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333;
            background-color: #f5f8fa;
            margin: 0;
            padding: 0;
        }

        /* 容器样式 */
        .container {
            max-width: 1100px;
            margin: 30px auto;
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            /* 新增样式：设置固定高度并启用纵向滚动 */
            height: 800px;           /* 可根据需求调整高度 */
            overflow-y: auto;        /* 内容超出时显示纵向滚动条 */
            overflow-x: hidden;      /* 隐藏横向滚动条（可选） */
        }
        /* 标题样式 */
        h1 {
            color: #2c3e50;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
            margin-top: 0;
            margin-bottom: 25px;
        }

        /* 表单样式 */
        .form-container {
            margin-top: 20px;
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

        input[type="text"],
        input[type="number"],
        select,
        textarea {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        select:focus,
        textarea:focus {
            border-color: #3498db;
            outline: none;
            box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
        }

        textarea {
            min-height: 100px;
            resize: vertical;
        }

        select {
            height: 40px;
            background-color: white;
        }

        /* 按钮样式 */
        .button-group {
            margin-top: 30px;
            display: flex;
            gap: 15px;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: all 0.3s;
            text-decoration: none;
            display: inline-block;
            text-align: center;
        }

        .btn-primary {
            background-color: #3498db;
            color: white;
        }

        .btn-primary:hover {
            background-color: #2980b9;
        }

        .btn-secondary {
            background-color: #95a5a6;
            color: white;
        }

        .btn-secondary:hover {
            background-color: #7f8c8d;
        }

        /* 提示文本样式 */
        .hint-text {
            font-size: 13px;
            color: #7f8c8d;
            margin-top: 5px;
            display: block;
        }

        /* 错误提示样式 */
        .error-message {
            color: #e74c3c;
            font-size: 14px;
            margin-top: 5px;
        }

        /* 响应式设计 */
        @media (max-width: 768px) {
            .container {
                padding: 20px;
                margin: 15px;
                height: 500px;       /* 移动端高度适当减小 */
                overflow-y: auto;
                overflow-x: hidden;
            }

            .button-group {
                flex-direction: column;
            }

            .btn {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>添加新课程</h1>

    <div class="form-container">
        <form:form modelAttribute="course" action="${pageContext.request.contextPath}/course/addCourse" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="form-group">
                <label for="courseCode">课程代码:</label>
                <form:input path="courseCode" id="courseCode" required="true" placeholder="例如：CS101"/>
                <span class="hint-text">请输入课程唯一代码</span>
                <form:errors path="courseCode" cssClass="error-message"/>
            </div>

            <div class="form-group">
                <label for="courseName">课程名称:</label>
                <form:input path="courseName" id="courseName" required="true" placeholder="例如：计算机科学导论"/>
                <form:errors path="courseName" cssClass="error-message"/>
            </div>

            <div class="form-group">
                <label for="credit">学分:</label>
                <form:input path="credit" id="credit" type="number" step="0.5" min="0.5" max="10" required="true" placeholder="0.5-10之间，支持0.5增量"/>
                <form:errors path="credit" cssClass="error-message"/>
            </div>

            <div class="form-group">
                <label for="semester">学期:</label>
                <form:input path="semester" id="semester" required="true" placeholder="例如：2023-2024学年第一学期"/>
                <form:errors path="semester" cssClass="error-message"/>
            </div>

            <div class="form-group">
                <label for="courseType">课程类型:</label>
                <form:select path="courseType" id="courseType" required="true">
                    <form:option value="" disabled="true" selected="true">-- 请选择课程类型 --</form:option>
                    <form:option value="必修">必修</form:option>
                    <form:option value="选修">选修</form:option>
                </form:select>
                <form:errors path="courseType" cssClass="error-message"/>
            </div>

            <div class="form-group">
                <label for="capacity">容量:</label>
                <form:input path="capacity" id="capacity" type="number" min="1" required="true" placeholder="最小为1"/>
                <span class="hint-text">该课程最多可容纳的学生人数</span>
                <form:errors path="capacity" cssClass="error-message"/>
            </div>

            <div class="form-group">
                <label for="classTime">上课时间(JSON格式):</label>
                <form:input path="classTime" id="classTime" required="true" placeholder='例如：[{"day":"周一","time":"1-2节","classroom":"A101"}]'/>
                <span class="hint-text">请按指定JSON格式填写上课时间安排</span>
                <form:errors path="classTime" cssClass="error-message"/>
            </div>

            <div class="form-group">
                <label for="classroom">教室:</label>
                <form:input path="classroom" id="classroom" required="true" placeholder="例如：A101"/>
                <form:errors path="classroom" cssClass="error-message"/>
            </div>

            <div class="form-group">
                <label for="description">课程描述:</label>
                <form:textarea path="description" id="description" placeholder="请输入课程简介、教学目标等内容"/>
                <form:errors path="description" cssClass="error-message"/>
            </div>

            <div class="button-group">
                <button type="submit" class="btn btn-primary">提交</button>
                <a href="${pageContext.request.contextPath}/course/list" class="btn btn-secondary">取消</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>