package com.example.proj.action;

import com.example.proj.model.Appointment;
import com.opensymphony.xwork2.ActionSupport;

public class Reschedule extends ActionSupport {
    private int accountId;
    private int appointmentId;
    private Appointment appointmentBean = new Appointment();

    public String execute() throws Exception{
        appointmentBean.setAppointmentId(appointmentId);
        
        
        return SUCCESS;
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

    public Appointment getAppointmentBean() {
        return appointmentBean;
    }

    public void setAppointmentBean(Appointment appointmentBean) {
        this.appointmentBean = appointmentBean;
    }

    
    
}
