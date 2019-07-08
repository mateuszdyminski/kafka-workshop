package com.kafka.workshop.transactions;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class TransactionsProducer {

	private final Logger logger = LoggerFactory.getLogger(TransactionsProducer.class);
	private final String[] topics;
	private final String fallbackTopic;
	private final Properties props;

	public TransactionsProducer(String bootstrapServers, String topics, String fallbackTopic) {
		this.topics = topics.split(",");
		this.fallbackTopic = fallbackTopic;

		String serializer = StringSerializer.class.getName();
		props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "transactional-producer");
		props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 100);
		props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 100);
		props.put(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, 100);
		props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); // enable idempotence
		props.put(ProducerConfig.ACKS_CONFIG, "all"); // ensure to get acks from all brokers
		props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional-id"); // set transaction id
		props.put("key.serializer", serializer);
		props.put("value.serializer", serializer);
	}

	public void produce(String msg) {
		Producer<String, String> producer = new KafkaProducer<>(props);
		Date d = new Date();
		String message = String.format("date: %s msg: %s", d, msg);

		producer.initTransactions(); //initiate transactions
		try {
			producer.beginTransaction(); //begin transactions

			Arrays.stream(topics).forEach(
					topic -> producer.send(new ProducerRecord<>(topic, message))
			);

			producer.commitTransaction(); //commit

		} catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
			// We can't recover from these exceptions, so our only option is to close the producer and exit.
			producer.close();

			sendFallbackMessage(message);
		} catch (KafkaException e) {
			// For all other exceptions, just abort the transaction
			producer.abortTransaction();
		}

		producer.close();
	}

	public void sendFallbackMessage(String message) {
		Producer producer = new KafkaProducer<>(props);

		logger.info("Sending message to fallback topic!");

		producer.initTransactions();
		try {
			producer.beginTransaction();
			producer.send(new ProducerRecord<>(fallbackTopic, message));
			producer.commitTransaction();
		} catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
			// We can't recover from these exceptions, so our only option is to close the producer and exit.
			producer.close();
		} catch (KafkaException e) {
			// For all other exceptions, just abort the transaction
			producer.abortTransaction();
		}
	}

	public static void main(String[] args) {
		String bootstrapServers = System.getenv("KAFKA_BOOTSTRAP_SERVERS");
		String topics = System.getenv("KAFKA_TOPICS");
		String fallbackTopic = System.getenv("KAFKA_FALLBACK_TOPIC");
		String message = System.getenv("KAFKA_MSG");

		TransactionsProducer c = new TransactionsProducer(bootstrapServers, topics, fallbackTopic);
		c.produce(message);
	}
}