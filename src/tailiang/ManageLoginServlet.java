package tailiang;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
public class ManageLoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        /*to get the username and password*/
        String userName=request.getParameter("userName");
        String userPassword=request.getParameter("userPassword");
        //to keep the username through the sesssion
        HttpSession session=request.getSession();
        session.setAttribute("UserName", userName);//it is to set the welcome page of teacher
        //let the mBean to check the usename and pwd from Mysql database
        MysqlJavaBean mBean=new MysqlJavaBean();
        int res=mBean.loginCheck(userName, userPassword);
        switch (res) {
            case 1://admin
                response.sendRedirect("admin.html");
                break;
            case 2://teacher
                response.sendRedirect("teacher.jsp");
                break;
            default://other is error
                PrintWriter out = response.getWriter();
                out.flush();
                out.println("<script>");
                out.println("alert('username and pwd are not match or may be you are not sign in');");
                out.println("history.back();");
                out.println("</script>");
                break;
        }
    }
}
