<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/arrow.png">
    <link rel="stylesheet" href="css/teacherheader.css">
    <link rel="stylesheet" href="css/messageadd.css">
    <title>Message</title>
</head>
<body>
<%
    String userName=(String)session.getAttribute("UserName");
%>
    <div id="wrapper">
        <header>
            <section id="title">
                <h1>Teacher</h1>
                <h5>welcome <%=userName%>teacher</h5>
            </section>
             <nav class="menu">
            <ul>
                <li class="p1"><a href="#">常见操作</a>
                    <ul>
                        <li><a href="BorrowServlet?username=<%=userName %>" >已借情况</a></li>
                        <li><a href="download.jsp">资料下载</a></li>
                    </ul>
                </li>
                <li class="p2"><a href="ReminderServlet">可借设备</a></li>
                <li class="p3"><a href="message.jsp">留言板块</a></li>
                <li class="p4"><a href="">校园官网</a></li>
                <li class="p5"><a href="">项目公告</a>
                    <ul>
                        <li><a href="">采购项目</a></li>
                        <li><a href="">竞价项目</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        </header>
        <section id="feature_area">
            <article id="message">
                <form action="MessageServlet">
                    <span>标题*</span>
                        <input type="text" placeholder="请输入标题" name="title" id="title" required>
                    <br>
                   <span>留言*</span>
                        <textarea name="message" id="message" cols="20" rows="5"></textarea>
                    <br>
                    <button type="submit">提交</button>
                </form>
            </article>
        </section>
    </div>
</body>
</html>