package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

public class Cancel extends ActionSupport{
    private String accountId;
    private int appointmentId;
    private String appointmentStatus;

    // / method for delete the record
	public String execute() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "DELETE FROM appointments WHERE appointment_id ='"+getAppointmentId()+"'";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                appointmentStatus ="Appointment is cancelled.";
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
                String sql = "DELETE FROM appointments WHERE appointment_id ='"+getAppointmentId()+"'";
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
    

    
}
