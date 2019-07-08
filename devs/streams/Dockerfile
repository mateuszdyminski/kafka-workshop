FROM openjdk:11.0.3-jdk-slim-stretch

RUN mkdir /usr/share/kafka

# Add the jar file
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/kafka/${JAR_FILE}

# Producer configuration
ENV KAFKA_BOOTSTRAP_SERVERS="kafka-cluster-kafka-bootstrap.kafka:9092"
ENV KAFKA_TOPICS="kafka-topic-trans-1,kafka-topic-trans-2"
ENV KAFKA_FALLBACK_TOPIC="kafka-topic-fallback"
ENV KAFKA_MSG="test message"
ENV EXECUTABLE_JAR=/usr/share/kafka/${JAR_FILE}

# Start Simple Kafka Producer
ENTRYPOINT java -jar $EXECUTABLE_JAR