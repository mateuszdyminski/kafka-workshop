# Kafka for Administrators

Set of exercises how to install, run and manage Kafka Cluster on top of Kubernetes.
Strimzi has been used as Kubernetes Operator to manipulate Apache Kafka Cluster.

Link to Strimzi main page: [strimzi.io](https://strimzi.io/)

## Exercises

Exercises should be done in order:

- [Install Strimzi on Minikube](kafka-installation.md)
- [Scaling up Kafka Brokers](brokers-scaling.md)
- [Rolling Update of Kafka configuration](kafka-configuration.md)
- [Rolling Update of Topic configuration](topic-configuration.md)
- [Install Prometheus + Grafana on Minikube](monitoring.md)
- [Solving problems with running cluster]()
- [Create MirrorMaker to Backup Cluster]()

Or use buildin `perf` test tool:

```sh
kubectl -n kafka run kafka-perf-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-producer-perf-test.sh --topic kafka-topic --num-records 1000000 --record-size 100 --throughput 1000 --producer-props bootstrap.servers=kafka-cluster-kafka-bootstrap:9092
```

### Scale up our cluster

Change line 9 in file: `resources/kafka-simple-single.yaml`

```sh
    replicas: 1 -> 3
```

And apply changes in Kafka cluster:

```sh
kubectl apply -f resources/kafka-simple-single.yaml
```

### See in realtime how Kafka cluster is scaled up

```sh
watch kubectl get pods -n kafka
```

### Run perf kafka producer

```sh
kubectl -n kafka run kafka-perf-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-producer-perf-test.sh --topic kafka-topic --num-records 1000000 --record-size 100 --throughput 1000 --producer-props bootstrap.servers=kafka-cluster-kafka-bootstrap:9092
```

### Remove comment in line 19 in file `resources/kafka-simple-single.yaml`

Apply changes in Kafka cluster:

```sh
kubectl apply -f resources/kafka-simple-single.yaml
```

### See in realtime how Kafka brokers are restarted one by one

```sh
watch kubectl get pods -n kafka
```
