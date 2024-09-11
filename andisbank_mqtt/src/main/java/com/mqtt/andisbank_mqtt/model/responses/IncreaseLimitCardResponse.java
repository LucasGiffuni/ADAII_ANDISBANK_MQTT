package com.mqtt.andisbank_mqtt.model.responses;

import com.mqtt.andisbank_mqtt.model.Card;

public class IncreaseLimitCardResponse {

    private Card card;
    private CommonResponse commonResponse;
    
    public Card getCard() {
        return card;
    }
    public void setCard(Card card) {
        this.card = card;
    }
    public CommonResponse getCommonResponse() {
        return commonResponse;
    }
    public void setCommonResponse(CommonResponse commonResponse) {
        this.commonResponse = commonResponse;
    }

  
    

}
