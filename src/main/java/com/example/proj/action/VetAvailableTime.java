package com.example.proj.action;

import com.opensymphony.xwork2.ActionSupport;

public class VetAvailableTime extends ActionSupport{
    private String accountId;

    public String execute() throws Exception{
        return SUCCESS;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
