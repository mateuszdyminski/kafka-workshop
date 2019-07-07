# Kafka on Minikube Installation Guide

## Install Strimzi Repo via Helm

Add repository:

```sh
helm repo add strimzi http://strimzi.io/charts/
```

Check if repository has been added:

```sh
$ helm search strimzi/ --versions
NAME                           CHART VERSION APP VERSION DESCRIPTION
strimzi/strimzi-kafka-operator 0.12.1        0.12.1      Strimzi: Kafka as a Service
strimzi/strimzi-kafka-operator 0.12.0        0.12.0      Strimzi: Kafka as a Service
strimzi/strimzi-kafka-operator 0.11.4        0.11.4      Strimzi: Kafka as a Service
strimzi/strimzi-kafka-operator 0.11.3        0.11.3      Strimzi: Kafka as a Service
```

### Install Strimzi Operator

```sh
helm install strimzi/strimzi-kafka-operator --namespace kafka
```

or install specified version:

```sh
helm install strimzi/strimzi-kafka-operator --name strimzi-release --namespace kafka --version 0.12.1
```

All parameters which might be used during Kafka installation: [README.md](https://github.com/strimzi/strimzi-kafka-operator/blob/master/helm-charts/strimzi-kafka-operator/README.md)

Detailed `helm` installation documentation available [HERE](https://strimzi.io/2018/11/01/using-helm.html)

### Create Namespace for Kafka cluster - it should be already created by helm

```sh
kubectl create namespace kafka
```

### Install single pod Kafka with zookeeper

```sh
kubectl apply -f resources/kafka-simple-single.yaml
```

### See in realtime how zookeeper and kafka are starting

```sh
$ watch kubectl get pods -n kafka
Every 2.0s: kubectl get pods -n kafka          Mateuszs-MacBook-Pro.local: Thu Jun 27 15:10:46 2019

NAME                                            READY   STATUS    RESTARTS   AGE
kafka-cluster-entity-operator-85d4c86f7c-tpvn6  3/3     Running   0          87s
kafka-cluster-kafka-0                           2/2     Running   0          113s
kafka-cluster-zookeeper-0                       2/2     Running   0          2m17s
strimzi-cluster-operator-6849d55bf-mbsb2        1/1     Running   0          3m4s
```

### Check the PersistentVolumeClaims

```sh
$ kubectl get pvc -n kafka
NAME                           STATUS   VOLUME                                     CAPACITY   ACCESS MODES   STORAGECLASS   AGE
data-0-kafka-cluster-kafka-0     Bound    pvc-c5e92cfa-24c3-4306-8db9-9a6a21683a82   10Gi       RWO            standard       88s
data-kafka-cluster-zookeeper-0   Bound    pvc-c5579637-012e-469a-85f6-b333cf4f43be   10Gi       RWO            standard       112s
```

### Open Dashboard

```sh
minikube dashboard
```

### Final test

Let's write some messages to Kafka:

```sh
kubectl -n kafka run kafka-producer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-producer.sh --broker-list kafka-cluster-kafka-bootstrap:9092 --topic test-topic
```

And read them:

```sh
kubectl -n kafka run kafka-consumer -ti --image=strimzi/kafka:0.12.1-kafka-2.2.1 --rm=true --restart=Never -- bin/kafka-console-consumer.sh --bootstrap-server kafka-cluster-kafka-bootstrap:9092 --topic test-topic --from-beginning
```
