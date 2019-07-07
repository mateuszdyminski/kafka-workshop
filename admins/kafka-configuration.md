# Kafka configuration

## Check list of existing topics in Cluster

```sh
$ kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --list
test-topic
kafka-topic
```

do the same with `kubectl`

```sh
$ kubectl get KafkaTopic -n kafka
NAME          PARTITIONS   REPLICATION FACTOR
kafka-topic   3            1
test-topic    1            1
```

## Let's change Kafka configuration

Remove comment in line 19 in file `resources/kafka-simple-single.yaml`

Apply changes in Kafka cluster:

```sh
kubectl apply -f resources/kafka-simple-single.yaml
```

## See in realtime how Kafka cluster behaves during update

```sh
watch kubectl get pods -n kafka
```

## Let's write some message to non-existing topic

```sh
kubectl -n kafka run kafka-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-producer.sh --broker-list kafka-cluster-kafka-bootstrap:9092 --topic test-topic-2
```

## Java Stack Trace should appear
