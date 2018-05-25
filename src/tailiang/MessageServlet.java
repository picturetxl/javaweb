
package tailiang;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * MessageServlet
extends HttpServlet */
public class MessageServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title=request.getParameter("title");
        String message=request.getParameter("message");
        HttpSession session=request.getSession();
        String username = (String)session.getAttribute("UserName");
        MysqlJavaBean bean= new MysqlJavaBean();
        PrintWriter out = response.getWriter();
        if(bean.insertmsg(username, title, message))
        {
            out.println("<script>");
            out.println("alert('submition is succussful');");
            out.println("history.back();");
            out.println("</script>");
        }
        else{
            out.println("<script>");
            out.println("alert('submition is failed,please contact with admininster');");
            out.println("history.back();");
            out.println("</script>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
