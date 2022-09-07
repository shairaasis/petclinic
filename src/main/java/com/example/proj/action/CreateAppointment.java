package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// EMAIL
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// EMAIL > 
import com.example.proj.model.Appointment;
import com.opensymphony.xwork2.ActionSupport;

public class CreateAppointment extends ActionSupport{
    static Appointment appointmentBean;
    private String error = "Random";
    private String successMessage;
    // < EMAIL
    private String from = "pet.clinic.confirmation@gmail.com";
    private String password = "ijopmxuhytcmzruv";
    private String to = "mad.dinar25@gmail.com";
    private String subject = "test";
    private String body = "creation confirmation test"; 
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
    // EMAIL >

    public String execute() throws Exception{
        appointmentBean = getAppointmentBean();
        appointmentBean.setDateOfAppointment(appointmentBean.getDateOfAppointment().substring(0, 10));
        getAppointmentData(appointmentBean);
        if(saveAppointment(appointmentBean) == true) {
            return "success";
        } else {
            return ERROR;
        }
    }
    public String getAppointmentData(Appointment appointmentBean)throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "select accounts.account_id, CONCAT(accounts.first_name, ' ', accounts.last_name) AS Veterinarian, services.service_id, services.service, pets.pet_id, pets.pet_name FROM accounts, services, pets where CONCAT(accounts.first_name, ' ', accounts.last_name) ="+ appointmentBean.getVeterinarian()+" and service ="+appointmentBean.getService()+" and pet_name =" +appointmentBean.getPetName();
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    appointmentBean.setVeterinarianId(rs.getInt(1));
                    appointmentBean.setVeterinarian(rs.getString(2));
                    appointmentBean.setServiceId(rs.getInt(3));
                    appointmentBean.setService(rs.getString(4));
                    appointmentBean.setPetId(rs.getInt(5));
                    appointmentBean.setPetName(rs.getString(6));
                }
                System.out.println("\n\n======get appointment data method==\n\n");
                System.out.println("=======\n\n\n"+appointmentBean.getDateOfAppointment()+"\n\n\n=========\n"+appointmentBean.getTimeOfAppointment()+"\n\n");
                System.out.println("===\n\n"+appointmentBean.getServiceId()+" "+ appointmentBean.getPetId()+"=======\n\n\n");
        
            } 
        } catch (Exception e) {

        } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return SUCCESS;
    }
    public boolean saveAppointment(Appointment appointmentBean) throws Exception {
        System.out.println("\n\n======SAve apoointment methof==\n\n");
        System.out.println("=======\n\n\n"+appointmentBean.getDateOfAppointment()+"\n\n\n=========\n"+appointmentBean.getTimeOfAppointment()+"\n\n");
        System.out.println("===\n\n"+appointmentBean.getServiceId()+" "+ appointmentBean.getPetId()+"=======\n\n\n");
        String status = "pending";
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                String sql = "INSERT INTO appointments (CUSTOMER_ID, PET_ID, VETERINARIAN_ID,SERVICE,SCHEDULE,status) VALUES('"+appointmentBean.getClientId()+"','"+appointmentBean.getPetId()+"','"+ appointmentBean.getVeterinarianId()+"','"+appointmentBean.getServiceId()+"','"+appointmentBean.getDateOfAppointment().concat(" "+appointmentBean.getTimeOfAppointment())+"','"+status+"')";
                statement.executeUpdate(sql);
                // EMAIL
                Session session = Session.getDefaultInstance(properties,  
                new javax.mail.Authenticator() {
                protected PasswordAuthentication 
                getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
                }
            }
            );
    
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
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



    public static Appointment getAppointmentBean() {
        return appointmentBean;
    }
    public static void setAppointmentBean(Appointment appointmentBean) {
        CreateAppointment.appointmentBean = appointmentBean;
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

    
}
