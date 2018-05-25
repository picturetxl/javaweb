package tailiang;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddEquipmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  equId=request.getParameter("equId");
        String equName=request.getParameter("equName");
        String equPrice=request.getParameter("equPrice");
        String typeId=request.getParameter("typeId");
        String typeName=request.getParameter("typeName");
        String supId=request.getParameter("supId");
        String supAddr=request.getParameter("supAddr");
        String supMan=request.getParameter("supMan");
        String supTel=request.getParameter("supTel");
        String supName=request.getParameter("supName");
        String buyerId=request.getParameter("buyerId");
        MysqlJavaBean bean=new MysqlJavaBean();
        if(bean.addEquipment(equId, equName, equPrice, typeId, typeName, supId, supAddr, supMan, supTel, supName, buyerId))
        {
            PrintWriter out1 = response.getWriter();//通过servlet的doget方法获取response对象，通过getWriter方法获取PrintWriter对象
            out1.flush();//清空缓存
            out1.println("<script>");//输出script标签
            out1.println("alert('successful do it');");//js语句：输出alert语句
            out1.println("history.back();");//js语句：输出网页回退语句
            out1.println("</script>");//输出script结尾标签
        }
        else
        {
            PrintWriter out1 = response.getWriter();//通过servlet的doget方法获取response对象，通过getWriter方法获取PrintWriter对象
            out1.flush();//清空缓存
            out1.println("<script>");//输出script标签
            out1.println("alert('add option error');");//js语句：输出alert语句
            out1.println("history.back();");//js语句：输出网页回退语句
            out1.println("</script>");//输出script结尾标签
        }

    }
}
