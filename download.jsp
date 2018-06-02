<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="shortcut icon" href="images/arrow.png">
    <link rel="stylesheet" href="css/teacherheader.css">
    <link rel="stylesheet" href="css/down.css">
    <base href="<%=basePath%>">
    <title>DownLoad</title>
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
            <article class="download">
                <ul>
                <%
                    try
                    {
                        File file =new File("D:\\webpage\\download");
                        File[] array=file.listFiles();
                        String filename=null;
                        String filesplit[]=null;
                        for (File file1 : array)
                        {
                            if(!file1.isDirectory())
                            {
                                filename=file1.toString();
                                filesplit=filename.split("\\\\");
                                for(String string:filesplit)
                                {
                                    if(string.endsWith(".zip")){
                                        filename=string;
                                    }
                                }
                %>
                        <li><a href="DownLoadServlet?filename=<%=filename%>"><%=filename%></a></li>
                    <%
                                }
                            }
                        } catch (SecurityException e) {

                        }finally{

                        }
                    %>
                </ul>
            </article>
            <aside>
            </aside>
        </section>
    </div>
</body>
</html>