# Problems

## Delete namespace `monitoring`

```sh
kubectl delete namespace monitoring
```

## Throubleshoot problems with creating topic

### Create misconfigured topic

```sh
kubectl apply -f resources/kafka-topic-wrong-config.yaml
```

### Check if it's created

```sh
$ kubectl get KafkaTopic -n kafka
NAME                                                          PARTITIONS   REPLICATION FACTOR
consumer-offsets---84e7a678d08f4bd226872e5cdd4eb527fadc1c6a   50           1
kafka-topic                                                   3            1
kafka-topic-compacted                                         3            1
kafka-topic-wrong                                             3            1
```

### Check with `kafka-topics.sh`

```sh
kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --list
```

### Check logs of entity operator

```sh
kubectl logs kafka-cluster-entity-operator-776f6cc6c7-fq4bt topic-operator -n kafka
```

## Problem with Too big record

### Create topic with very small max message size

```sh
kubectl apply -f resources/kafka-topic-small-msg.yaml
```

### Check it with `kafka-topics.sh`

```sh
kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --list
```

### Run perf tool

```sh
kubectl -n kafka run kafka-perf-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-producer-perf-test.sh --topic kafka-topic-small-msg --num-records 1 --record-size 100 --throughput 1000 --producer-props bootstrap.servers=kafka-cluster-kafka-bootstrap:9092
```

### Check logs from all Kafka Broker pods

```sh
kubectl logs -f -n kafka -l strimzi.io/cluster=kafka-cluster --all-containers --max-log-requests=10
```
