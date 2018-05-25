
package tailiang;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class RegisterCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherid=request.getParameter("teacherid");
        String teachername=request.getParameter("teachername");
        String password=request.getParameter("pwd");
        String password2=request.getParameter("pwd2");
        String phone=request.getParameter("phone");
        MysqlJavaBean bean=new MysqlJavaBean();
        int res=bean.registerCheck(teacherid, teachername, password, password2, phone);
        switch (res) {
            case 1://right register
                response.sendRedirect("index.html");
                break;
            case 2://password is not matched
                PrintWriter out = response.getWriter();//通过servlet的doget方法获取response对象，通过getWriter方法获取PrintWriter对象
                out.flush();
                out.println("<script>");//输出script标签
                out.println("alert('Password inconsistency');");//js语句：输出alert语句
                out.println("history.back();");//js语句：输出网页回退语句
                out.println("</script>");//输出script结尾标签
                break;
            case 3://already is signed
                PrintWriter out1 = response.getWriter();//通过servlet的doget方法获取response对象，通过getWriter方法获取PrintWriter对象
                out1.flush();//清空缓存
                out1.println("<script>");//输出script标签
                out1.println("alert('user is already sign in');");//js语句：输出alert语句
                out1.println("history.back();");//js语句：输出网页回退语句
                out1.println("</script>");//输出script结尾标签
                break;
            default://database is error
                PrintWriter out2 = response.getWriter();//通过servlet的doget方法获取response对象，通过getWriter方法获取PrintWriter对象
                out2.flush();//清空缓存
                out2.println("<script>");//输出script标签
                out2.println("alert('username and pwd are not match or may be you are not sign in');");//js语句：输出alert语句
                out2.println("history.back();");//js语句：输出网页回退语句
                out2.println("</script>");//输出script结尾标签
                break;
        }
    }
}
