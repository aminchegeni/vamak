package ir.snapp.pay.side.project.vamak.commons;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

@FunctionalInterface
public interface Identifiable<T extends Serializable> {

    T getCode();

    static <K extends Serializable, V extends Enum<V> & Identifiable<K>> Map<K, V> createCache(Class<V> clazz) {
        requireNonNull(clazz, "class must not be null");
        return Stream.of(clazz.getEnumConstants()).collect(
                collectingAndThen(
                        toMap(
                                Identifiable::getCode,
                                Function.identity()
                        ),
                        Collections::unmodifiableMap));

    }
}
