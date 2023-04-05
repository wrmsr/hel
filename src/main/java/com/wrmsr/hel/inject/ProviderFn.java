package com.wrmsr.hel.inject;

import java.util.function.Function;

@FunctionalInterface
public interface ProviderFn<T> extends Function<Injector, T> {
}
