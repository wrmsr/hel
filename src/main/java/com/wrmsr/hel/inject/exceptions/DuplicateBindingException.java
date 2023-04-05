package com.wrmsr.hel.inject.exceptions;

import com.wrmsr.hel.inject.Key;

public final class DuplicateBindingException extends KeyException {
    public DuplicateBindingException(Key<?> key) {
        super(key);
    }
}
