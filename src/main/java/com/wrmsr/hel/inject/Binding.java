package com.wrmsr.hel.inject;

import javax.annotation.concurrent.Immutable;

import static java.util.Objects.requireNonNull;

@Immutable
public final class Binding {
    final Key<?> key;
    final Provider<?> provider;

    public Binding(Key<?> key, Provider<?> provider) {
        this.key = requireNonNull(key);
        this.provider = requireNonNull(provider);
    }
}
