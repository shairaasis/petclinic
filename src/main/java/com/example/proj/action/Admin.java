package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import com.example.proj.model.Appointment;
import com.opensymphony.xwork2.ActionSupport;

public class Admin extends ActionSupport{
    private int accountId;
    private int clients;
    private int pendingAppointments;
    private int totalSales;
    private String today;
    LocalDate now = LocalDate.now();
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    public String execute() throws Exception{
        
        setClients(numberOfClients());
        setPendingAppointments(numOfPendingAppointments());
        setTotalSales(totalSales());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, concat(DATE_FORMAT(schedule, '%a, %b %d %Y'),' ', time_format(time, '%h:%i %p')) AS currentSchedule, CONCAT(customer_name.first_name,' ',customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,' ', veterinarian_name.last_name) AS Veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id inner join time on appointments.timeID=time.timeID where schedule = '"+now+"' order by schedule ASC";
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
                    // appointment.setSchedule(appointment.getSchedule().substring(0, 16));
                    appointment.setTimeOfAppointment(rs.getInt(7));
                    appointment.setStatus(rs.getString(8));
                    appointment.setCustomer(rs.getString(10));
                    appointment.setVeterinarian(rs.getString(11));
                    appointment.setPetName(rs.getString(12));
                    appointment.setService(rs.getString(13));
                    appointments.add(appointment);
                }
                
                today = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
        return SUCCESS;
    }
    public int numberOfClients() throws Exception{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT COUNT(*) FROM accounts where account_type_id = 3";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                setClients(rs.getInt(1));
                }
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return clients;
    }
    public int numOfPendingAppointments() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT COUNT(*) FROM appointments where status = 'pending'";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                    setPendingAppointments(rs.getInt(1));
                }
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return pendingAppointments;
    }

    public int totalSales() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT SUM(services.fee)AS 'Total Sale' FROM services INNER JOIN appointments ON services.service_id = appointments.service where status = 'approved'";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                    setTotalSales(rs.getInt(1));
                }
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return totalSales;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    
    public int getPendingAppointments() {
        return pendingAppointments;
    }

    public void setPendingAppointments(int pendingAppointments) {
        this.pendingAppointments = pendingAppointments;
    }

    
    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }
    public int getTotalSales() {
        return totalSales;
    }
    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
    public LocalDate getNow() {
        return now;
    }
    public void setNow(LocalDate now) {
        this.now = now;
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
    public String getToday() {
        return today;
    }
    public void setToday(String today) {
        this.today = today;
    }

    
}
