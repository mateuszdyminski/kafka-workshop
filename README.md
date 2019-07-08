# Apache Kafka™ Workshop

The repository contains the materials for [Apache Kafka™](https://kafka.apache.org/) Workshop.

## Repository structure

- [slides](slides) - directory with all presentations
- [admins](admins) - exercises for Kafka Administrators
- [exercises](devs) - exercises for Developers

## Requirements

- installed and configurad `minikube` in version >= v1.2.0
- installed and configured `kubectl` in version >= v1.15.0
- installed and configured `helm` in version >= 2.11
- installed and configured `JDK11` and `Maven`
- installed and configured `Docker`
- installed and configured account in `dockerhub`
- favourite IDE

## Agenda

- [Lecture] - Lecture session
- [Demo] - Demo session
- [Exercise] - Exercise session

1. General Sessions:

- [Lecture] Intro - 5 mins
- [Lecture] Apache Kafka Basics - 25 mins

2. Kafka for Administrators

- [Lecture] Kafka on Kubernetes - challenges - 10 mins
- [Lecture] Kubernetes Storage 101 - 20 mins
- [Lecture] Kubernetes Operators Intro - 10 mins
- [Lecture] Strimzi - [https://strimzi.io/](https://strimzi.io/)
  - [Demo] Strimzi Demo
  - [Exercise] Install Strimzi on Minikube
  - [Exercise] Scaling up Kafka Brokers
  - [Exercise] Repartitioning
- [Lecture] Kafka Configuration
  - [Lecture] Partitioning
  - [Lecture] Log Compaction
  - [Demo] How to reconfigure Kafka Topics in Kafka on K8s
  - [Exercise] Rolling Update of Topic configuration
  - [Exercise] Rolling Update of Broker configuration
- [Lecture] Kafka Configuration for HA
- [Lecture] Replicas and In-Sync Replicas (ISRs)
- [Lecture] Monitoring Kafka Cluster
  - [Demo] Kafka Monitoring Demo - Prometheus + Grafana
  - [Exercise] Install Prometheus + Grafana on Minikube
- [Lecture] Troubleshooting and Debugging
  - [Exercise] Solving problems with running cluster
- [Lecture] Kafka Partitioning, partition vs consuments, repartitioning
- [Lecture] Kafka Backup Strategies
  - [Exercise] Create MirrorMaker to Backup Cluster

3. Kafka for Developers

- [Lecture] Kafka Producers and Consumers
- [Lecture] Writing Kafka Producer
  - [Lecture] Producer API
  - [Exercise] Create Simple Producer and deploy it on K8s
- [Lecture] Writing Kafka Consumer
  - [Lecture] Consumer API
  - [Exercise] Create Simple Consumer and deploy it on K8s
- [Lecture] Patterns for Kafka Consumers and Producers
  - [Exercise] Message Acknowledgement
  - [Exercise] Transactions and Fallback technique
- [Lecture] Kafka Streams API
  - [Exercise] Windows, Joins, Tables, and State Stores
