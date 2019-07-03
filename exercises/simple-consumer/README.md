# Commands

put in ~/.m2/settings.xml

```
  <servers>
    <server>
      <id>docker.io</id>
      <username>YOUR DOCKERHUB USER HERE</username>
      <password>YOUR DOCKERHUB PASSWORD HERE</password>
      <configuration>
        <email>YOUR DOCKERHUB EMAIL HERE</email>
      </configuration>
    </server>
  </servers>
```

```
Edit: pom.xml -> line:73
<repository>mateuszdyminski/kafka-simple-consumer</repository>

kubectl create namespace kafka-consumers

mvn clean install

mvn dockerfile:push

kubectl apply -f simple-consumer-deployment.yaml

kubectl -n kafka-consumers run kafka-simple-consumer -ti --image=mateuszdyminski/kafka-simple-consumer:1.3 --rm=true --restart=Never --env=KAFKA_BOOTSTRAP_SERVERS=pgs-cluster-kafka-bootstrap.kafka:9092


```