package com.mqtt.andisbank_mqtt.service;


import java.util.List;

import com.mqtt.andisbank_mqtt.model.HasCard;
import com.mqtt.andisbank_mqtt.model.responses.CreateDebitCardResponse;
import com.mqtt.andisbank_mqtt.model.responses.IncreaseLimitCardResponse;

public interface ICardService {
    

    public List<HasCard> getAllCards();

    public List<String> getCustomerCard(int customer_id);

    public CreateDebitCardResponse createDebitCard(String customer_id);

    public IncreaseLimitCardResponse increaseCard(int customer_id, String cardId, int newLimit);

    public HasCard cancelActiveCard(int idUser, String idCard);

}
