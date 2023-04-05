package com.wrmsr.hel.inject.providers;

import com.wrmsr.hel.inject.Key;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public final class MultiProvider implements Provider {
    private final Type ty;
    private final List<Provider> ps;

    public MultiProvider(Type ty, List<Provider> ps) {
        this.ty = requireNonNull(ty);
        this.ps = requireNonNull(ps);
    }

    @Override
    public Type providedTy(Function<Key, Type> rec) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProviderFn providerFn() {
        throw new UnsupportedOperationException();
    }
}
