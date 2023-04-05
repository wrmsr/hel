package com.wrmsr.hel.inject.providers;

import com.wrmsr.hel.inject.Key;
import com.wrmsr.hel.inject.Provider;
import com.wrmsr.hel.inject.ProviderFn;
import com.wrmsr.hel.inject.TypeLit;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public final class ConstProvider<T> implements Provider<T> {
    private final T v;

    public ConstProvider(T v) {
        this.v = requireNonNull(v);
    }

    @Override
    public TypeLit<T> providedTy(Function<Key<T>, TypeLit<T>> rec) {
        return new TypeLit<T>((Class<? extends T>) v.getClass());
    }

    @Override
    public ProviderFn<T> providerFn() {
        return inj -> v;
    }
}
