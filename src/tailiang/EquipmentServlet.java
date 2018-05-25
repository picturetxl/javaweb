package tailiang;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EquipmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MysqlJavaBean bean=new MysqlJavaBean();
            ResultSet resultset=bean.selectall();
            HttpSession session=request.getSession();
            ArrayList<Equipment> aList=new ArrayList<>();
            while(resultset.next())
            {
                Equipment equipment=new Equipment();
                equipment.setEquid(resultset.getString("equ_id"));
                equipment.setEquname(resultset.getString("equ_name"));
                equipment.setEquprice(resultset.getString("equ_price"));
                equipment.setTypeid(resultset.getString("type_id"));
                equipment.setTypename(resultset.getString("type_name"));
                equipment.setSupid(resultset.getString("sup_id"));
                equipment.setSupaddr(resultset.getString("sup_addr"));
                equipment.setSupman(resultset.getString("sup_man"));
                equipment.setSuptel(resultset.getString("sup_tel"));
                equipment.setSupname(resultset.getString("sup_name"));
                aList.add(equipment);
                session.setAttribute("aList", aList);
            }
            resultset.close();
            response.sendRedirect("equipment.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
