package com.mqtt.andisbank_mqtt.service.impl;



import org.springframework.stereotype.Service;

import com.mqtt.andisbank_mqtt.model.Card;
import com.mqtt.andisbank_mqtt.model.HasCard;
import com.mqtt.andisbank_mqtt.model.Transfer;
import com.mqtt.andisbank_mqtt.model.responses.CommonResponse;
import com.mqtt.andisbank_mqtt.model.responses.CreateDebitCardResponse;
import com.mqtt.andisbank_mqtt.model.responses.IncreaseLimitCardResponse;
import com.mqtt.andisbank_mqtt.service.ICardService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements ICardService {


    List<Card> cards = new ArrayList<Card>();
    List<Transfer> transfer = new ArrayList<Transfer>();
    public List<HasCard> cardUser = new ArrayList<HasCard>();
   
    
    @Override
    public List<String> getCustomerCard(int customer_id) {
        List<String> customerCards = new ArrayList<String>();
        for (HasCard card : cardUser) {
            if (card.getIdPerson() == customer_id) {
                customerCards.add(card.getIdCard());
            }
        }
        return customerCards; 
        
    }

    @Override
    public List<HasCard> getAllCards() {
        return cardUser;        
    }

    private String randomNumber(){
        int numeroAleatoric = 1000000 + (int)(Math.random() * 9000000);
        return Integer.toString(numeroAleatoric);
    }
    
    @Override
    public CreateDebitCardResponse createDebitCard(String customer_id) {
        String cardId = randomNumber();
        Card newCard = new Card(cardId, 1, 20000);
        cards.add(newCard);        
        HasCard hasCard = new HasCard(cardId, Integer.parseInt(customer_id));
        cardUser.add(hasCard);
        System.out.println(cardUser);
        CreateDebitCardResponse response = new CreateDebitCardResponse();
        CommonResponse comResponse = new CommonResponse(200, "OK");
        response.setCommonResponse(comResponse);
        response.setCard(newCard);
        return response;
    }
    
    @Override
    public IncreaseLimitCardResponse increaseCard(int customer_id, String cardId, int newLimit) {
        IncreaseLimitCardResponse response = new IncreaseLimitCardResponse();        
        for(Card c : cards){
            if(c.getId().equals(cardId)){
                c.max_credit = newLimit;
                response.setCard(c);
            }
        }        
        CommonResponse comResponse = new CommonResponse(200, "OK");
        response.setCommonResponse(comResponse);        
        return response;
    }

    @Override
    public HasCard cancelActiveCard(int idUser, String idCard){        
        HasCard cardUserTemp = new HasCard(idCard, idUser);        
        cardUser.remove(cardUserTemp);                
        return cardUserTemp;
    }
    
   
}
