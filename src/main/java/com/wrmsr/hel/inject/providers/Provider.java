package com.wrmsr.hel.inject.providers;

import com.wrmsr.hel.inject.Key;

import java.lang.reflect.Type;
import java.util.function.Function;

public interface Provider {
    Type providedTy(Function<Key, Type> rec);

    ProviderFn providerFn();
}
