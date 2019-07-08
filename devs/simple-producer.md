# Exercise: Create Simple Producer

## Procedure

1. Read the javadoc of [KafkaProducer](https://kafka.apache.org/22/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html) to know how to use the Producer API (to send messages to Kafka)
2. Go to `simple-producer` directory
3. Create kafka cluster with at least 3 brokers

```sh
kubectl apply -f kafka.yaml -n kafka
```

4. Create Kafka Topic with 3 partitions and replication factor set to 3

```sh
kubectl apply -f topic.yaml -n kafka
```

5. Edit `pom.xml` file in line:73 to configure DockerHub properly
6. Edit `simple-producer/src/main/java/com/kafka/workshop/producer/com.kafka.workshop.transactions.KafkaSimpleProducer.java` file to Produce only 1 record instead of producing them in loop.
7. Don't forget to close the producer.
8. Analyze `pom.xml` - how build and creating docker image is done
9. Analyze or change `Dockerfile`
10. Build Project `mvn clean install`
11. Push image to dockerhub: `mvn dockerfile:push`
12. Create namespace for producers

```sh
kubectl create namespace kafka-producers
```

11. Deploy our very first producer

```sh
kubectl apply -f simple-producer-deployment.yaml
```

12. Or run it without creating deployment:

```sh
kubectl -n kafka-producers run kafka-simple-producer -ti --image=index.docker.io/mateuszdyminski/kafka-simple-producer:1.4 --rm=true --restart=Never --env=KAFKA_BOOTSTRAP_SERVERS=kafka-cluster-kafka-bootstrap.kafka:9092
```
