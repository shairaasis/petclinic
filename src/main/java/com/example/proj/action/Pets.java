package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.proj.model.Pet;
import com.opensymphony.xwork2.ActionSupport;

public class Pets extends ActionSupport{
    private static Pet petBean = new Pet();
    private String error = "Random";
    private int accountId;
    ArrayList<Pet> pets = new ArrayList<Pet>();
    Pet pet;
        
    public String execute() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * from pets where owner_id ="+getAccountId();
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Pet pet=new Pet();
                    pet.setPetId(rs.getInt(1));
                    pet.setOwnerId(rs.getInt(2));
                    pet.setHealthRecordId(rs.getInt(3));
                    pet.setPetName(rs.getString(4));
                    pet.setDateOfBirth(rs.getString(5));
                    pet.setBreed(rs.getString(6));
                    pet.setCoatColor(rs.getString(7));
                    pets.add(pet);
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }
    public String registerPet() throws Exception{
        petBean = getPetBean();
        
        System.out.println("======\n\n "+petBean.getDateOfBirth()+"\n\n===========");
        // String pattern = "MM-dd-yyyy";
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        // String dateOfBirth = simpleDateFormat.format(new Date());
        // dateOfBirth = petBean.getDateOfBirth();
        // petBean.setDateOfBirth(dateOfBirth);
        petBean.setDateOfBirth(petBean.getDateOfBirth().substring(0, 10));
        if(saveToDB(petBean) == true) {
            getSuccessMessage();
            return "success";
        } else {
            return ERROR;
        }
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }
    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
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
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


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
        return "Pet successfully registered!";
    }


    public void setSuccessMessage(String successMessage) {
    }

    
    
}
