# Original Message from Irek

Lista tematów na warsztat (1 dzien) wg. priorytetów:

1. Konfiguracja i obsluga Kafki

* tuning parametrow pod HA, performance (real-time) itp
* partycjonowanie (partycje vs konsumenci, repartycjonowanie)
* replikacja, backupy z zewnetrznego dysku

1. Event-sourcing

* retention (nieskonczona), snapshots
* backups (czasy przywracania) - porady praktyczne
* log compactions


## Requirements

* installed and configurad `minikube` in version >= v1.2.0
* installed and configured `kubectl` in version >= v1.15.0
* installed and configured `helm` in version >= 2.11
* installed and configured `JDK11`
* installed and configured `Docker`
* installed and configured account in `dockerhub`

## Agenda

[Lecture]   - Lecture session
[Demo]      - Demo session
[Exercise]  - Exercise session

1. General Sessions:

* [Lecture] Apache Kafka Basics - 30 mins

2. Kafka for Administrators

* [Lecture] Kafka on Kubernetes - challenges - 10 mins
* [Lecture] Kubernetes Storage 101 - 20 mins
* [Lecture] Kubernetes Operators Intro - 10 mins
* [Lecture] Strimzi - [https://strimzi.io/](https://strimzi.io/)
  * [Demo] Strimzi Demo
  * [Exercise] Install 
* [Lecture] Monitoring Kafka Cluster
  * [Demo] Kafka Monitoring Demo - Prometheus + Grafana
  * [Exercise] Kafka Monitoring
* [Lecture] Replicas and In-Sync Replicas (ISRs)
* [Lecture] Kafka Topics Configuration
  * [Lecture] Partitioning
  * [Lecture] Retention
  * [Lecture] Log Compaction
  * [Demo] `TODO`
  * [Exercise] `TODO`
* [Lecture] Kafka Configuration for HA
  * [Demo] `TODO`
  * [Exercise] `TODO`
* [Lecture] Kafka Configuration for Best Performance
  * [Demo] `TODO`
  * [Exercise] `TODO`
* [Lecture] Kafka Server and Scheduled Recurring Tasks
* [Lecture] Troubleshooting and Debugging
* [Lecture] Kubernetes Snapshots of PVC
* [Lecture] Kafka Partitioning, partition vs consuments, repartitioning
* [Lecture] Backup's from external disk
  * [Demo] `TODO`
  * [Exercise] `TODO`

3. Kafka for Developers

* [Lecture] Kafka Producers and Consumers
* [Lecture] Developing Kafka Producers
  * [Lecture] Producer API
  * [Lecture] KafkaProducer
  * [Exercise] `TODO`
* [Lecture] Developing Kafka Consumers
  * [Lecture] Consumer API
  * [Lecture] KafkaConsumer, TopicPartition, ConsumerRecord
  * [Lecture] partition vs consuments
* [Lecture] Kafka Streams
* [Lecture] Kafka Connect
* [Lecture] AVRO

### Exercises

#### Exercises - for Administrators

* Strimzi
  * Install Strimzi Operator
  * Install Kafka
  * Test Kafka cluster with Producer, Consumer and Consumer Perf
* Monitoring
  * Install Prometheus + Grafana
  * 
* Configuration
  * Kafka for HA configuration + benchmarks
  * Kafka for Real Time + benchmarks

#### Exercises - for Developers

* Simple Consumer
* Simple Producer
* Consumer Groups
* Message Acknowledgement
* Transactions
* Windows, Joins, Tables, and State Stores
* Scaling Concurrent Operations in Streaming Systems
* Deleting messages in Kafka Topic (GDPR related)
* Authorization Example
