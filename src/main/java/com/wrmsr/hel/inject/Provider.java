package com.wrmsr.hel.inject;

import java.util.function.Function;

public interface Provider<T> {
    TypeLit<T> providedTy(Function<Key<T>, TypeLit<T>> rec);

    ProviderFn<T> providerFn();
}
