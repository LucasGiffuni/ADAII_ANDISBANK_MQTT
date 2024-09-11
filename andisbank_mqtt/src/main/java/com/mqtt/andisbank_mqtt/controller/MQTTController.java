package com.mqtt.andisbank_mqtt.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mqtt.andisbank_mqtt.AndisbankMqttApplication;
import com.mqtt.andisbank_mqtt.configuration.MQTTGateway;
import com.mqtt.andisbank_mqtt.model.Card;
import com.mqtt.andisbank_mqtt.model.HasCard;
import com.mqtt.andisbank_mqtt.model.responses.CreateDebitCardResponse;
import com.mqtt.andisbank_mqtt.model.responses.IncreaseLimitCardResponse;
import com.mqtt.andisbank_mqtt.service.ICardService;

@Controller
public class MQTTController {

    private static final Logger logger = LoggerFactory.getLogger(AndisbankMqttApplication.class);

    @Autowired
    MQTTGateway gateway;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/sendMqttMessage")
    public ResponseEntity<?> sendMessageToTopic(@RequestParam String message, @RequestParam String topic) {
        try {
            logger.info("message" + message);
            logger.info("topic" + topic);
            gateway.senToMqtt(message, topic);
            return ResponseEntity.ok("Success");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok("fail");
        }
    }

    @Autowired
    ICardService cardService;

    @PostMapping("/{customer_account}/createDebitCard")
    public ResponseEntity<?> createDebitCard(
            @PathVariable(name = "customer_account", required = true) String customer_account) {
        try {
            CreateDebitCardResponse response = cardService.createDebitCard(customer_account);

            gateway.senToMqtt(response.getCard().toString(), "createDebitCard");
            return ResponseEntity.ok(response.getCard().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok("fail");
        }
    }

    @GetMapping("/getAllCardsUsers")
    public List<HasCard> getAll() {
        try {
            List<HasCard> response = cardService.getAllCards();

            gateway.senToMqtt(response.toString(), "getAllCardsUsers");
            return (List<HasCard>) ResponseEntity.ok("Success");
        } catch (Exception ex) {
            ex.printStackTrace();
            return (List<HasCard>) ResponseEntity.ok("fail");
        }
    }

    @GetMapping("/getCardsFromUser/{userId}")
    public List<String> getCardFromUser(
            @PathVariable(name = "userId", required = true) int userId) {
        try {
            List<String> response = cardService.getCustomerCard(userId);
            String[] arrayDeStrings = response.toArray(new String[0]);

            StringBuilder str = new StringBuilder();

            for (String string : arrayDeStrings) {
                str.append(string);
                System.out.println(string);
            }

            System.out.println("MESSAGE: " + str.toString());
            gateway.senToMqtt(str.toString(), "getCardsFromUser");
            return response;
        } catch (Exception ex) {
            return null;
        }

    }

    @PatchMapping("/increase/{id}")
    public IncreaseLimitCardResponse increaseCredit(
            @RequestBody Card increasedCard, @PathVariable("id") Integer id) {
        try {
            IncreaseLimitCardResponse response = cardService.increaseCard(id, increasedCard.getId(),
                    increasedCard.max_credit);
            gateway.senToMqtt(response.getCard().toString(), "increaseCredit");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @DeleteMapping("/deleteCardUser/{idUser}/{idCard}")
    public HasCard cancelActiveCard(@PathVariable("idUser") int idUser, @PathVariable("idCard") String idCard) {

        try {
            HasCard response = cardService.cancelActiveCard(idUser, idCard);
            gateway.senToMqtt(response.toString(), "cancelActiveCard");
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}