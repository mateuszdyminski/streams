# Streams

Simple Java example how to use Kafka-Streams.

### Requirements

* Installed Kafka (only if you would like to produce and consume messages from you local CLI)
* Docker
* Docker-compose
* Java8

## Setup Kafka

```
cd infra && docker-compose up -d
```

## Run word-count
```
gradle installDist && build/install/streams/bin/wordCount
```

## Verification

Open kafka consumer in one terminal:
```
kafka-console-consumer.sh --zookeeper localhost:2181 --topic WordsWithCountsTopic --from-beginning
```

Open kafka producer in another:
```
kafka-console-producer.sh --broker-list localhost:9092 --topic TextLinesTopic
```

Type some words in producer console. In consumer you should see words count.
