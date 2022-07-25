package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.proj.model.PetService;
import com.opensymphony.xwork2.ActionSupport;

public class AddService extends ActionSupport{
    
    private static PetService serviceBean;
    private String error;

    public String execute() throws Exception{
        serviceBean = getServiceBean();
        if(saveToDB(serviceBean)) {
            return "success";
        } else {
            return ERROR;
        }

    }

    public boolean saveToDB(PetService serviceBean) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                String sql = "INSERT INTO services(service, fee) VALUES('"+serviceBean.getService()+"','"+ serviceBean.getFee()+"')";
                statement.executeUpdate(sql);
                return true;
            } else {
                error = "DB connection failed";
                return false;
            }
         } catch (Exception e) {
             error = e.toString();
             return false;  
         } finally {
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public PetService getServiceBean() {
        return serviceBean;
    }

    public static void setServiceBean(PetService service) {
        AddService.serviceBean = service;
    }

}
