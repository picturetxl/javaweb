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

/**
 * TeacherInfoServlet
 */
public class TeacherInfoServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MysqlJavaBean bean=new MysqlJavaBean();
            ResultSet resultset=bean.queryteacher();
            HttpSession session=request.getSession();
            ArrayList<Teacher> teacherList=new ArrayList<>();
            while(resultset.next())
            {
                Teacher teacher = new Teacher();
                teacher.setTech_id(resultset.getString("tech_id"));
                teacher.setTech_name(resultset.getString("tech_name"));
                teacher.setTech_password(resultset.getString("tech_password"));
                teacher.setTech_phone(resultset.getString("tech_phone"));
                teacherList.add(teacher);
                session.setAttribute("teacherList", teacherList);
            }
            resultset.close();
            response.sendRedirect("teacherinfo.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



