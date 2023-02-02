package com.amigoscode.iotpoc.controller;

import lombok.Value;

@Value
class MqqtMessage {
    String topic;
    String message;
}
