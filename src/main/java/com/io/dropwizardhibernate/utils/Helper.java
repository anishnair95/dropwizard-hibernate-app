package com.io.dropwizardhibernate.utils;

import static java.util.function.Function.identity;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Helper {

    /**
     * Check if a string is null or empty
     *
     * @param string the string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    /**
     * If the value is null then return the default value else return the value
     */
    public static <T> T ifNullThen(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * If the value is default value then return newValue else return value
     */
    public static <T> T ifDefaultThen(T value, T def, T newValue) {
        return value.equals(def) ? newValue : value;
    }


    public static <T> void setIfNotNull(Consumer<T> func, T val) {
        setIfNotNull(func, val, identity());
    }

    public static <T, U> void setIfNotNull(Consumer<T> consumer, Supplier<U> supplier, Function<U, T> extractor) {
        U object = supplier.get();
        if (object != null) {
            T val = extractor.apply(object);
            setIfNotNull(consumer, val, identity());
        }
    }

    public static <U, V> void setIfNotNull(Consumer<U> func, V val, Function<V, U> converter) {
        if (val != null && converter != null) {
            U u = converter.apply(val);
            if (u != null) {
                func.accept(u);
            }
        }
    }

}
