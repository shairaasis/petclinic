package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import com.example.proj.model.PetService;
import com.opensymphony.xwork2.ActionSupport;

public class Services1 extends ActionSupport{
    private static PetService serviceBean = new PetService();
    ArrayList<PetService> services = new ArrayList<PetService>();
    PetService service;
    private String error;

    public String execute() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * FROM SERVICES";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    PetService service=new PetService();
                    service.setServiceId(rs.getInt(1));
                    service.setService(rs.getString(2));
                    service.setFee(rs.getInt(3));
                    services.add(service);
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }

    public ArrayList<PetService> getServices() {
        return services;
    }
    public void setServices(ArrayList<PetService> services) {
        this.services = services;
    }
    public PetService getService() {
        return service;
    }
    public void setService(PetService service) {
        this.service = service;
    }

    public static PetService getServiceBean() {
        return serviceBean;
    }

    public static void setServiceBean(PetService service) {
        Services1.serviceBean = service;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    
}
