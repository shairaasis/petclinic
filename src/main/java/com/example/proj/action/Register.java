package com.example.proj.action;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


import com.example.proj.model.Account;
import com.opensymphony.xwork2.ActionSupport;

public class Register extends ActionSupport {
    
    private static final long serialVersionUID = 1L;
                                 
    private static Account accountBean;
    private String error = "Random";
    private String status;
    private String encryptedPassword;
    private int typeOfAccount;
    private int accountId;

    public String execute() throws Exception {
        accountBean = getAccountBean();
        accountBean.setAccountType(3);
        if(typeOfAccount == 1)
        {
            accountBean.setAccountType(1);
            if(saveToDB()) {
                addActionMessage("You have successfuly created an account.");
                return "accountregistered";
            } else {
                return ERROR;
            }
        }   
        else if (typeOfAccount == 2){
            accountBean.setAccountType(2);
            if(saveToDB()) {
                return "accountregistered";
            } else {
                return ERROR;
            }
        }else if (typeOfAccount == 3){
            accountBean.setAccountType(3);
            if(saveToDB()) {
                return "accountregistered";
            } else {
                return ERROR;
            }
        }else{
            if(saveToDB()) {
                return SUCCESS;
            } else {
                return ERROR;
            }
        }
        
    }

    public boolean saveToDB() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                setEncryptedPassword(encryptMD5(accountBean.getPassword()));
                String sql = "INSERT INTO accounts(account_type_id, username, password, first_name, last_name, address, email, contact_no) VALUES('"+accountBean.getAccountType()+"','"+accountBean.getUsername()+"','"+ getEncryptedPassword()+"','"+accountBean.getFirstName()+"','"+accountBean.getLastName()+"','"+accountBean.getAddress()+"', '"+accountBean.getEmail()+"', '"+accountBean.getContactNo()+"')";
                statement.executeUpdate(sql);
                status = "Account Successfully added!";
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

    private String encryptMD5(String password) throws NoSuchAlgorithmException {
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

    public String getError() {
        return error;
    }
    
    public Account getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(Account account) {
        Register.accountBean = account;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public int getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(int typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    

    
}