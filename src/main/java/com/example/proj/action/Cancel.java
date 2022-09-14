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
    private int accountId;
    private int appointmentId;
    private String appointmentStatus;
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

    

        // < EMAIL
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        final private String from = "pet.clinic.confirmation@gmail.com";
        final private String password = "ijopmxuhytcmzruv";
        private String to = null;
        
        private int toID;   
        private String to2 = null;
        private String subject = null;
        private String body2 = null;
        private String body = null;
        
        static Properties properties = new Properties();
        static {
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
        }
        // EMAIL >

    // / method for cancelling the appointment
	public String execute() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");
            if (connection != null) {
                String sql = " SELECT customer_id, pet_id, veterinarian_id, service, schedule, timeID  FROM appointments WHERE appointment_id=?";
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
                preparedStatement.close();
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
                sql = "select email from accounts where account_id=? OR account_type_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getVetID());
                preparedStatement.setInt(2, 1);
                rs = preparedStatement.executeQuery();
                setSubject("Cancelled: Appointment for "+ getPetName() +" on " +geteSchedule()+" was canceled.");
                setBody2("Hello Admins and "+getVetFName() +" " + getVetLName() + ",\n \n We're sorry to inform you that your approved appointment was cancelled by "+ getVetFName() + " " +getVetLName() +" due to some various reasons. \n\n Appointment Details: \n Date: " +geteSchedule()+ "\n Time: "+getTimeOfAppointment()+" \n Veterinarian: "+getVetFName() +" "+getVetLName()+"\n Pet: "+getPetName()+"\n Service: "+getServiceName());
                while (rs.next()){
                    setTo2(rs.getString(1));
                    emailCancelClient();;
                }
                setSubject("Cancelled: Appointment for "+ getPetName() +" on " +geteSchedule()+" was canceled.");
                setBody("Hello "+getClientName()+",\n \n We're sorry to inform you that your scheduled appointment was cancelled due to some various reasons. \n\n Appointment Details: \n Date: " +geteSchedule()+ "\n Time: "+getTimeOfAppointment()+" \n Veterinarian: "+getVetFName() +" "+getVetLName()+"\n Pet: "+getPetName()+"\n Service: "+getServiceName()+"\n \n We still hope to see you soon.");
                emailCancelVetAd();
                sql = "DELETE FROM appointments WHERE appointment_id ='"+getAppointmentId()+"'";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                appointmentStatus ="Appointment is cancelled.";

                // EMAIL
                
                if(accountId == getVetID()){
                    return "vetAppointmentCancelled";
                }
                return SUCCESS;
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
                String sql = " SELECT customer_id, pet_id, veterinarian_id, service, schedule, timeID  FROM appointments WHERE appointment_id=?";
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
                preparedStatement.close();
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
                sql = "select email from accounts where account_id=? OR account_type_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, getVetID());
                preparedStatement.setInt(2, 1);
                rs = preparedStatement.executeQuery();
                setSubject("Cancelled: Appointment for "+ getPetName() +" on " +geteSchedule()+" was cancelled.");
                setBody2("Hello Admins and "+getVetFName() +" " + getVetLName() + ",\n \n We're sorry to inform you that your approved appointment was cancelled by "+ getClientName() +" due to some various reasons. \n\n Appointment Details: \n Date: " +geteSchedule()+ "\n Time: "+getTimeOfAppointment()+" \n Veterinarian: "+getVetFName() +" "+getVetLName()+"\n Pet: "+getPetName()+"\n Service: "+getServiceName());
                while (rs.next()){
                    setTo2(rs.getString(1));
                    emailCancelClient();;
                }
                setSubject("Cancelled: Appointment for "+ getPetName() +" on " +geteSchedule()+" was cancelled.");
                setBody("Hello "+getClientName()+",\n \n Cancellation of appointment was sent to your veterinarian . \n\n Appointment Details: \n Date: " +geteSchedule()+ "\n Time: "+getTimeOfAppointment()+" \n Veterinarian: "+getVetFName() +" "+getVetLName()+"\n Pet: "+getPetName()+"\n Service: "+getServiceName()+"\n \n We still hope to see you soon.");
                emailCancelVetAd();
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getTo2()));
            message.setText(getBody2());
            Transport.send(message);
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
            message.setText(getBody());
            Transport.send(message);
            // EMAIL to client 
            } catch(Exception e) {
                ret = ERROR;
                e.printStackTrace();
                System.out.println(e);
            }
        return ret;
    }
    
   

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
}
