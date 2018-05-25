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


public class SelectBuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MysqlJavaBean bean=new MysqlJavaBean();
            ResultSet resultset=bean.selectbuyinfo();
            HttpSession session=request.getSession();
            ArrayList<BuyInfo> buyList=new ArrayList<>();
            while(resultset.next())
            {
                BuyInfo buyInfo=new BuyInfo();
                buyInfo.setBuyId(resultset.getString("buy_id"));
                buyInfo.setBuyName(resultset.getString("buy_name"));
                buyInfo.setBuyTel(resultset.getString("buy_tel"));
                buyInfo.setBuySex(resultset.getString("buy_sex"));
                buyInfo.setAdmId(resultset.getString("adm_id"));
                buyInfo.setEquId(resultset.getString("equ_id"));
                buyInfo.setEquName(resultset.getString("equ_name"));
                buyInfo.setEquPrice(resultset.getString("equ_price"));
                buyInfo.setTypeId(resultset.getString("type_id"));
                buyList.add(buyInfo);
                session.setAttribute("buyList", buyList);
            }
            resultset.close();
            response.sendRedirect("buyinfo.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
