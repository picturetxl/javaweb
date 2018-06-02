<%@ page import="tailiang.Teacher" %>
<%@ page import="java.util.ArrayList" %>
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
    <link rel="stylesheet" href="css/headerandfooter.css">
    <link rel="stylesheet" href="css/admin.css">
    <base href="<%=basePath%>">
    <title>Admin</title>
</head>
<body>
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
            <%
                ArrayList<Teacher> aList=(ArrayList)session.getAttribute("teacherList");
            %>
            <section>
                <article class="information">
                    <table>
                        <tr>
                            <th>教师工号</th>
                            <th>教师姓名</th>
                            <th>教师密码</th>
                            <th>教师手机</th>
                        </tr>
                        <%
                            for(int i=0;i<aList.size();i++)
                            {
                                Teacher teacher=aList.get(i);
                        %>
                        <tr>
                            <td><%=teacher.getTech_id()%></td>
                            <td><%=teacher.getTech_name()%></td>
                            <td><%=teacher.getTech_password()%></td>
                            <td><%=teacher.getTech_phone()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
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