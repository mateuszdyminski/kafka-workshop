# Commands

put in ~/.m2/settings.xml

```
  <servers>
    <server>
      <id>docker.io</id>
      <username>mateuszdyminski</username>
      <password>YOUR PASSWORD HERE</password>
      <configuration>
        <email>dyminski@gmail.com</email>
      </configuration>
    </server>
  </servers>
```

```
Edit: pom.xml -> line:73
<repository>mateuszdyminski/kafka-simple-producer</repository>

kubectl create namespace kafka-producers

mvn clean install

mvn dockerfile:push

kubectl create namespace kafka-producers

kubectl apply -f simple-producer-deployment.yaml

kubectl -n kafka-producers run kafka-simple-producer -ti --image=mateuszdyminski/kafka-simple-producer:1.4 --rm=true --restart=Never --env=KAFKA_BOOTSTRAP_SERVERS=kafka-cluster-kafka-bootstrap.kafka:9092


```
