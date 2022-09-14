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

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Approve extends ActionSupport{
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    private Appointment appointment;
    private int appointmentId;
    private String error;
    private String appointmentStatus;
    private int accountId;
    private int customerID;
    private int petID;
    private int vetID;
    private int service;
    private String eSchedule;
    private int eTime;
    private String clientName;
    private String petName;
    private String vetFName;
    private String vetLName;
    private String serviceName;
    private String dateOfAppointment;
    private String timeOfAppointment;

    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    final private String from = "pet.clinic.confirmation@gmail.com";
    final private String password = "ijopmxuhytcmzruv";
    private String to = null;
    private String to2= null;
    private String subject = null;
    private String body = null;
    private String body2 =null;
    static Properties properties = new Properties();
    static {
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
        }
    
    public String execute() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, concat(DATE_FORMAT(schedule, '%a, %b %d %Y'),' ', time_format(time, '%h:%i %p')) AS currentSchedule, CONCAT(customer_name.first_name,' ',customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,' ', veterinarian_name.last_name) AS Veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id inner join time on appointments.timeID=time.timeID where status = 'approved' order by schedule ASC";
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
                sql = " SELECT customer_id, pet_id, veterinarian_id, service, schedule, timeID  FROM appointments WHERE appointment_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getAppointmentId());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setCustomerID(rs.getInt(1));
                    setPetID(rs.getInt(2));
                    setVetID(rs.getInt(3));
                    setService(rs.getInt(4));
                    seteSchedule(rs.getString(5));
                    seteTime(rs.getInt(6));
                }
                sql = "SELECT email, first_name FROM accounts WHERE account_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getCustomerID());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setTo(rs.getString(1));
                    setClientName(rs.getString(2));
                }
                sql = "SELECT pet_name FROM pets WHERE pet_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getPetID());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setPetName(rs.getString(1));
                }
                sql = "SELECT email, first_name, last_name FROM accounts WHERE account_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getVetID());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setTo2(rs.getString(1));
                    setVetFName(rs.getString(2));
                    setVetLName(rs.getString(3));
                }
                sql = "SELECT service FROM services WHERE service_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getService());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setServiceName(rs.getString(1));
                }
                sql = "SELECT time FROM time WHERE timeID=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, geteTime());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setTimeOfAppointment(rs.getString(1));
                }
                setSubject("Approved: Appointment for "+ getPetName() +" on " +geteSchedule()+" was approved.");
                setBody("Hello "+getClientName()+",\n \n We're glad to inform you that your scheduled appointment was approved. \n\n Appointment Details: \n Date: " +geteSchedule()+ "\n Time: "+getTimeOfAppointment()+" \n Veterinarian: "+getVetFName() +" "+getVetLName()+"\n Pet: "+getPetName()+"\n Service: "+getServiceName()+"\n \n  See you!");
                emailApproved();
                if(accountId == getVetID()){
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

    public String emailApproved(){
        String ret = SUCCESS;
        try {
            Session session = Session.getDefaultInstance(properties,  
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication 
                    getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                }
            );
            // EMAIL to client
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setSubject(getSubject());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getTo()));
            message.setText(getBody());
            Transport.send(message);
            // EMAIL to client />
            } catch(Exception e) {
                ret = ERROR;
                e.printStackTrace();
                System.out.println(e);
            }
        return ret;
    }
    public String approvedClientAppointments() throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, concat(DATE_FORMAT(schedule, '%a, %b %d %Y'),' ', time_format(time, '%h:%i %p')) AS currentSchedule, CONCAT(customer_name.first_name,' ',customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,' ', veterinarian_name.last_name) AS veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id inner join time on appointments.timeID=time.timeID where status='approved' and customer_id ="+getAccountId()+" order by schedule asc";
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

   

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFrom() {
        return from;
    }

    public String getPassword() {
        return password;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getVetID() {
        return vetID;
    }

    public void setVetID(int vetID) {
        this.vetID = vetID;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String geteSchedule() {
        return eSchedule;
    }

    public void seteSchedule(String eSchedule) {
        this.eSchedule = eSchedule;
    }

    public int geteTime() {
        return eTime;
    }

    public void seteTime(int eTime) {
        this.eTime = eTime;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        Approve.properties = properties;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getVetFName() {
        return vetFName;
    }

    public void setVetFName(String vetFName) {
        this.vetFName = vetFName;
    }

    public String getVetLName() {
        return vetLName;
    }

    public void setVetLName(String vetLName) {
        this.vetLName = vetLName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getTimeOfAppointment() {
        return timeOfAppointment;
    }

    public void setTimeOfAppointment(String timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    public String getTo2() {
        return to2;
    }

    public void setTo2(String to2) {
        this.to2 = to2;
    }

    public String getBody2() {
        return body2;
    }

    public void setBody2(String body2) {
        this.body2 = body2;
    }
}
