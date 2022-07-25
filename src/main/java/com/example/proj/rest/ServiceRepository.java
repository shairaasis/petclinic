package com.example.proj.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.proj.model.Service;
import com.example.proj.model.ServiceResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceRepository {
    private String error = "Random";
    private static Map<String, Service> map = new HashMap<String, Service>();
    Service serviceBean = new Service();
    ServiceResponse serviceResponse;
    String token = "7lgqAPeqka6St2GNCDosVRc2vt7A3Q240JGwdu0H";
    public ServiceRepository(){
      
    }

    /*POST SAVE TO DB */
    public Map<String, Service> saveService(Service service) throws Exception{
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
        return map;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static Map<String, Service> getMap() {
        return map;
    }

    public static void setMap(Map<String, Service> map) {
        ServiceRepository.map = map;
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
