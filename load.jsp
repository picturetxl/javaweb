<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/arrow.png">
    <link rel="stylesheet" href="css/headerandfooter.css">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <base href="<%=basePath%>">
    <title>Document</title>
    <style>
    .all{
    width: 980px;
    height: 680px;
    /* background: red; */
    margin-top:20px;
    border-radius: 20px 0 20px 0;
    box-shadow: 0 2px 5px black;
    position: relative;
    z-index:-1;
}
    .download{
    background:pink;
    width: 250px;
    height: auto;
    position: absolute;
    top: 20px;
    left: 40px;
    border-radius: 20px 20px 20px 20px;
    box-shadow: 1px 3px 5px rgb(165, 161, 161);
}
.download ul{
    list-style-type: none;
}
.download a{
    text-decoration: none;
    color: black;
    margin-left: 5px;
}
.download a:hover{
    color:white;
    font-size: 20px;
}
.download span{
    font-size: 20px;
    padding-left: 5px;
}
.upload{
    position: relative;
    top: 20px;
    left: 320px;
    background: rgb(142, 203, 238);
    border-radius: 20px 20px 20px 20px;
    box-shadow: 1px 3px 5px rgb(165, 161, 161);
    width: 400px;
    height: 80px;
}
.upload span{
    font-size: 20px;
    padding-left: 5px;
}
.upload form{
    /* background: blue; */
    margin-left: 5px;
}
.upload input[type="file"]{
    border: none;
    color: olive;
    font-size: 10px;
    /* background: mediumvioletred; */
    padding-left: 5px;
    border-radius: 20px 20px 20px 20px;
    cursor: pointer;
}
.upload input[type="submit"]{
    border: none;
    font-size: 20px;
    border-radius: 20px 20px 20px 20px;
    padding: 3px;
    box-shadow: 1px 2px 5px black;
    cursor: pointer;
}
.upload input[type="submit"]:hover{
    color: orange;
}

</style>
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
                    <li class="p1"><a href="EquipmentServlet">设备总览</a>
                        <ul>
                            <li><a href="addequip.html">添加设备</a></li>
                             <li><a href="SupplierServlet">供货商信息</a></li>
                        </ul>
                    </li>
                    <li class="p2"><a href="QueryMessageServlet">查看留言板</a></li>
                    <li class="p3"><a href="TeacherInfoServlet">教师信息</a></li>
                    <li class="p4"><a href="SelectBuyServlet">采购信息</a></li>
                    <li class="p5"><a href="load.jsp">上传文件</a>
                        <!-- <ul>
                            <li><a href="">采购项目</a></li>
                            <li><a href="">竞价项目</a></li>
                        </ul> -->
                    </li>
                </ul>
            </nav>
            <form class="search" action="#" method="post">
                <label for="user_name">search</label>
                <input type="text" id="user_name" name="user_name" placeholder="search" />
            </form>
        </header>
        <div class="all">
            <article class="download">
                <span>已经存在以下文件</span>
                <ul>
                <%
                    try {
                        File file =new File("D:\\webpage\\download");
                        File[] array=file.listFiles();
                        String filename=null;
                        String filesplit[]=null;
                        for (File file1 : array) {
                            if(!file1.isDirectory())
                            {
                                filename=file1.toString();
                                filesplit=filename.split("\\\\");
                                for(String string:filesplit)
                                {
                                    if(string.endsWith(".zip"))
                                        filename=string;
                                }
                            }
                    %>
                        <li><a href="DownLoadServlet?filename=<%=filename%>"> <%=filename%></a></li>
                    <%
                            }

                        } catch (SecurityException e) {

                        }finally{

                        }
                    %>
                </ul>
            </article>
            <aside>
                <article class="upload">
                    <div id="formbutton">
                        <span>请选择zip格式的文件</span>
                        <form action="MultipleUploadsServlet" enctype="multipart/form-data" method="POST">
                            <input type="file" name="filename" multiple>
                            <input type="submit" value="upload">
                        </form>
                    </div>
                </article>
            </aside>
        </div>
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