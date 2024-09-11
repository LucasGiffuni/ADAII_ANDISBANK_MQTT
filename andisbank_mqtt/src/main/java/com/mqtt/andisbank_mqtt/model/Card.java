package com.mqtt.andisbank_mqtt.model;

public class Card{
    String id;
    int type;
    public int max_credit;    

    public Card(String id, int type, int max_credit) {
        this.id = id;
        this.type = type;
        this.max_credit = max_credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMax_credit() {
        return max_credit;
    }

    public void setMax_credit(int max_credit) {
        this.max_credit = max_credit;
    }

    @Override
    public String toString() {
        return "Card [id=" + id + ", type=" + type + ", max_credit=" + max_credit + ", getId()=" + getId()
                + ", getType()=" + getType() + ", getMax_credit()=" + getMax_credit() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

    
    
}