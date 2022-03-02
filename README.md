# spring-kafka-docker

A JAVA spring boot application which contains two services:
#### Stock Reader Service: 
 - periodically, check an input folder for new csv files. 
 - Read every file and transform each record to an object.
 - Send each record to a Kafka topic as an object.

#### Stock Writer Service:
- read from KAFKA the stock info message.
- Save it to a local csv file that limited by predefined number of records on different folder

Kafka and zookeeper are running in a docker.

## Running

```bash

mvn spring-boot:run
