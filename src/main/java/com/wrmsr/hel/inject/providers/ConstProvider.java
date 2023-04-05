package com.wrmsr.hel.inject.providers;

import com.wrmsr.hel.inject.Key;

import java.lang.reflect.Type;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public final class ConstProvider implements Provider {
    private final Object v;

    public ConstProvider(Object v) {
        this.v = requireNonNull(v);
    }

    @Override
    public Type providedTy(Function<Key, Type> rec) {
        return v.getClass();
    }

    @Override
    public ProviderFn providerFn() {
        return inj -> v;
    }
}
