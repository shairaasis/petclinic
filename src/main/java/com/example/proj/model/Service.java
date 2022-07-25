package com.example.proj.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Service {
    public static Service serviceBean;
    private String name;
    private String price;
    private String type;
    private String duration;
    private boolean enabled = true;
    public Service() {}

    public Service(String name, String price, String type,String duration, boolean enabled) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.duration = duration;
        this.enabled = enabled;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Service rhs = (Service) obj;
        return new EqualsBuilder()
                .append(this.name, rhs.name)
                .append(this.price, rhs.price)
                .append(this.type, rhs.type)
                .append(this.duration, rhs.duration)
                .append(this.enabled, rhs.enabled)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() 
                .append(name)
                .append(price)
                .append(type)
                .append(duration)
                .append(enabled)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", name)
                .append("password", price)
                .append("firstName", duration)
                .append("lastName", enabled)
                .toString();
    }

    public static Service getServiceBean() {
        return serviceBean;
    }

    public static void setServiceBean(Service serviceBean) {
        Service.serviceBean = serviceBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    

}
