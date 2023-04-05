package com.wrmsr.hel.inject;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public final class Injector {
    private final Bindings bs;
    private final @Nullable Injector parent;

    private final Map<Key<?>, ProviderFn<?>> pfm;

    public Injector(Bindings bs, @Nullable Injector parent) {
        this.bs = requireNonNull(bs);
        this.parent = parent;

        Map<Key<?>, Provider<?>> pm = Inject.makeProviderMap(bs);
        this.pfm = pm.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().providerFn()));
    }

    @SuppressWarnings({"unchecked"})
    @Nullable
    public <T> T maybeProvide(Key<T> k) {
        if (k.equals(Inject.injectorKey)) {
            return (T) this;
        }

        ProviderFn<?> pfn = pfm.get(k);
        if (pfn != null) {
            return ((ProviderFn<T>) pfn).apply(this);
        }

        if (parent != null) {
            T po = parent.maybeProvide(k);
            if (po != null) {
                return po;
            }
        }

        return null;
    }
}
