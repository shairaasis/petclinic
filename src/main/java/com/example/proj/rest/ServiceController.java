package com.example.proj.rest;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.example.proj.model.Service;
import com.opensymphony.xwork2.ModelDriven;

public class ServiceController implements ModelDriven<Object>{
    private static final long serialVersionUID = 1L; 
    private Object model;
    String id;
    Service service = new Service();
    private ServiceRepository serviceRepository = new ServiceRepository();
    private static Map<String, Service> map;
    {
        // // try {
        // //     // map = serviceRepository.findAllservice();
        // // } catch (SQLException e) {
        // //     e.printStackTrace();
        // }
       
    }
    //Return all user records
    public HttpHeaders index() {
        model = map;
        return new DefaultHttpHeaders("index").disableCaching();
    } 

    @Override
    public Object getModel() {
		return (model != null ? model : service);
	}

    //GET accounts/id/ by id
    public String getId() {

        return id;
	}

    
    public void setModel(Object model) {
        this.model = model;
    }
    public Service getService() {
        return service;
    }
    public void setAccount(Service service) {
        this.service = service;
    }
    public ServiceRepository getServiceRepository() {
        return serviceRepository;
    }
    public void setServiceRepository(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    /*POST method, insert data into database*/
    public HttpHeaders saveService() throws Exception {
        model = serviceRepository.saveService(service); 
        System.out.println(service.toString());
        return new DefaultHttpHeaders("saveService");
    }
}
