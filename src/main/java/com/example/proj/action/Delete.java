package com.example.proj.action;

import java.sql.*;

import com.opensymphony.xwork2.ActionSupport;

public class Delete extends ActionSupport{
    
    private int accountId;
    private int petId, serviceId;
    private String status;
    private String error;
   

    // / method for delete the record
	public String execute() throws Exception{
        // HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        // accountId = Integer.parseInt(request.getParameter(accountId));
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "DELETE FROM accounts WHERE account_id ='"+accountId+"'";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                setStatus("Account deleted.");
                return SUCCESS;
            }
		} catch (Exception e) {

        } finally {
           if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
           if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return ERROR;
	}
    // / method for delete the record
	public String deletePet() throws SQLException{
        // HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        // accountId = Integer.parseInt(request.getParameter(accountId));
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "DELETE FROM pets WHERE pet_id ='"+getPetId()+"'";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                return SUCCESS;
            }
		} catch (Exception e) {
            error = e.toString();
        } finally {
           if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
           if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return ERROR;
	}

    // / method for delete the record
	public String deleteService() throws SQLException{
        // HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        // accountId = Integer.parseInt(request.getParameter(accountId));
        Connection connection = null;
        PreparedStatement preparedStatement = null;
		try {
			String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "DELETE FROM services WHERE service_id ='"+getServiceId()+"'";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                return SUCCESS;
            }
		} catch (Exception e) {
            error = e.toString();
        } finally {
           if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
           if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return ERROR;
	}


    public int getAccountId() {
        return accountId;
    }


    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


   
    public int getPetId() {
        return petId;
    }
    public void setPetId(int petId) {
        this.petId = petId;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public int getServiceId() {
        return serviceId;
    }
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    
}
