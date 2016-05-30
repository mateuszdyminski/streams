package com.streams;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * User mdyminski
 */
public class Util {

    static Serde<Long> LongStr() {
        return OnlySerializing((topic, doub) -> ("" + doub).getBytes());
    }

    private static class SimpleSerializer<T> implements Serializer<T> {

        private BiFunction<String, T, byte[]> fn;

        private SimpleSerializer(BiFunction<String, T, byte[]> fn) {
            this.fn = fn;
        }


        @Override
        public void configure(Map<String, ?> configs, boolean isKey) {
        }

        @Override
        public void close() {
        }

        @Override
        public byte[] serialize(String topic, T data) {
            return fn.apply(topic, data);
        }
    }

    private static <T> Serde<T> OnlySerializing(BiFunction<String, T, byte[]> fn) {

        return new Serde<T>() {
            @Override
            public void configure(Map<String, ?> configs, boolean isKey) {

            }

            @Override
            public void close() {

            }

            @Override
            public Serializer<T> serializer() {
                return new SimpleSerializer<>(fn);
            }

            @Override
            public Deserializer<T> deserializer() {
                return null;
            }
        };

    }
}
