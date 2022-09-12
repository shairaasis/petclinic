package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// EMAIL
import java.sql.ResultSet;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// EMAIL > 

import com.opensymphony.xwork2.ActionSupport;

public class Cancel extends ActionSupport{
    private String accountId;
    private int appointmentId;
    private String appointmentStatus;

        // < EMAIL
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        final private String from = "pet.clinic.confirmation@gmail.com";
        final private String password = "ijopmxuhytcmzruv";
        private String to = null;
        
        private int toID;   
        private String to2 = null;
        private String subject = "TEST appointment cancellation";
        private String body2 = "TEST : should be received by client";
        private String body = "TEST : should be received by vet";
        
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

    // / method for delete the record
	public String execute() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {

                String sql = " SELECT customer_id FROM appointments WHERE appointment_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getAppointmentId());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setToID(rs.getInt(1));
                    System.out.println("clientid : " + getToID());
                }
                preparedStatement.close();
                sql = " SELECT email FROM accounts WHERE account_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getToID());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setTo(rs.getString(1));
                    System.out.println("clientemail : " + getTo());
                }
                emailCancelVetAd();
                sql = "DELETE FROM appointments WHERE appointment_id ='"+getAppointmentId()+"'";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                appointmentStatus ="Appointment is cancelled.";

                // EMAIL
                
                if( accountId == null){
                    return SUCCESS;
                }
                return "vetAppointmentCancelled";
            }
		} catch (Exception e) {

        } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return ERROR;
	}

    public String cancelledByClient() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = " SELECT veterinarian_id FROM appointments WHERE appointment_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getAppointmentId());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setToID(rs.getInt(1));
                    System.out.println("vet ID : " + getToID());
                }
                preparedStatement.close();
                sql = " SELECT email FROM accounts WHERE account_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getToID());
                rs = preparedStatement.executeQuery();
                while (rs.next()){
                    setTo(rs.getString(1));
                    System.out.println("vetemail : " + getTo());
                }
                emailCancelClient();
                sql = "DELETE FROM appointments WHERE appointment_id ='"+getAppointmentId()+"'";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                appointmentStatus ="Appointment is cancelled.";
                
                return SUCCESS;
            }
		} catch (Exception e) {

        } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return ERROR;
	}
    public String emailCancelClient(){
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
            // EMAIL to vet / admin
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setSubject(getSubject());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getTo()));
            message.setText(getBody());
            Transport.send(message);
            System.out.println("myEMAIL 1st");
            // EMAIL to vet / admin />
            } catch(Exception e) {
                ret = ERROR;
                e.printStackTrace();
                System.out.println(e);
            }
        return ret;
    }

    public String emailCancelVetAd(){
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
            message.setText(getBody2());
            Transport.send(message);
            System.out.println("myEMAIL 2nd");
            // EMAIL to client 
            } catch(Exception e) {
                ret = ERROR;
                e.printStackTrace();
                System.out.println(e);
            }
        return ret;
    }
    
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public String getAppointmentStatus() {
        return appointmentStatus;
    }
    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
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

    public String getTo2() {
        return to2;
    }

    public void setTo2(String to2) {
        this.to2 = to2;
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

    public String getBody2() {
        return body2;
    }

    public void setBody2(String body2) {
        this.body2 = body2;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        Cancel.properties = properties;
    }    

    public int getToID() {
        return toID;
    }

    public void setToID(int toID) {
        this.toID = toID;
    }
}
