package com.example.proj.model;

public class Appointment {
    public static Appointment appointmentBean;
    private int appointmentId;
    private int clientId;
    private int petId;
    private int veterinarianId;
    private int serviceId;
    private String customer;
    private String veterinarian;
    private String petName;
    private String service;
    private String schedule;
    private String status;
    private String dateOfAppointment;
    private int timeOfAppointment;
    private String customerEmail;
    private String veterinarianEmail;

    public Appointment() {}

    public Appointment(String dateOfAppointment, String veterinarian){
        this.dateOfAppointment = dateOfAppointment;
        this.veterinarian = veterinarian;
    }

    public Appointment(int appointmentId, int clientId,int petId, int veterinarianId, int serviceId, String schedule, String customer, String veterinarian, String petName, String service, String status, String dateOfAppointment, int timeOfAppointment, String customerEmail, String veterinarianEmail){
        this.appointmentId = appointmentId;
        this.clientId = clientId;
        this.petId = petId;
        this.veterinarianId = veterinarianId;
        this.serviceId = serviceId;
        this.customer = customer;
        this.veterinarian = veterinarian;
        this.petName =petName;
        this.service=service;
        this.schedule = schedule;
        this.status = status;
        this.dateOfAppointment = dateOfAppointment;
        this.timeOfAppointment = timeOfAppointment;
        this.customerEmail = customerEmail;
        this.veterinarianEmail = veterinarianEmail;
    }

    public static Appointment getAppointmentBean() {
        return appointmentBean;
    }

    public static void setAppointmentBean(Appointment appointment) {
        Appointment.appointmentBean = appointment;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(int veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    // public String getTimeOfAppointment() {
    //     return timeOfAppointment;
    // }

    // public void setTimeOfAppointment(int i) {
    //     this.timeOfAppointment = i;
    // }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVeterinarianEmail() {
        return veterinarianEmail;
    }

    public void setVeterinarianEmail(String veterinarianEmail) {
        this.veterinarianEmail = veterinarianEmail;
    }

    public int getTimeOfAppointment() {
        return timeOfAppointment;
    }

    public void setTimeOfAppointment(int timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    
    
}
