package tailiang;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class DeleteEquipmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MysqlJavaBean bean=new MysqlJavaBean();
        String NO=request.getParameter("NO");
        PrintWriter out = response.getWriter();//通过servlet的doget方法获取response对象，通过getWriter方法获取PrintWriter对象
        int re=bean.deleteEquipment(NO);
        switch (re) {
            case 1:
                out.flush();//清空缓存
                out.println("<script>");//输出script标签
                out.println("alert('successfully do it');");//js语句：输出alert语句
                out.println("history.back();");//js语句：输出网页回退语句
                out.println("</script>");//输出script结尾标签
                break;
            case 2:
                out.flush();//清空缓存
                out.println("<script>");//输出script标签
                out.println("alert('delete option failed');");//js语句：输出alert语句
                out.println("history.back();");//js语句：输出网页回退语句
                out.println("</script>");//输出script结尾标签
                break;
            case 3:
                out.flush();//清空缓存
                out.println("<script>");//输出script标签
                out.println("alert('this equipment is rented');");//js语句：输出alert语句
                out.println("history.back();");//js语句：输出网页回退语句
                out.println("</script>");//输出script结尾标签
                break;
            default:
                out.flush();//清空缓存
                out.println("<script>");//输出script标签
                out.println("alert('error');");//js语句：输出alert语句
                out.println("history.back();");//js语句：输出网页回退语句
                out.println("</script>");//输出script结尾标签
                break;
        }
    }
}
