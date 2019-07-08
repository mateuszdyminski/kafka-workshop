# Kafka Backup

## Install Backup Kafka Cluster in the same namespace

```sh
kubectl apply -f resources/kafka-backup-cluster.yaml
```

Watch until is up and running

```sh
watch kubectl get pods -n kafka-backup
```

## Create MirrorMaker to send records to backup cluster

```sh
kubectl apply -f resources/kafka-mirror-maker.yaml
```

## Verification

Run producer on first cluster

```sh
kubectl -n kafka run kafka-perf-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-producer-perf-test.sh --topic kafka-topic --num-records 10000 --record-size 100 --throughput 1000 --producer-props bootstrap.servers=kafka-cluster-kafka-bootstrap:9092
```

Run consumer on backup cluster

```sh
kubectl -n kafka run kafka-consumer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server kafka-cluster-backup-kafka-bootstrap:9092 --topic kafka-topic --from-beginning --property "print.timestamp=true" --property "print.key=true"
```

Check topics on backup cluster

```sh
kubectl -n kafka exec -ti kafka-cluster-backup-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --list
```
