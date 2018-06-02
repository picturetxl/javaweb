package tailiang;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MysqlJavaBean bean=new MysqlJavaBean();
            ResultSet resultset=bean.querysupplier();
            HttpSession session=request.getSession();
            ArrayList<Supplier> aList=new ArrayList<>();
            while(resultset.next())
            {
                Supplier supplier = new Supplier();
                supplier.setSupid(resultset.getString("sup_id"));
                supplier.setSupaddr(resultset.getString("sup_addr"));
                supplier.setSupman(resultset.getString("sup_man"));
                supplier.setSuptel(resultset.getString("sup_tel"));
                supplier.setSupname(resultset.getString("sup_name"));
                aList.add(supplier);
                session.setAttribute("supplierList", aList);
            }
            resultset.close();
            response.sendRedirect("supplier.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
