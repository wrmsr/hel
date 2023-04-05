package com.wrmsr.hel.inject.providers;

import com.wrmsr.hel.inject.Key;
import com.wrmsr.hel.inject.Provider;
import com.wrmsr.hel.inject.ProviderFn;
import com.wrmsr.hel.inject.TypeLit;

import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public final class MultiProvider<T> implements Provider<T> {
    private final TypeLit<T> ty;
    private final List<Provider<T>> ps;

    public MultiProvider(TypeLit<T> ty, List<Provider<T>> ps) {
        this.ty = requireNonNull(ty);
        this.ps = requireNonNull(ps);
    }

    @Override
    public TypeLit<T> providedTy(Function<Key<T>, TypeLit<T>> rec) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProviderFn<T> providerFn() {
        throw new UnsupportedOperationException();
    }
}
