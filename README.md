How to connect Mosquitto MQTT broker running in the Docker container  with Spring Boot backend
=================

Mosquitto is an open source implementation of a server for version 5.0, 3.1.1,
and 3.1 of the MQTT protocol. It also includes a C and C++ client library, and
the `mosquitto_pub` and `mosquitto_sub` utilities for publishing and
subscribing.

## run Mosquitto MQTT broker in the Docker container

- create docker-compose.yml
````
version: "3.8"

services:
   mqtt:
    container_name: mqtt
    image: eclipse-mosquitto:2.0.15
    restart: always
    volumes:
      - ./mosquitto/config:/mosquitto/config
      - ./mosquitto/data:/mosquitto/data
      - ./mosquitto/log:/mosquitto/log
    ports:
      - 1883:1883
      - 9001:9001

volumes:
  data: {}
````

- in the terminal invoke commands
````
docker compose up
````

- create mosquitto.conf file in ./mosquitto/config directory

- in terminal invoke commands
```
> docker exec -it mqtt sh
/ # mosquitto_passwd -c /mosquitto/config/pwfile admin
Password:
Reenter password:
/ # exit
```

- insert to mosquitto.conf file in ./mosquitto/config directory
```
password_file /mosquitto/config/pwfile
```

- restart the Docker container

- open new instance of terminal and invoke commands
```
> docker exec -it mqtt sh
/ # mosquitto_sub -t myTopic
```

- open new instance of terminal and invoke commands
```
> docker exec -it mqtt sh
/ # mosquitto_pub -t myTopic -m "Hello World"
```

- modify the mosquitto.conf file in ./mosquitto/config directory
```

listener 1883
password_file /mosquitto/config/pwfile
allow_anonymous false

```

- restart the Docker container

- now only authenticated users can use the broker

- in instance of terminal and invoke commands
```
> docker exec -it mqtt sh
/ # mosquitto_sub -t myTopic -u admin -P qwerty
```

- in another instance of terminal and invoke commands
```
> docker exec -it mqtt sh
/ # mosquitto_pub -t myTopic -m "Hello World" -u admin -P qwerty
```

## create Spring Boot app

