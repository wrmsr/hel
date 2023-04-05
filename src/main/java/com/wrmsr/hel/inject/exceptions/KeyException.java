package com.wrmsr.hel.inject.exceptions;

import com.wrmsr.hel.inject.Key;

import static java.util.Objects.requireNonNull;

public abstract class KeyException extends RuntimeException {
    protected final Key<?> key;

    protected KeyException(Key<?> key) {
        this.key = requireNonNull(key);
    }
}
