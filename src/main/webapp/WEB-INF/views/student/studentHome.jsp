<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生主页</title>
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

        /* 信息卡片样式 */
        .info-card {
            background-color: #f1f9ff;
            border-left: 4px solid #3498db;
            padding: 20px;
            margin: 20px 0;
            border-radius: 0 4px 4px 0;
        }

        .info-card h3 {
            color: #2c3e50;
            margin-top: 0;
        }

        .info-card p {
            margin: 5px 0;
        }

        /* 功能区块样式 */
        .feature-blocks {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            margin: 30px 0;
        }

        .feature-block {
            flex: 1 1 300px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 6px;
            padding: 20px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.05);
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .feature-block:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }

        .feature-block h3 {
            color: #3498db;
            margin-top: 0;
        }

        .feature-block p {
            color: #6c757d;
            margin-bottom: 20px;
        }

        /* 响应式设计 */
        @media (max-width: 768px) {
            .container {
                padding: 15px;
            }

            .feature-blocks {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>学生管理系统</h1>

    <div class="info-card">
        <h3>${welcomeMsg}</h3>
        <p>用户ID: ${currentUser.userId}</p>
        <p>用户角色: ${currentUser.role}</p>
    </div>

    <div class="feature-blocks">
        <div class="feature-block">
            <h3>个人信息</h3>
            <p>查看和管理你的个人资料</p>
            <a href="${pageContext.request.contextPath}/student/detail" class="nav-links a">查看详情</a>
        </div>

        <div class="feature-block">
            <h3>课程管理</h3>
            <p>查看和选择课程</p>
            <a href="${pageContext.request.contextPath}/course/list" class="nav-links a">课程列表</a>
        </div>

        <div class="feature-block">
            <h3>成绩查询</h3>
            <p>查看你的课程成绩</p>
            <a href="${pageContext.request.contextPath}/student/grade" class="nav-links a">成绩查询</a>
        </div>
    </div>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/student/edit">编辑个人信息</a>
        <a href="${pageContext.request.contextPath}/logout">退出登录</a>
    </div>
</div>
</body>
</html>