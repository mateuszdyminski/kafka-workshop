# Log Compaction

## Create Topic with Compaction configuration

```sh
kubectl apply -n kafka -f resources/kafka-topic-compacted.yaml
```

## Check topic configuration

```sh
kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic kafka-topic-compacted
```

## Run consumer

```sh
kubectl -n kafka run kafka-consumer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --topic kafka-topic-compacted --from-beginning --property "print.timestamp=true" --property "print.key=true"
```

## Run Producer

```sh
kubectl -n kafka run kafka-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-producer.sh --broker-list kafka-cluster-kafka-bootstrap:9092 --topic kafka-topic-compacted --property "parse.key=true" --property "key.separator=:"
```

Write some records with Key and Value

Send `1:initial value`
Send `2:another value`
Send `3:final value`

## Observe consumer

## Overwrite existing Key

Write some records with Key and Value

Send `1:new initial value`
Send `2:new another value`

## Observe consumer

## Run Consumer once again from the very beggining to check number of records in log

Stop consumer and run it again with --from-beggining flag

```sh
kubectl -n kafka run kafka-consumer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --topic kafka-topic-compacted --from-beginning --property "print.timestamp=true" --property "print.key=true"
```
