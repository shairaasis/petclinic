package com.example.proj.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.proj.model.Appointment;
import com.opensymphony.xwork2.ActionSupport;

public class Approve extends ActionSupport{
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    private Appointment appointment;
    private String appointmentId;
    private String error;
    private String appointmentStatus;
    private String accountId;
    public String execute() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, DATE_FORMAT(schedule, '%a, %b %d %Y') AS dateOfAppointment, CONCAT(customer_name.first_name,' ',customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,' ', veterinarian_name.last_name) AS Veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id where status = 'approved' order by schedule ASC";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Appointment appointment=new Appointment();
                    appointment.setAppointmentId(rs.getInt(1));
                    appointment.setClientId(rs.getInt(2));
                    appointment.setPetId(rs.getInt(3));
                    appointment.setVeterinarianId(rs.getInt(4));
                    appointment.setServiceId(rs.getInt(5));
                    appointment.setSchedule(rs.getString(9));
                    appointment.setTimeOfAppointment(rs.getInt(7));
                    appointment.setStatus(rs.getString(8));
                    appointment.setCustomer(rs.getString(10));
                    appointment.setVeterinarian(rs.getString(11));
                    appointment.setPetName(rs.getString(12));
                    appointment.setService(rs.getString(13));
                    appointments.add(appointment);
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }

    public String approveAppointment() throws SQLException, IOException, InterruptedException{
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                String sql = "update appointments set status='approved' where appointment_id =" +getAppointmentId();
                statement.executeUpdate(sql);
                appointmentStatus = "Appointment approved!";

                if(accountId != null){
                    return "vetApproved";
                }
                return SUCCESS;
            
            } else {
                error = "DB connection failed";
                return error;
            }
         } catch (Exception e) {
             error = e.toString();
             return error;  
         } finally {
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
    }
    public String approvedClientAppointments() throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, CONCAT(customer_name.first_name,' ',customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,' ', veterinarian_name.last_name) AS veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id where status='approved' and customer_id ="+getAccountId();
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Appointment appointment=new Appointment();
                    appointment.setAppointmentId(rs.getInt(1));
                    appointment.setClientId(rs.getInt(2));
                    appointment.setPetId(rs.getInt(3));
                    appointment.setVeterinarianId(rs.getInt(4));
                    appointment.setServiceId(rs.getInt(5));
                    appointment.setSchedule(rs.getString(6));
                    appointment.setTimeOfAppointment(rs.getInt(7));
                    appointment.setStatus(rs.getString(8));
                    appointment.setCustomer(rs.getString(9));
                    appointment.setVeterinarian(rs.getString(10));
                    appointment.setPetName(rs.getString(11));
                    appointment.setService(rs.getString(12));
                    appointments.add(appointment);
                }
                return SUCCESS;
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
    public Appointment getAppointment() {
        return appointment;
    }
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    
    
}
