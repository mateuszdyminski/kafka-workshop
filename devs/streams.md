# Exercise: Word Count with Record Stream with KStream

Develop a standalone Kafka Streams application that reads records in from a source and writes it out to a sink.

1. Use the high-level [Streams DSL](http://kafka.apache.org/22/documentation/streams/developer-guide/dsl-api.html)
2. Use [StreamsBuilder.stream](http://kafka.apache.org/22/javadoc/org/apache/kafka/streams/StreamsBuilder.html) to create a [KStream](http://kafka.apache.org/22/javadoc/org/apache/kafka/streams/kstream/KStream.html) to read records in
3. Use [KStream.to](http://kafka.apache.org/22/javadoc/org/apache/kafka/streams/kstream/KStream.html) to write records out

## Procedure

1. Create new module in maven
2. Create new maven module
3. Craete new yaml file with topic `kafka-input-topic`
4. Craete new yaml file with topic `kafka-output-topic`
5. Write `WordCount` class which reads messages from `kafka-input-topic` and count each word occurence and sends it to: `kafka-output-topic`
6. Run WordCount application:

```sh
kubectl -n kafka-producers run kafka-streams -ti --image=index.docker.io/mateuszdyminski/kafka-streams:1.0 --rm=true --restart=Never --env=KAFKA_BOOTSTRAP_SERVERS=kafka-cluster-kafka-bootstrap.kafka:9092
```

7. Open kafka producer:

```sh
kubectl -n kafka run kafka-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-producer.sh --broker-list kafka-cluster-kafka-bootstrap:9092 --topic kafka-input-topic
```

8. Open Kafka consumer:

```sh
kubectl -n kafka run kafka-streams-consumer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --topic kafka-output-topic --from-beginning --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```

9. Check how KStream works
