package com.mqtt.andisbank_mqtt.model;
import java.util.Objects;

public class HasCard{
    int id;
    String idCard;
    int idPerson;

    public HasCard(String idCard, int idPerson) {
        this.idCard = idCard;
        this.idPerson = idPerson;   
    }

    public int getIdPerson() {
        return this.idPerson;
    }

    public String getIdCard(){
        return this.idCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasCard hasCard = (HasCard) o;
        return idPerson == hasCard.idPerson && idCard.equals(hasCard.idCard);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idCard, idPerson);
    }

    @Override
    public String toString() {
        return "HasCard [id=" + id + ", idCard=" + idCard + ", idPerson=" + idPerson + ", getIdPerson()="
                + getIdPerson() + ", getIdCard()=" + getIdCard() + ", hashCode()=" + hashCode() + ", getClass()="
                + getClass() + ", toString()=" + super.toString() + "]";
    }

    
}
    