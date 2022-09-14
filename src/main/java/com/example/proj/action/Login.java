package com.example.proj.action;
import com.example.proj.model.*;
import java.sql.PreparedStatement;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;                
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import java.sql.Statement;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware{
    private Map<String, Object> userSession;
    public String errorMessage;
    private static String encryptedPassword; 
    private String token;
    private String logoutToken = null;
    String sql;
    PreparedStatement preparedStatement;


    private static Account accountBean;

    public String execute() throws Exception {
        
        
        return SUCCESS;
    }

    public String login() throws Exception{
        accountBean = getAccountBean();
        if(validate(accountBean.getUsername(), accountBean.getPassword())){  
            token = generateToken();
            saveToken(token);
            userSession.put("token", token);
            if(accountBean.getAccountType() == 1){
                userSession.put("accountType", "admin");
                return "admin";
            }else if(accountBean.getAccountType() == 2){
                userSession.put("accountType", "veterinarian");
                return "veterinarian";

            }else{
                userSession.put("accountType", "client");
                return "client";
            }
        }  
        else{  
            errorMessage = "Login failed. Username and/or password is incorrect.";
            return "input";  
        }
    }


    private String generateToken(){
        token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
        
    }
    public boolean validate (String username,String password){  
        boolean status=false;  
        try{  
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC","root","password");  
        setEncryptedPassword(encryptMD5(accountBean.getPassword()));
        PreparedStatement ps=con.prepareStatement(  
            "select * from accounts where username=? and password=?");  
        ps.setString(1,username);  
        ps.setString(2,encryptedPassword);  
        ResultSet rs=ps.executeQuery();
        status=rs.next();   
            accountBean.setAccountId(rs.getInt(1));
            accountBean.setAccountType(rs.getInt(2));
            accountBean.setUsername(rs.getString(3));
            accountBean.setLastName(rs.getString(6));   
            accountBean.setFirstName(rs.getString(5)); 
            accountBean.setEmail(rs.getString(9));
            userSession.put("accountID", accountBean.getAccountId());
            userSession.put("userName", accountBean.getUsername());
            userSession.put("lastName", accountBean.getLastName());
            userSession.put("firstName", accountBean.getFirstName());
            userSession.put("eMail", accountBean.getEmail());
        }catch(Exception e){e.printStackTrace();}  
        return status;  
    } 
    public static String encryptMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5"); 
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder s = new StringBuilder();  
        for(int i=0; i<  hash.length; i++)  
        {  
                s.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));  
        }  
        encryptedPassword = s.toString();
        return encryptedPassword;
    }
    public boolean saveToken(String token) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");
            if (connection != null) {
                statement = connection.createStatement();
                sql = "INSERT INTO tokens(token) VALUES('"+token+"')";
                statement.executeUpdate(sql);
                return true;
            } else {
                errorMessage = "DB connection failed";
                return false;
            }
        } catch (Exception e) {
            errorMessage = e.toString();
            return false;  
        } finally {
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }
    public boolean deleteToken() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                logoutToken = (String) userSession.get("token");
                sql = " DELETE FROM tokens where token=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, logoutToken);
                preparedStatement.execute(); 
                userSession.clear();
                return true;
            } else {
                errorMessage = "DB connection failed";
                return false;
            }
        } catch (Exception e) {
            errorMessage = e.toString();
            return false;  
        } finally {
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }


    public String logout() throws SQLException{
        deleteToken();
        addActionMessage("You are logged out.");
        return SUCCESS;
    }

    
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
    public void setEncryptedPassword(String encryptedPassword) {
        Login.encryptedPassword = encryptedPassword;
    }
    public Account getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(Account accountBean) {
        Login.accountBean = accountBean;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public void setUserSession(Map<String, Object> userSession) {
        this.userSession = userSession;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        userSession = session;
        
    }

    public String getLogoutToken() {
        return logoutToken;
    }
    public void setLogoutToken(String logoutToken) {
        this.logoutToken = logoutToken;
    }

}