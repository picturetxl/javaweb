package tailiang;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BorrowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String usrname=request.getParameter("username");
            MysqlJavaBean bean=new MysqlJavaBean();
            ResultSet resultSet=bean.selectborrow(usrname);
            HttpSession session=request.getSession();
            // session.setMaxInactiveInterval(30*60);//set the timeout of session
            ArrayList<Borrow> borrowList=new ArrayList<>();
            while(resultSet.next())
            {
                Borrow borrow=new Borrow();
                borrow.setEquId(resultSet.getString("equ_id"));
                borrow.setEquName(resultSet.getString("equ_name"));
                borrow.setEquPrice(resultSet.getString("equ_price"));
                borrowList.add(borrow);
                session.setAttribute("BorrowList", borrowList);
            }
            resultSet.close();
            response.sendRedirect("borrow.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}