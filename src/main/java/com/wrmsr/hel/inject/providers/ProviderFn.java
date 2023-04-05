package com.wrmsr.hel.inject.providers;

import com.wrmsr.hel.inject.Injector;

import java.util.function.Function;

@FunctionalInterface
public interface ProviderFn extends Function<Injector, Object> {
}
