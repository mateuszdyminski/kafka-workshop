# Topic configuration

## Check current topic configuration

```sh
$ kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic kafka-topic
Topic:kafka-topic	PartitionCount:3	ReplicationFactor:1	Configs:segment.bytes=1073741824,message.format.version=2.2-IV1,retention.ms=7200000
	Topic: kafka-topic	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: kafka-topic	Partition: 1	Leader: 1	Replicas: 1	Isr: 1
	Topic: kafka-topic	Partition: 2	Leader: 2	Replicas: 2	Isr: 2
```

## Update Topic config

Remove comment in line 14 in file `resources/kafka-topic.yaml`

## Apply changes

```sh
kubectl apply -f resources/kafka-topic.yaml -n kafka
```

## Check current topic configuration

```sh
$ kubectl -n kafka exec -ti kafka-cluster-kafka-0 -- bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic kafka-topic
Topic:kafka-topic	PartitionCount:3	ReplicationFactor:1	Configs:segment.bytes=1073741824,message.format.version=2.2-IV1,retention.ms=7200000,max.message.bytes=128000
	Topic: kafka-topic	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: kafka-topic	Partition: 1	Leader: 1	Replicas: 1	Isr: 1
	Topic: kafka-topic	Partition: 2	Leader: 2	Replicas: 2	Isr: 2
```
