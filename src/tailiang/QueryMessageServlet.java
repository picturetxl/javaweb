package tailiang;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class QueryMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MysqlJavaBean bean=new MysqlJavaBean();
            ResultSet resultset=bean.querymsg();
            HttpSession session=request.getSession();
            ArrayList<Message> messageList=new ArrayList<>();
            while(resultset.next())
            {
                Message message=new Message();
                message.setMid(resultset.getString("mid"));
                message.setUsername(resultset.getString("username"));
                message.setTitle(resultset.getString("title"));
                message.setMessage(resultset.getString("message"));
                messageList.add(message);
                session.setAttribute("messageList", messageList);//name - value
            }
            resultset.close();
            response.sendRedirect("admessage.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
