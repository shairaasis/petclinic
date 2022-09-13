package com.example.proj.action;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.proj.model.Account;
import com.opensymphony.xwork2.ActionSupport;

public class Accounts extends ActionSupport{
    
    ArrayList<Account> accounts = new ArrayList<Account>();
    Account account;
    Account accountBean = new Account();
    private String status;
    private int filter;
    private int account_type_id;
    private int accountId;
    private String activeAccount;
    private String updateStatus;
    public String execute() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * FROM accounts ORDER BY last_name ASC";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Account account=new Account();
                    account.setAccountId(rs.getInt(1));
                    account.setUsername(rs.getString(3));
                    account.setLastName(rs.getString(6));   
                    account.setFirstName(rs.getString(5)); 
                    account.setEmail(rs.getString(8));
                    account.setAddress(rs.getString(7));
                    account.setContactNo(rs.getString(9)); 
                    accounts.add(account); 

                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }

    public String filterAccount() throws Exception {
        if(filter == 3){
            setAccount_type_id(3);
        }else if(filter == 2){
            setAccount_type_id(2);
        }else{
            setAccount_type_id(1);
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * FROM accounts where account_type_id ='"+getAccount_type_id()+"' ORDER BY last_name ASC";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Account account=new Account();
                    account.setAccountId(rs.getInt(1));
                    account.setUsername(rs.getString(3));
                    account.setLastName(rs.getString(6));   
                    account.setFirstName(rs.getString(5)); 
                    account.setEmail(rs.getString(8));
                    account.setAddress(rs.getString(7));
                    account.setContactNo(rs.getString(9)); 
                    accounts.add(account); 

                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }

    // Fetch account
    public String viewProfile() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * FROM accounts where account_id =" +getAccountId();
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){ 
                    accountBean.setAccountId(rs.getInt(1));
                    accountBean.setAccountType(rs.getInt(2));
                    accountBean.setUsername(rs.getString(3));
                    accountBean.setLastName(rs.getString(6));   
                    accountBean.setFirstName(rs.getString(5)); 
                    accountBean.setEmail(rs.getString(8));
                    accountBean.setAddress(rs.getString(7));
                    accountBean.setContactNo(rs.getString(9)); 

                    if(accountBean.getAccountType() == 1){
                        activeAccount = "admin";
                    }else if(accountBean.getAccountType() == 2){
                        activeAccount = "veterinarian";
                    }else if(accountBean.getAccountType() == 3){
                        activeAccount = "customer";
                    }
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
        return SUCCESS;
    }


    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getParameter(int accountId) {
        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccount_type_id() {
        return account_type_id;
    }

    public void setAccount_type_id(int account_type_id) {
        this.account_type_id = account_type_id;
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Account getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(Account accountBean) {
        this.accountBean = accountBean;
    }

    public String getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(String activeAccount) {
        this.activeAccount = activeAccount;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }



    
}
