package com.mqtt.andisbank_mqtt.model;

import java.util.Date;

public class Transfer{
    int id;
    Date date;
    int transferUser;
    int benefitedUser;
    int amount;
    String currency;


    public Transfer(int id, Date date, int transferUser, int benefitedUser, int amount, String currency) {
        this.id = id;
        this.date = date;
        this.transferUser = transferUser;
        this.benefitedUser = benefitedUser;
        this.amount = amount;
        this.currency = currency;   
    }
}