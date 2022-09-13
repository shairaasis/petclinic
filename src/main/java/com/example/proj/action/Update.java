package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.proj.model.Account;
import com.opensymphony.xwork2.ActionSupport;

public class Update extends ActionSupport {
    private Account accountBean;
    private String accountId;
    private String appointmentId;
    private String error = "";
    private String updateStatus = "";
    public String execute() throws Exception{
        accountBean = getAccountBean();

        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");
            String encryptedPassword = Login.encryptMD5(accountBean.getPassword());
            if (connection != null) {
                statement = connection.createStatement();
                
                String sql = "update accounts set username ='"+accountBean.getUsername()+"', password = '"+encryptedPassword+"', first_name='"+accountBean.getFirstName()+"', last_name='"+accountBean.getLastName()+"', address='"+accountBean.getAddress()+"', email='"+accountBean.getEmail()+"', contact_no = '"+ accountBean.getContactNo()+"' where account_id =" +getAccountId();
                statement.executeUpdate(sql);
                setUpdateStatus("Account update successful");
                return SUCCESS;
            } else {
                error = "DB connection failed";
                return ERROR;
            }
         } catch (SQLException e) {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Account getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(Account accountBean) {
        this.accountBean = accountBean;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
    
}
