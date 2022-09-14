package com.example.proj.action;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

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
    private int temp4DCode;
    PreparedStatement preparedStatement;
    ResultSet rs;
    private String contactNum;

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");


    public String execute() throws Exception {
        accountBean = getAccountBean();
        accountBean.setAccountType(3);
        if(typeOfAccount == 1)
        {
            accountBean.setAccountType(1);
            if(saveToDB()) {
                addActionMessage("You have successfully created an account, Please verify your account first. A 4-Digit code will be sent to your Contact #.");
                return "accountregistered";
            } else {
                return ERROR;
            }
        }   
        else if (typeOfAccount == 2){
            accountBean.setAccountType(2);
            if(saveToDB()) {
                addActionMessage("You have successfully created an account, Please verify your account first. A 4-Digit code will be sent to your Contact #.");
                return "accountregistered";
            } else {
                return ERROR;
            }
        }else if (typeOfAccount == 3){
            accountBean.setAccountType(3);
            if(saveToDB()) {
                addActionMessage("You have successfully created an account, Please verify your account first. A 4-Digit code will be sent to your Contact #.");
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
                statement.close();
                setTemp4DCode(fourDigitGenerator());
                sql = "SELECT account_id, contact_no FROM accounts WHERE username=? and password=?";
                preparedStatement = connection.prepareStatement(sql); 
                preparedStatement.setString(1, accountBean.getUsername());  
                preparedStatement.setString(2, getEncryptedPassword());  
                System.out.println("username: " + accountBean.getUsername());
                System.out.println("password: " + getEncryptedPassword());
                rs = preparedStatement.executeQuery();
                while (rs.next()){ 
                    setAccountId(rs.getInt(1));
                    setContactNum(rs.getString(2));
                }
                System.out.println("accountID: " +getAccountId());
                System.out.println("code: " + getTemp4DCode());
                statement = connection.createStatement();
                String sql2 = "INSERT INTO verification(account_id, status, code) VALUES('"+getAccountId()+"','"+"Pending"+"','"+getTemp4DCode()+"')";
                statement.executeUpdate(sql2);
                status = "Account Successfully added, Please verify your account with the 4-Digit Code that was sent to your Contact #.";
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                String myNum = getContactNum();
                Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(myNum),
                    new com.twilio.type.PhoneNumber("+19253970189"),
                        "Your Pet Clinic 4-Digit Code: " + getTemp4DCode())
                    .create();
                System.out.println(message.getSid());
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

    private int fourDigitGenerator() { 
        int min = 9999;
        int max = 1111;
        int randomNumber = (int)(Math.random() * (max - min + 1) + min);
        return randomNumber;
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
    public int getTemp4DCode() {
        return temp4DCode;
    }

    public void setTemp4DCode(int temp4dCode) {
        temp4DCode = temp4dCode;
    }
    
    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}