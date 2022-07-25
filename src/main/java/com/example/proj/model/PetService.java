package com.example.proj.model;

public class PetService {
    public static PetService serviceBean;
    private int serviceId;
    private String service;
    private int fee;

    public PetService() {}

    public PetService(int service_id, String service, int fee){
        this.serviceId =service_id;
        this.service = service;
        this.fee = fee;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public static PetService getServiceBean() {
        return serviceBean;
    }

    public static void setServiceBean(PetService service) {
        PetService.serviceBean = service;
    }
    
}
