package tailiang;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReminderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MysqlJavaBean bean=new MysqlJavaBean();
            ResultSet resultSet=bean.selectReminder();
            HttpSession session=request.getSession();
            ArrayList<Reminder> reminderList=new ArrayList<>();
            while(resultSet.next())
            {
                Reminder reminder=new Reminder();
                reminder.setEquId(resultSet.getString("equ_id"));
                reminder.setEquName(resultSet.getString("equ_name"));
                reminder.setEquPrice(resultSet.getString("equ_price"));
                reminder.setTypeId(resultSet.getString("type_id"));
                reminderList.add(reminder);
                session.setAttribute("reminderList", reminderList);
            }
            resultSet.close();
            response.sendRedirect("reminder.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}