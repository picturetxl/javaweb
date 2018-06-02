package tailiang;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;



public class MysqlJavaBean {
    private String driverName="com.mysql.cj.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3306/myweb2?serverTimezone=GMT%2B8&useSSL=true";
    private String user="root";
    private String password="thll1tl2dl";
    private Connection connection=null;
    private Statement statement=null;
    private ResultSet resultset=null;
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public Statement getStatement() {
        return statement;
    }
    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    public ResultSet getResultSet() {
        return resultset;
    }
    public void setResultSet(ResultSet resultSet) {
        this.resultset = resultSet;
    }
    /**
     * 负责连接数据库
     * @return
     */
    private Statement getStmt()
    {
        try {
            Class.forName(getDriverName());
            connection=DriverManager.getConnection(getUrl(), getUser(), getPassword());
            return connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 教师注册检查
     * @param teacherid
     * @param teachername
     * @param password
     * @param password2
     * @param phone
     * @return
     */
    public int registerCheck(String teacherid,String teachername,String password,String password2,String phone)
    {
        try {
            String sqlquery="select tech_id from techer where tech_id="+teacherid;
            statement=getStmt();
            if(statement.executeQuery(sqlquery).next())
            {
                statement.close();
                connection.close();
                return 3;//该教师已经注册
            }
            else {//没有注册 再检查密码是否相同
                if(password.equals(password2))
                {
                    try {
                        String sql="insert into techer(tech_id,tech_name,tech_password,tech_phone)values"+"("+teacherid+",'"+teachername+"','"+password+"','"+phone+"')";
                        int row=statement.executeUpdate(sql);
                        if(row==1)
                        {
                            statement.close();
                            connection.close();
                            return 1;//正确注册
                        }
                        else
                        {
                            statement.close();
                            connection.close();
                            return 0;//数据库插入失败
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        return 0;
                    }
                }
                else {//密码不相同
                    statement.close();
                    connection.close();
                    return 2;//密码不匹配
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



    /**
     * 登录时检查用户角色 如果是管理员返回1 是教师返回2 否则返回0
     * @param userName
     * @param userPassword
     * @return
     */
    public int loginCheck(String userName,String userPassword)
    {
        try {
            String sqlquery1="select * from adminstrator where adm_name="+"'"+userName+"'"+" and "+"adm_password="+"'"+userPassword+"'";
            String sqlquery2="select * from techer where tech_name="+"'"+userName+"'"+" and "+"tech_password="+"'"+userPassword+"'";
            statement=getStmt();
            resultset=statement.executeQuery(sqlquery1);
            if(!resultset.next())
            {
                resultset=statement.executeQuery(sqlquery2);
                if(!resultset.next())
                {
                    statement.close();
                    connection.close();
                    return 0;
                }else {//2 teacher
                    statement.close();
                    connection.close();
                    return 2;
                }
            }//1 admin
            else
            {
                statement.close();
                connection.close();
                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 查询所有的设备情况
     * @return
     */
    public  ResultSet selectall()
    {
        try {
            String sql=" select e.*,type.type_name,s.* from equipment as e inner join type inner join supplier as s on e.type_id=type.type_id and type.sup_id=s.sup_id;";
            statement=getStmt();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 查询采购信息
     * @return
     */
    public ResultSet selectbuyinfo()
    {
        try {
            String sql="select buyer.*,equipment.* from buyer inner join equipment inner join buyequipment on equipment.equ_id=buyequipment.equ_id;";
            statement=getStmt();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询已借的设备
     * @param usrname
     * @return
     */
    public ResultSet selectborrow(String usrname)
    {
        try {
            String sqlquery="select tech_id from techer where tech_name="+"'"+usrname+"'";
            statement=getStmt();
            ResultSet result=statement.executeQuery(sqlquery);
            String techId=null;
            while(result.next())
            {
                techId=result.getString("tech_id");
            }
            String sql=" select rent.tech_id, rent.equ_id,e.equ_name,e.equ_price from rent inner join equipment as e inner join techer on rent.tech_id="+techId+" and  e.equ_id=rent.equ_id group by tech_id";
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *查询可借的设备
     * @return
     */
    public ResultSet selectReminder()
    {
        try {
            String sql="select e.* from equipment as e inner join rent on e.equ_id <> rent.equ_id";
            statement=getStmt();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 插入设备信息
     * @param equId
     * @param equName
     * @param equPrice
     * @param typeId
     * @param typeName
     * @param supId
     * @param supAddr
     * @param supMan
     * @param supTel
     * @param supName
     * @param buyerId
     * @return
     */
    public boolean addEquipment(String equId, String equName,String equPrice,String typeId,String typeName,
                                String supId,String supAddr,String supMan,String supTel,String supName,String buyerId)
    {
        try {
            String insert_supplier="insert into supplier(sup_id,sup_addr,sup_man,sup_tel,sup_name) values"+"("+supId+",'"+supAddr+"','"+supMan+"','"+supTel+"','"+supName+"')";
            String insert_type="insert into type(type_id,type_name,sup_id) values"+"("+typeId+",'"+typeName+"',"+supId+")";
            String insert_equipment="insert into equipment(equ_id,equ_name,equ_price,type_id) values"+"("+equId+",'"+equName+"','"+equPrice+"',"+typeId+")";
            String insert_buyequipment="insert into buyequipment values"+"("+null+","+buyerId+","+equId+")";
            String insert_order_info="insert into order_info values"+"("+null+","+supId+","+equId+")";
            String query_sup_id="select sup_id from supplier where sup_id="+supId;
            String query_type_id="select type_id from type where type_id="+typeId;
            String query_buy_id="select buy_id from buyer where buy_id="+buyerId;
            statement=getStmt();

            int row=0;
            //先看用户填写的supId是否已经存在 如果存在 则不执行插入supplier表        supplier->type->equipment->buyequipment->order_info
            if(statement.executeQuery(query_sup_id).next())
            {	//再看用户填写的typeid是否已经存在 如果存在 则不执行插入type表
                if(statement.executeQuery(query_type_id).next())
                {//supid typeid 都已经存在 所以只要执行插入equipment 和 buyequipment 表 即可
                    row=statement.executeUpdate(insert_equipment);
                    if(row==1)//正确执行后 插入order_info 和 buyequipment表
                    {
                        if (statement.executeQuery(query_buy_id).next()) {
                            // 插入 buyequipment表
                            row=statement.executeUpdate(insert_buyequipment);
                            if (row==1) {//buyequipment 插入成功
                                //插入order_info表
                                row=statement.executeUpdate(insert_order_info);
                                if (row==1) {
                                    statement.close();
                                    connection.close();
                                    return true;
                                } else {
                                    statement.close();
                                    connection.close();
                                    return false;
                                }
                            } else {//buyequipment 插入失败
                                statement.close();
                                connection.close();
                                return false;
                            }
                        }
                        else
                        {
                            statement.close();
                            connection.close();
                            return false;
                        }


                    }
                    else //equipment没插入执行成功
                    {
                        statement.close();
                        connection.close();
                        return false;
                    }
                }
                else//typeid不存在
                {//插入type表
                    row=statement.executeUpdate(insert_type);
                    if(row==1)//type成功插入后
                    {//插入equipment表
                        row=statement.executeUpdate(insert_equipment);
                        if(row==1)//正确执行后 插入order_info 和 buyequipment表
                        {// 插入 buyequipment表
                            row=statement.executeUpdate(insert_buyequipment);
                            if(row==1)//正确执行后 插入order_info 和 buyequipment表
                            {
                                if (statement.executeQuery(query_buy_id).next()) {
                                    // 插入 buyequipment表
                                    row=statement.executeUpdate(insert_buyequipment);
                                    if (row==1) {//buyequipment 插入成功
                                        //插入order_info表
                                        row=statement.executeUpdate(insert_order_info);
                                        if (row==1) {
                                            statement.close();
                                            connection.close();
                                            return true;
                                        } else {
                                            statement.close();
                                            connection.close();
                                            return false;
                                        }
                                    } else {//buyequipment 插入失败
                                        statement.close();
                                        connection.close();
                                        return false;
                                    }
                                }
                                else
                                {
                                    statement.close();
                                    connection.close();
                                    return false;
                                }
                            }
                            else //equipment没插入执行成功
                            {
                                statement.close();
                                connection.close();
                                return false;
                            }

                        }
                        else //equipment没插入执行成功
                        {
                            statement.close();
                            connection.close();
                            return false;
                        }

                    }
                    else//type插入失败
                    {
                        statement.close();
                        connection.close();
                        return false;
                    }
                }
            }
            else//supid不存在
            {//supid不存在就插入supplier
                row=statement.executeUpdate(insert_supplier);
                if(row==1)
                {//再看用户填写的typeid是否已经存在 如果存在 则不执行插入type表
                    if(statement.executeQuery(query_type_id).next())//type_id存在
                    {//插入equipment表
                        row=statement.executeUpdate(insert_equipment);
                        if(row==1)//正确执行后 插入order_info 和 buyequipment表
                        {
                            if (statement.executeQuery(query_buy_id).next()) {
                                // 插入 buyequipment表
                                row=statement.executeUpdate(insert_buyequipment);
                                if (row==1) {//buyequipment 插入成功
                                    //插入order_info表
                                    row=statement.executeUpdate(insert_order_info);
                                    if (row==1) {
                                        statement.close();
                                        connection.close();
                                        return true;
                                    } else {
                                        statement.close();
                                        connection.close();
                                        return false;
                                    }
                                } else {//buyequipment 插入失败
                                    statement.close();
                                    connection.close();
                                    return false;
                                }

                            }
                            else
                            {
                                statement.close();
                                connection.close();
                                return false;
                            }

                        }
                        else //equipment没插入执行成功
                        {
                            statement.close();
                            connection.close();
                            return false;
                        }
                    }
                    else//typeid不存在
                    {//插入type
                        row=statement.executeUpdate(insert_type);

                        if(row==1)//插入成功
                        {//再插入equipment
                            row=statement.executeUpdate(insert_equipment);
                            if(row==1)//正确执行后 插入order_info 和 buyequipment表
                            {// 插入 buyequipment表
                                row=statement.executeUpdate(insert_buyequipment);
                                if (row==1) {//buyequipment 插入成功
                                    //插入order_info表
                                    row=statement.executeUpdate(insert_order_info);
                                    if (row==1) {
                                        statement.close();
                                        connection.close();
                                        return true;
                                    } else {
                                        statement.close();
                                        connection.close();
                                        return false;
                                    }
                                } else {//buyequipment 插入失败
                                    statement.close();
                                    connection.close();
                                    return false;
                                }
                            }
                            else
                            {
                                statement.close();
                                connection.close();
                                return false;
                            }
                        }
                        else
                        {
                            statement.close();
                            connection.close();
                            return false;
                        }
                    }
                }
                else
                {
                    statement.close();
                    connection.close();
                    return false;
                }
            }//end of else1

        }// end of try
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//end of method

    public ResultSet lookdeleteequipment()
    {
        try {
            String sql="select * from equipment";
            statement=getStmt();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *demonstrate:
     *	删除设备
     *	先查该设备是否被借出
     *author:john
     *email:txltech123@163.com
     */
    public int deleteEquipment(String no)
    {
        String delequip="delete from equipment where equ_id="+no;
        String queryrent="select * from rent where equ_id="+no;
        try {
            statement=getStmt();
            ResultSet rs= statement.executeQuery(queryrent);//返回值永不为null
            if(rs.next()){
                return 3;//在rent表中 该设备已经被借出
            }
            else{
                int row=statement.executeUpdate(delequip);
                if(row==1)
                    return 1;//success
                else{
                    return 2;//delete error
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     *demonstrate:
     *	留言板信息插入到数据库中
     *author:john
     *email:txltech123@163.com
     */
    public boolean insertmsg(String username,String title,String msg)
    {
        //you must add double qutoe to the mysql sentense but why?
        String msgsql="insert into message(mid,username,title,message) values(null,"+"\""+username+"\""+","+"\""+title+"\""+","+"\""+msg+"\""+")";
        try {
            statement=getStmt();
            int row=statement.executeUpdate(msgsql);
            if(row==1)
            {
                statement.close();
                connection.close();
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *demonstrate:
     *	查询留言板信息
     *author:john
     *email:txltech123@163.com
     */
    public ResultSet querymsg()
    {
        try {
            String sql="select * from message";
            statement=getStmt();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *demonstrate:
     *	查询老师信息
     *author:john
     *email:txltech123@163.com
     */
    public ResultSet queryteacher()
    {
        try {
            String sql="select * from techer";
            statement=getStmt();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *demonstrate:
     *	查询供货商信息
     *author:john
     *email:txltech123@163.com
     */
    public ResultSet querysupplier()
    {
        try {
            String sql="select * from supplier";
            statement=getStmt();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

