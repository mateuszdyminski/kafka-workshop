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
- [Log compaction](log-compaction.md)
- [Install Prometheus + Grafana on Minikube](monitoring.md)
- [Solving problems with running cluster](problems.md)
- [Create MirrorMaker to Backup Cluster](backup.md)
