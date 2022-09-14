package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.example.proj.model.Appointment;
import com.opensymphony.xwork2.ActionSupport;

public class Client extends ActionSupport{
    private int accountId;
    private int petCount;
    private int pendingAppointments;
    private int approvedAppointments;
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String execute() throws Exception{
        LocalDate now = LocalDate.now();
        LocalDate afterWeek = LocalDate.now();
        afterWeek = afterWeek.plusDays(6);
        
        accountId = getAccountId();
        petCount = numberOfPets();
        pendingAppointments = numOfPendingAppointments();
        approvedAppointments = numOfApprovedAppointments();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, concat(DATE_FORMAT(schedule, '%a, %b %d %Y'),' ', time_format(time, '%h:%i %p')) AS currentSchedule, CONCAT(customer_name.first_name,' ',customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,' ', veterinarian_name.last_name) AS Veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id inner join time on appointments.timeID=time.timeID where customer_id ='"+accountId+"' and schedule <= '"+afterWeek+"' and schedule >= '"+now+"' order by schedule ASC";
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
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
        return SUCCESS;
    }
    public int numberOfPets() throws Exception{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT COUNT(*) FROM pets where owner_id ="+accountId;
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                setPetCount(rs.getInt(1));
                }
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return petCount;
    }
    public int numOfPendingAppointments() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT COUNT(*) FROM appointments where status = 'pending' and customer_id ="+accountId;
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
    public int numOfApprovedAppointments() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT COUNT(*) FROM appointments where status = 'approved' and customer_id ="+accountId;
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){
                    setApprovedAppointments(rs.getInt(1));
                }
                
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return approvedAppointments;
    }
    
    
    
    public int getPetCount() {
        return petCount;
    }
    public void setPetCount(int petCount) {
        this.petCount = petCount;
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
    public int getApprovedAppointments() {
        return approvedAppointments;
    }
    public void setApprovedAppointments(int approvedAppointments) {
        this.approvedAppointments = approvedAppointments;
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
    public static String getDateFormat() {
        return DATE_FORMAT;
    }
    public static DateFormat getDateformat() {
        return dateFormat;
    }
    public static DateTimeFormatter getDateformat8() {
        return dateFormat8;
    }
    
    
}
