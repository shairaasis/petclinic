package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.proj.model.Account;
import com.opensymphony.xwork2.ActionSupport;

public class Update extends ActionSupport {
    private static Account accountBean = new Account();
    private String accountId;
    private String error;
    
    public String execute() throws Exception{
        
        accountBean = getAccountBean();
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                String sql = "update accounts set username ="+accountBean.getAccountType()+", first_name="+accountBean.getFirstName()+", last_name="+accountBean.getLastName()+", address="+accountBean.getAddress()+", email="+accountBean.getEmail()+", contact_no = "+ accountBean.getContactNo()+" where account_id =" +accountBean.getAccountId();
                statement.executeUpdate(sql);
                return SUCCESS;
            } else {
                error = "DB connection failed";
                return error;
            }
         } catch (Exception e) {
             error = e.toString();
             return error;  
         } finally {
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public static Account getAccountBean() {
        return accountBean;
    }

    public static void setAccountBean(Account accountBean) {
        Update.accountBean = accountBean;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
