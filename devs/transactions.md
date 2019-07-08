# Exercise: Create Transactional Producer to write msg to 2 topics

## Procedure

1. Read the javadoc of [KafkaProducer](https://kafka.apache.org/22/javadoc/org/apache/kafka/clients/producer/KafkaProducer.html) to know how to use the Producer Transactions API
2. Create new maven module
3. Craete new yaml file with topic `kafka-topic-trans-1`
4. Craete new yaml file with topic `kafka-topic-fallback`
5. Write TransactionalProducer which tries to send message to 2 topics: `kafka-topic-trans-1` and `kafka-topic-trans-2`
6. If transaction fails try to send message to `kafka-topic-fallback` topic
7. Try to read data from 2 different topics: `kafka-topic-trans-1` and `kafka-topic-fallback` but for `kafka-topic-trans-1` use consumer with extra flag `--isolation-level=read_committed`

```sh
kubectl -n kafka run kafka-consumer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --topic kafka-topic-fallback --from-beginning --property "print.timestamp=true"
```

```sh
kubectl -n kafka run kafka-consumer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --topic kafka-topic-trans-1 --from-beginning --property "print.timestamp=true" --isolation-level=read_committed
```

```sh
kubectl -n kafka run kafka-consumer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --topic kafka-topic-trans-1 --from-beginning --property "print.timestamp=true"
```

8. Run TransactionalProducer and watch behaviour

```sh
kubectl -n kafka-producers run kafka-trans-producer -ti --image=index.docker.io/mateuszdyminski/kafka-trans-producer:1.8 --rm=true --restart=Never --env=KAFKA_BOOTSTRAP_SERVERS=kafka-cluster-kafka-bootstrap.kafka:9092
```

9. Create topic: `kafka-topic-trans-2` and run TransactionalProducer once again

```sh
kubectl -n kafka-producers run kafka-trans-producer -ti --image=index.docker.io/mateuszdyminski/kafka-trans-producer:1.8 --rm=true --restart=Never --env=KAFKA_BOOTSTRAP_SERVERS=kafka-cluster-kafka-bootstrap.kafka:9092
```
