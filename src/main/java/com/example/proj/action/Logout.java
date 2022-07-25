package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import java.sql.Statement;
import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport implements SessionAware{
    private Map<String, Object> userSession;
    private String token;
    public String execute() throws Exception{
        deleteToken(userSession.get("token"));
        userSession.clear(); 
        return SUCCESS;
    }
    public boolean deleteToken(Object object) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                String sql = "DELETE from tokens where token="+userSession.get("token");
                statement.executeUpdate(sql);
                return true;
            } else {
                return false;
            }
         } catch (Exception e) {
            e.toString();
             return false;  
         } finally {
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
    }

    @Override
    public void setSession(Map<String, Object> session) {

        userSession = session;
    }
    public Map<String, Object> getUserSession() {
        return userSession;
    }
    public void setUserSession(Map<String, Object> userSession) {
        this.userSession = userSession;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    
}
