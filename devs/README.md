# Kafka for Administrators

Set of exercises work with Kafka from Java developer perspective.

## Exercises

Exercises should be done in order:

- [Run simple producer on K8s cluster](simple-producer.md)
- [Run simple consumer on K8s cluster](simple-consumer.md)
- [Message delivery Acknowledgement](ack.md)
- [Transaction Producer](transactions.md)
- [Kafka Streams](streams.md)

## Requirement: Configure Dockerhub server

Put in ~/.m2/settings.xml

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0                       http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>docker.io</id>
      <username>YOUR DOCKERHUB USER HERE</username>
      <password>YOUR PASSWORD HERE</password>
      <configuration>
        <email>YOUR DOCKERHUB EMAIL HERE</email>
      </configuration>
    </server>
  </servers>
</settings>
```
