package com.amigoscode.iotpoc.controller;

import com.amigoscode.iotpoc.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MqttController {

    @Qualifier("mqttGateway")
    @Autowired
    MqttGateway mqtGateway;
    @PostMapping("api/v1/sendMessage")
    public ResponseEntity<?> publish(@RequestBody MqqtMessage mqttMessage){

        if(mqttMessage.getTopic() != null && mqttMessage.getMessage() != null) {
            mqtGateway.senToMqtt(mqttMessage.getMessage(), mqttMessage.getTopic());
            return ResponseEntity.ok("Success");
        }

        return ResponseEntity.status(204).build();

    }

}
