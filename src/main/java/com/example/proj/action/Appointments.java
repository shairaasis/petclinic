package com.example.proj.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Statement;
import java.time.LocalDate;

import com.example.proj.model.Account;
import com.example.proj.model.Appointment;
import com.example.proj.model.Pet;
import com.example.proj.model.PetService;
import com.opensymphony.xwork2.ActionSupport;

public class Appointments extends ActionSupport{
    LocalDate now = LocalDate.now(); 
    private String numOfAppointments;
    private static Appointment appointmentBean = new Appointment();
    ArrayList<Appointment> appointments = new ArrayList<Appointment>();
    ArrayList<Appointment> vetAppointments = new ArrayList<Appointment>();
    Appointment appointment;
    Appointment vetAppointment;
    private String createAppointment = "false";
    private String timeIsAvailable = "no";
    private String concat = "', '";
    private int accountId;
    private String error;
    public ArrayList<String> listOfServices = new ArrayList<String>();
    ArrayList<PetService> services = new ArrayList<PetService>();
    private PetService serviceBean;
    public ArrayList<String> listOfCustomers = new ArrayList<String>();
    ArrayList<Account> customers = new ArrayList<Account>();
    private Account accountBean;
    public ArrayList<String> listOfVeterinarians = new ArrayList<String>();
    ArrayList<Account> veterinarians = new ArrayList<Account>();
    public ArrayList<String> listOfPets = new ArrayList<String>();
    ArrayList<Pet> pets = new ArrayList<Pet>();
    private Account vetBean;
    private String appointmentStatus;
    private HashMap<Integer, String> listOfTimes = new HashMap<Integer, String>();
    public HashMap<String, Integer> veterinarianId = new HashMap<String, Integer>();
    public HashMap<String, Integer> timeId = new HashMap<String, Integer>();
    public String execute() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, CONCAT(customer_name.first_name,"+ getConcat() +",customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,"+getConcat()+", veterinarian_name.last_name) AS veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id where status='pending'";
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
                    appointment.setStatus(rs.getString(7));
                    appointment.setSchedule(appointment.getSchedule().substring(0, 16));
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

    

    public String vetPendingAppointments() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, CONCAT(customer_name.first_name,"+ getConcat() +",customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,"+getConcat()+", veterinarian_name.last_name) AS veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id where status='pending' and veterinarian_id = '"+getAccountId()+"' order by `schedule` ASC";
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
                    appointment.setStatus(rs.getString(7));
                    appointment.setSchedule(appointment.getSchedule().substring(0, 16));
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
    public String vetApprovedAppointments() throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, CONCAT(customer_name.first_name,"+ getConcat() +",customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,"+getConcat()+", veterinarian_name.last_name) AS veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id where status='approved' and veterinarian_id = '"+getAccountId()+"' order by `schedule` ASC";
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
                    appointment.setStatus(rs.getString(7));
                    appointment.setSchedule(appointment.getSchedule().substring(0, 16));
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
    public String clickCreateAppointment() throws Exception{
        createAppointment = "true";
        return SUCCESS;
    }
    public String customerAppointments() throws Exception {
        // getTimetable();
        listOfServices();
        listOfVeterinarians();
        listOfPets();
        // listOfVeterinarians();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT appointments.*, DATE_FORMAT(schedule, '%a, %b %d %Y') AS schedule, CONCAT(customer_name.first_name,' ',customer_name.last_name) AS customer,CONCAT(veterinarian_name.first_name,' ', veterinarian_name.last_name) AS Veterinarian, pets.pet_name, services.service FROM appointments INNER JOIN accounts AS customer_name ON appointments.customer_id=customer_name.account_id INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id INNER JOIN pets ON appointments.pet_id = pets.pet_id INNER JOIN services ON appointments.service = services.service_id where customer_id ="+getAccountId()+ " and status = 'pending'";
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
                if(appointment == null){
                    numOfAppointments = "No approved Appointments. Wait for pending appointments to be approved.";
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }

    private String getTimetable() {
        listOfTimes.clear();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * from time";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    listOfTimes.put(rs.getInt(1), rs.getString(2));
                }
            } 
         } catch (Exception e) {
            error = e.toString();
            return error;
         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
         return SUCCESS;
    }
    



    public String listOfPets() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * from pets where owner_id ="+getAccountId();
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Pet pet=new Pet();
                    pet.setPetId(rs.getInt(1));
                    pet.setOwnerId(rs.getInt(2));
                    pet.setHealthRecordId(rs.getInt(3));
                    pet.setPetName(rs.getString(4));
                    pet.setDateOfBirth(rs.getString(5));
                    pet.setBreed(rs.getString(6));
                    pet.setCoatColor(rs.getString(7));
                    pets.add(pet);
                    listOfPets.add(pet.getPetName());
                }
            } 
         } catch (Exception e) {
            error = e.toString();
            return error;
         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }
    public String getTimeAvailable() throws Exception{
        appointmentBean = getAppointmentBean();
        accountId = appointmentBean.getClientId();
        appointmentBean.setDateOfAppointment(appointmentBean.getDateOfAppointment().substring(0, 10));
        getTimetable();
        listOfServices();
        listOfVeterinarians();
        listOfPets();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");
            if (connection != null) {
                String sql = "select appointments.*, time.time, CONCAT(veterinarian_name.first_name, ' ', veterinarian_name.last_name) AS Veterinarian from appointments INNER JOIN accounts AS veterinarian_name ON appointments.veterinarian_id = veterinarian_name.account_id inner join time ON appointments.timeID = time.timeID where schedule ='"+ appointmentBean.getDateOfAppointment() +"' and veterinarian_id =" +veterinarianId.get(appointmentBean.getVeterinarian());
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Appointment vetAppointment=new Appointment();
                    vetAppointment.setAppointmentId(rs.getInt(1));
                    vetAppointment.setClientId(rs.getInt(2));
                    vetAppointment.setPetId(rs.getInt(3));
                    vetAppointment.setVeterinarianId(rs.getInt(4));
                    vetAppointment.setServiceId(rs.getInt(5));
                    vetAppointment.setSchedule(rs.getString(6));
                    vetAppointment.setTimeOfAppointment((rs.getInt(7)));
                    vetAppointment.setStatus(rs.getString(8));
                    vetAppointment.setVeterinarian(rs.getString(10));
                    vetAppointments.add(vetAppointment);
                    listOfTimes.remove(vetAppointment.getTimeOfAppointment());
                    
                   
                }
                timeIsAvailable = "yes";
            } 
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }

         return SUCCESS;
    }
    public String createAppointment() throws Exception{
        appointmentBean = getAppointmentBean();
        if(saveAppointment(appointmentBean) == true) {
            return "success";
        } else {
            return error;
        }
    }
    // public Appointment getAppointmentData(Appointment appointmentBean)throws SQLException{
    //     Connection connection = null;
    //     PreparedStatement preparedStatement = null;
    //     try {
    //         String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
    //         Class.forName("com.mysql.jdbc.Driver");
    //         connection = DriverManager.getConnection(URL, "root", "password");

    //         if (connection != null) {
    //             String sql = "select accounts.account_id, CONCAT(accounts.first_name, ' ', accounts.last_name) AS Veterinarian, services.service_id, services.service, pets.pet_id, pets.pet_name FROM accounts, services, pets where CONCAT(accounts.first_name, ' ', accounts.last_name) ="+ appointmentBean.getVeterinarian()+" and service ="+appointmentBean.getService()+" and pet_name =" +appointmentBean.getPetName();
    //             preparedStatement = connection.prepareStatement(sql);
    //             ResultSet rs= preparedStatement.executeQuery();

    //             while(rs.next()){  
    //                 appointmentBean.setVeterinarianId(rs.getInt(1));
    //                 appointmentBean.setVeterinarian(rs.getString(2));
    //                 appointmentBean.setServiceId(rs.getInt(3));
    //                 appointmentBean.setService(rs.getString(4));
    //                 appointmentBean.setPetId(rs.getInt(5));
    //                 appointmentBean.setPetName(rs.getString(6));
    //             }
    //             System.out.println("\n\n======get appointment data method==\n\n");
    //             System.out.println("=======\n\n\n"+appointmentBean.getDateOfAppointment()+"\n\n\n=========\n"+appointmentBean.getTimeOfAppointment()+"\n\n");
    //             System.out.println("===\n\n"+appointmentBean.getServiceId()+" "+ appointmentBean.getPetId()+"=======\n\n\n");
        
    //         } 
    //      } catch (Exception e) {

    //      } finally {
    //         if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
    //         if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
    //      }

    //      return appointmentBean;
    // }
    public boolean saveAppointment(Appointment appointmentBean) throws Exception {
        String status = "pending";
        Connection connection = null;
        Statement statement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                statement = connection.createStatement();
                String sql = "INSERT INTO appointments (CUSTOMER_ID, PET_ID, VETERINARIAN_ID,SERVICE,SCHEDULE, timeID, status) VALUES((select account_id from accounts where account_id ='"+appointmentBean.getClientId()+"'),(select pet_id from pets where pet_name = '"+appointmentBean.getPetName()+"' AND owner_id = '"+appointmentBean.getClientId()+"'),(select account_id from accounts where CONCAT(accounts.first_name, ' ', accounts.last_name) = '"+ appointmentBean.getVeterinarian()+"'),(select service_id from services where service = '"+appointmentBean.getService()+"'),'"+appointmentBean.getDateOfAppointment()+"', '"+appointmentBean.getTimeOfAppointment()+"','"+status+"')";
                statement.executeUpdate(sql);
                appointmentStatus = "Appointment is scheduled. Please wait for approval.";
                return true;
            } else {
                error = "DB connection failed";
                return false;
            }
         } catch (SQLException e) {
             error = e.toString();
             return false;  
         } finally {
            if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
    }
    public String listOfServices() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * FROM services";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    PetService service=new PetService();
                    service.setServiceId(rs.getInt(1));
                    service.setService(rs.getString(2));
                    service.setFee(rs.getInt(3));
                    services.add(service);
                    listOfServices.add(service.getService());
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
        return SUCCESS;
    }
    public String listOfCustomers() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * FROM accounts where account_type_id = 3";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Account customer=new Account();
                    customer.setAccountId(rs.getInt(1));
                    customer.setUsername(rs.getString(3));
                    customer.setLastName(rs.getString(6));   
                    customer.setFirstName(rs.getString(5)); 
                    customer.setEmail(rs.getString(8));
                    customer.setAddress(rs.getString(7));
                    customer.setContactNo(rs.getString(9)); 
                    customers.add(customer); 
                    listOfCustomers.add(""+customer.getFirstName()+" "+ customer.getLastName());
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
        return SUCCESS;
    }
    public String listOfVeterinarians() throws Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/petclinic?useTimezone=true&serverTimezone=UTC";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "password");

            if (connection != null) {
                String sql = "SELECT * FROM accounts where account_type_id = 2";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){  
                    Account veterinarian=new Account();
                    veterinarian.setAccountId(rs.getInt(1));
                    veterinarian.setUsername(rs.getString(3));
                    veterinarian.setLastName(rs.getString(6));   
                    veterinarian.setFirstName(rs.getString(5)); 
                    veterinarian.setEmail(rs.getString(8));
                    veterinarian.setAddress(rs.getString(7));
                    veterinarian.setContactNo(rs.getString(9)); 
                    veterinarians.add(veterinarian); 
                    listOfVeterinarians.add(""+veterinarian.getFirstName()+" "+ veterinarian.getLastName());
                    veterinarianId.put(""+veterinarian.getFirstName()+" "+ veterinarian.getLastName(),veterinarian.getAccountId());
                }
            } 
         } catch (Exception e) {

         } finally {
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
         }
        return SUCCESS;
    }

    public String vetAvailableTime(){
        return SUCCESS;
    }
    

    public ArrayList<String> getListOfPets() {
        return listOfPets;
    }

    public void setListOfPets(ArrayList<String> listOfPets) {
        this.listOfPets = listOfPets;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public ArrayList<String> getListOfVeterinarians() {
        return listOfVeterinarians;
    }

    public void setListOfVeterinarians(ArrayList<String> listOfVeterinarians) {
        this.listOfVeterinarians = listOfVeterinarians;
    }

    public ArrayList<Account> getVeterinarians() {
        return veterinarians;
    }

    public void setVeterinarians(ArrayList<Account> veterinarians) {
        this.veterinarians = veterinarians;
    }

    public Account getVetBean() {
        return vetBean;
    }

    public void setVetBean(Account vetBean) {
        this.vetBean = vetBean;
    }

    public ArrayList<String> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(ArrayList<String> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }

    public ArrayList<Account> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Account> customers) {
        this.customers = customers;
    }

    public Account getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(Account accountBean) {
        this.accountBean = accountBean;
    }

    public ArrayList<String> getListOfServices() {
        return listOfServices;
    }

    public void setListOfServices(ArrayList<String> listOfServices) {
        this.listOfServices = listOfServices;
    }

    public ArrayList<PetService> getServices() {
        return services;
    }

    public void setServices(ArrayList<PetService> services) {
        this.services = services;
    }

    public PetService getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(PetService serviceBean) {
        this.serviceBean = serviceBean;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Appointment getAppointmentBean() {
        return appointmentBean;
    }

    public static void setAppointmentBean(Appointment appointment) {
        Appointments.appointmentBean = appointment;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getNumOfAppointments() {
        return numOfAppointments;
    }

    public void setNumOfAppointments(String numOfAppointments) {
        this.numOfAppointments = numOfAppointments;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
    
    public HashMap<String, Integer> getVeterinarianId() {
        return veterinarianId;
    }



    public void setVeterinarianId(HashMap<String, Integer> veterinarianId) {
        this.veterinarianId = veterinarianId;
    }



    public HashMap<String, Integer> getTimeId() {
        return timeId;
    }



    public void setTimeId(HashMap<String, Integer> timeId) {
        this.timeId = timeId;
    }



    public LocalDate getNow() {
        return now;
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }

    

    public Appointment getVetAppointment() {
        return vetAppointment;
    }



    public void setVetAppointment(Appointment vetAppointment) {
        this.vetAppointment = vetAppointment;
    }





    public String getCreateAppointment() {
        return createAppointment;
    }



    public void setCreateAppointment(String createAppointment) {
        this.createAppointment = createAppointment;
    }



    public String getTimeIsAvailable() {
        return timeIsAvailable;
    }



    public void setTimeIsAvailable(String timeIsAvailable) {
        this.timeIsAvailable = timeIsAvailable;
    }



    public ArrayList<Appointment> getVetAppointments() {
        return vetAppointments;
    }



    public void setVetAppointments(ArrayList<Appointment> vetAppointments) {
        this.vetAppointments = vetAppointments;
    }



    public HashMap<Integer, String> getListOfTimes() {
        return listOfTimes;
    }



    public void setListOfTimes(HashMap<Integer, String> listOfTimes) {
        this.listOfTimes = listOfTimes;
    }


    public HashMap<Integer, String> getListOfTimeAvailable() {
        return listOfTimes;
    }


}
