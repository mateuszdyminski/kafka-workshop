# Exercise: Create Simple Consumer

## Procedure

1. Read the javadoc of [KafkaConsumer](https://kafka.apache.org/22/javadoc/org/apache/kafka/clients/consumer/KafkaConsumer.html) to know how to use the Consumer API (to consume msg from Kafka)
2. Go to `simple-consumer` directory
3. Create kafka cluster with at least 3 brokers

```sh
kubectl apply -f kafka.yaml -n kafka
```

4. Create Kafka Topic with 3 partitions and replication factor set to 3

```sh
kubectl apply -f topic.yaml -n kafka
```

5. Edit `pom.xml` file in line:73 to configure DockerHub properly
6. Edit `simple-consumer/src/main/java/com/kafka/workshop/consumer/KafkaSimpleConsumer.java` file to Consumer only 1 record instead of consuming them in loop.
7. Don't forget to close the consumer.
8. Analyze `pom.xml` - how build and creating docker image is done
9. Analyze or change `Dockerfile`
10. Build Project `mvn clean install`
11. Push image to dockerhub: `mvn dockerfile:push`
12. Create namespace for consumers

```sh
kubectl create namespace kafka-consumers
```

11. Deploy our very first producer

```sh
kubectl apply -f simple-consumer-deployment.yaml
```

12. Or run it without creating deployment:

```sh
kubectl -n kafka-consumers run kafka-simple-consumer -ti --image=index.docker.io/mateuszdyminski/kafka-simple-consumer:1.3 --rm=true --restart=Never --env=KAFKA_BOOTSTRAP_SERVERS=kafka-cluster-kafka-bootstrap.kafka:9092
```
