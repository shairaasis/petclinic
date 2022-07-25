package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.proj.model.Pet;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterPet extends ActionSupport{
    static Pet petBean;
    private String error = "Random";
    private String successMessage;
    private String accountId;
    // Account accountFound = new Account();


    // private int accountId;
    public String execute() throws Exception{
        
        petBean = getPetBean();
        // String pattern = "MM-dd-yyyy";
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        // String dateOfBirth = simpleDateFormat.format(new Date());
        // dateOfBirth = petBean.getDateOfBirth();
        // petBean.setDateOfBirth(dateOfBirth);
        petBean.setDateOfBirth(petBean.getDateOfBirth().substring(0, 10));
        if(saveToDB(petBean) == true) {
            return "success";
        } else {
            return ERROR;
        }
    }

    public boolean saveToDB(Pet petBean) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                String sql = "INSERT INTO pets(owner_id, pet_name,date_of_birth, breed, coat_color) VALUES('"+petBean.getOwnerId()+"','"+petBean.getPetName()+"','"+petBean.getDateOfBirth()+"','"+petBean.getBreed()+"','"+petBean.getCoatColor()+"')";
                statement.executeUpdate(sql);
                successMessage = "Pet Successfully registered!";
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

    // public boolean findaccount(String username) throws Exception{
    //     boolean status=false;  
    //      try{  
    //       Class.forName("com.mysql.jdbc.Driver");  
    //       Connection con=DriverManager.getConnection(  
    //       "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC","root","password");  
    //       PreparedStatement ps=con.prepareStatement(  
    //         "select * from accounts where username=?");  
    //       ps.setString(1,username);  
    //       ResultSet rs=ps.executeQuery();
    //       while(rs.next()){   
    //         accountFound.setAccountId(rs.getInt(1));
    //       }
    //      }catch(Exception e){e.printStackTrace();}  
    //     return status;  
    // }
    public Pet getPetBean() {
        return petBean;
    }

    public void setPetBean(Pet pet) {
        RegisterPet.petBean = pet;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }


    
}
