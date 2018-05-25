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
    <link rel="stylesheet" href="css/headerandfooter.css">
    <link rel="stylesheet" href="css/download.css">
    <base href="<%=basePath%>">
    <title>DownLoad</title>
</head>
<body>
    <div id="wrapper">
        <header>
            <section id="title">
                <h1>Manage</h1>
                <h2>Equipment management system</h2>
            </section>
            <nav class="menu">
                <ul>
                    <li class="p1"><a href="">设备指南</a>
                        <ul>
                            <li><a href="">政策法规</a></li>
                            <li><a href="">实务知识</a></li>
                            <li><a href="">资料下载</a></li>
                            <li><a href="">采购问答</a></li>
                        </ul>
                    </li>
                    <li class="p2"><a href="">本周安排</a></li>
                    <li class="p3"><a href="">采购进度</a></li>
                    <li class="p4"><a href="">校园官网</a></li>
                    <li class="p5"><a href="">项目公告</a>
                        <ul>
                            <li><a href="">采购项目</a></li>
                            <li><a href="">竞价项目</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <form class="search" action="#" method="post">
                <label for="user_name">search</label>
                <input type="text" id="user_name" name="user_name" placeholder="search" />
            </form>
        </header>
        <section>
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
        <footer>
            <ul>
                <li><a href="">first</a></li>
                <li><a href="">second</a></li>
                <li><a href="">third</a></li>
                <li><a href="">forth</a></li>
                <li><a href="">fifth</a></li>
                <li><a href="">sixth</a></li>
            </ul>
        </footer>
    </div>
</body>
</html>