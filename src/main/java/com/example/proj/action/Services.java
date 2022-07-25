package com.example.proj.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.stream.Collectors;

import com.example.proj.model.Service;
import com.example.proj.model.ServiceResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

public class Services extends ActionSupport{

    Service serviceBean = new Service();
    ServiceResponse serviceResponse;
    String token = "7lgqAPeqka6St2GNCDosVRc2vt7A3Q240JGwdu0H";

    public String execute() throws Exception {
    String format = "yyyy-MM-dd";
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    String currentDate = dateFormat.format(calendar.getTime());
    try {

        URL url = new URL("https://eu-central-1.hapio.net/v1/services/name="+ serviceBean.getName() +"/?price="+serviceBean.getPrice()+"/?type=fixed/?duration="+ serviceBean.getDuration());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer {token}");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output;
        output =  br.lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        serviceResponse = mapper.readValue(output, ServiceResponse.class);
        conn.disconnect();

    } catch (Exception e) {
        e.printStackTrace();
    }
    return SUCCESS;
    }

    public Service getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(Service serviceBean) {
        this.serviceBean = serviceBean;
    }

    public ServiceResponse getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(ServiceResponse serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
    
}
