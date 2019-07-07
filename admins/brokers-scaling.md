# Scalling up Kafka Brokers

# Create Topic and send some messages

Create Topic:

```sh
kubectl apply -f resources/kafka-topic.yaml
```

Write some messages:

```sh
kubectl -n kafka run kafka-perf-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-producer-perf-test.sh --topic kafka-topic --num-records 100 --record-size 100 --throughput 1000 --producer-props bootstrap.servers=kafka-cluster-kafka-bootstrap:9092
```

## Describe Topic

```sh
$ kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic kafka-topic

Topic:kafka-topic	PartitionCount:3	ReplicationFactor:1	Configs:segment.bytes=1073741824,message.format.version=2.2-IV1,retention.ms=7200000
	Topic: kafka-topic	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: kafka-topic	Partition: 1	Leader: 0	Replicas: 0	Isr: 0
	Topic: kafka-topic	Partition: 2	Leader: 0	Replicas: 0	Isr: 0
```

## Scale up our cluster

Change line 9 in file: `resources/kafka-simple-single.yaml`

```sh
    replicas: 1 -> 3
```

And apply changes in Kafka cluster:

```sh
kubectl apply -f resources/kafka-simple-single.yaml
```

## See in realtime how Kafka cluster is scaled up

```sh
watch kubectl get pods -n kafka
```

## Describe Topic again

```sh
$ kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic kafka-topic

Topic:kafka-topic	PartitionCount:3	ReplicationFactor:1	Configs:segment.bytes=1073741824,message.format.version=2.2-IV1,retention.ms=7200000
	Topic: kafka-topic	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: kafka-topic	Partition: 1	Leader: 0	Replicas: 0	Isr: 0
	Topic: kafka-topic	Partition: 2	Leader: 0	Replicas: 0	Isr: 0
```

Problem: 3 Partitions are on the same broker - we need to repartition them

## Reassign partitions

## Put topics list on broker

```sh
cat resources/topics/kafka-topic.json | kubectl exec -c kafka kafka-cluster-kafka-0 -n kafka -i -- /bin/bash -c 'cat > /tmp/topics.json'
```

## Generate new assignment plan

```sh
$ kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-reassign-partitions.sh --zookeeper localhost:2181 --topics-to-move-json-file /tmp/topics.json --broker-list 0,1,2 --generate

Current partition replica assignment
{
  "version": 1,
  "partitions": [
    {
      "topic": "kafka-topic",
      "partition": 2,
      "replicas": [0],
      "log_dirs": ["any"]
    },
    {
      "topic": "kafka-topic",
      "partition": 1,
      "replicas": [0],
      "log_dirs": ["any"]
    },
    {
      "topic": "kafka-topic",
      "partition": 0,
      "replicas": [0],
      "log_dirs": ["any"]
    }
  ]
}


Proposed partition reassignment configuration
{
  "version": 1,
  "partitions": [
    {
      "topic": "kafka-topic",
      "partition": 2,
      "replicas": [2],
      "log_dirs": ["any"]
    },
    {
      "topic": "kafka-topic",
      "partition": 1,
      "replicas": [1],
      "log_dirs": ["any"]
    },
    {
      "topic": "kafka-topic",
      "partition": 0,
      "replicas": [0],
      "log_dirs": ["any"]
    }
  ]
}
```

## Put new assignments on broker

```sh
cat resources/topics/proposed-assignment.json | kubectl exec -c kafka kafka-cluster-kafka-0 -n kafka -i -- /bin/bash -c 'cat > /tmp/reassignment.json'
```

## Run plan

```sh
kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-reassign-partitions.sh --zookeeper localhost:2181 --reassignment-json-file /tmp/reassignment.json --execute
```

## Verify

```sh
kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-reassign-partitions.sh --zookeeper localhost:2181 --reassignment-json-file /tmp/reassignment.json --verify
```

## Describe Topic for final check

```sh
$ kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic kafka-topic

Topic:kafka-topic	PartitionCount:3	ReplicationFactor:1	Configs:segment.bytes=1073741824,message.format.version=2.2-IV1,retention.ms=7200000
	Topic: kafka-topic	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: kafka-topic	Partition: 1	Leader: 1	Replicas: 1	Isr: 1
	Topic: kafka-topic	Partition: 2	Leader: 2	Replicas: 2	Isr: 2
```
