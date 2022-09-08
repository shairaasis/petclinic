package com.example.proj.action;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    private String concat = "' '";
    private String appointmentId;
    private String error;
    private String appointmentStatus;
    private String accountId;

    final private String from = "pet.clinic.confirmation@gmail.com";
    final private String password = "ijopmxuhytcmzruv";
    private String to = null;
    private String subject = "approved appointment";
    private String body = "Sending this message to the client to notify about the approved appointment.";
    static Properties properties = new Properties();
    static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }
    

    public String execute() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, CONCAT(customer_name.first_name,"+ getConcat() +",customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,"+getConcat()+", veterinarian_name.last_name) AS veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id where status='approved' order by schedule ASC";
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
                    appointment.setSchedule(appointment.getSchedule().substring(0, 16));
                    appointment.setStatus(rs.getString(7));
                    appointment.setCustomer(rs.getString(8));
                    appointment.setVeterinarian(rs.getString(9));
                    appointment.setPetName(rs.getString(10));
                    appointment.setService(rs.getString(11));
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
        PreparedStatement preparedStatement = null;
        // HttpRequest request = HttpRequest.newBuilder()
		// .uri(URI.create("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send"))
		// .header("content-type", "application/json")
		// .header("X-RapidAPI-Key", "8e5fc8ca86mshebf74458f8359fap1bbbabjsn71215df7915c")
		// .header("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
		// .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"personalizations\": [\r\n        {\r\n            \"to\": [\r\n                {\r\n                    \"email\": \"asisshaira99@gmail.com\"\r\n                }\r\n            ],\r\n            \"subject\": \"Appointment Confirmed\"\r\n        }\r\n    ],\r\n    \"from\": {\r\n        \"email\": \"shairaasis99@gmail.com\"\r\n    },\r\n    \"content\": [\r\n        {\r\n            \"type\": \"text/plain\",\r\n            \"value\": \"Hello, This is to inform you that your appointments has been confirmed!\"\r\n        }\r\n    ]\r\n}"))
		// .build();
        // HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        // System.out.println(response.body());
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                String sql = "update appointments set status='approved' where appointment_id =" +getAppointmentId();
                statement.executeUpdate(sql);
                appointmentStatus = "Appointment approved!";
                // EMAIL
                emailApproved();
                String sql1 = "SELECT appointments.*, CONCAT(customer_name.first_name,"+ getConcat() +",customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,"+getConcat()+", veterinarian_name.last_name) AS veterinarian, pets.pet_name, services.service, customerEmail.email as customerEmail, veterinarianEmail.email as veterinarianEmail FROM appointments inner join accounts as customerEmail on appointments.customer_id = customerEmail.account_id inner join accounts as veterinarianEmail on appointments.veterinarian_id = veterinarianEmail.account_id INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id where appointment_id='"+getAppointmentId()+"'";
                preparedStatement = connection.prepareStatement(sql1);
                ResultSet rs= preparedStatement.executeQuery();
                while(rs.next()){  
                    Appointment appointment=new Appointment();
                    appointment.setAppointmentId(rs.getInt(1));
                    appointment.setClientId(rs.getInt(2));
                    appointment.setPetId(rs.getInt(3));
                    appointment.setVeterinarianId(rs.getInt(4));
                    appointment.setServiceId(rs.getInt(5));
                    appointment.setSchedule(rs.getString(6));
                    appointment.setSchedule(appointment.getSchedule().substring(0, 16));
                    appointment.setStatus(rs.getString(7));
                    appointment.setCustomer(rs.getString(8));
                    appointment.setVeterinarian(rs.getString(9));
                    appointment.setPetName(rs.getString(10));
                    appointment.setService(rs.getString(11));
                    appointment.setCustomerEmail(rs.getString(12));
                    appointment.setVeterinarianEmail(rs.getString(13));
                    appointments.add(appointment);
                    
                    try {
                    HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send"))
                    .header("content-type", "application/json")
                    .header("X-RapidAPI-Key", "8e5fc8ca86mshebf74458f8359fap1bbbabjsn71215df7915c")
                    .header("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"personalizations\": [\r\n        {\r\n            \"to\": [\r\n                {\r\n                    \"email\": \""+appointment.getCustomerEmail()+"\"\r\n                }\r\n            ],\r\n            \"subject\": \"Appointment Confirmed\"\r\n        }\r\n    ],\r\n    \"from\": {\r\n        \"email\": \"shairaasis99@gmail.com\"\r\n    },\r\n    \"content\": [\r\n        {\r\n            \"type\": \"text/plain\",\r\n            \"value\": \"Hello, This is to inform you that your appointment has been confirmed! Appointment Details:\n   Schedule:"+appointment.getSchedule()+" \n   Veterinarian: "+appointment.getVeterinarian()+" \n  Service: "+appointment.getService()+" \n Pet: "+appointment.getPetName()+" \n  \"\r\n        }\r\n    ]\r\n}"))
                    .build();
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                }
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
    public String getConcat() {
        return concat;
    }
    public void setConcat(String concat) {
        this.concat = concat;
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

    
    
}
