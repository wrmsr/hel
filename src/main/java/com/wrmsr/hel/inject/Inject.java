/*
TODO (func):
 - erasure lol - cgi.TypeLiteral

TODO (style):
 - records
*/
package com.wrmsr.hel.inject;

import com.wrmsr.hel.inject.exceptions.DuplicateBindingException;
import com.wrmsr.hel.inject.providers.MultiProvider;
import com.wrmsr.hel.inject.providers.Provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Inject {
    private Inject() {
    }

    static Map<Key, Provider> makeProviderMap(Bindings bs) {
        Map<Key, Provider> pm = new HashMap<>();
        Map<Key, List<Provider>> mm = null;
        for (Binding b : bs) {
            if (b.key.multi) {
                if (mm == null) {
                    mm = new HashMap<>();
                }
                mm.computeIfAbsent(b.key, k -> new ArrayList<>()).add(b.provider);
            } else {
                if (pm.containsKey(b.key)) {
                    throw new DuplicateBindingException(b.key);
                }
                pm.put(b.key, b.provider);
            }
        }
        if (mm != null) {
            mm.forEach((k, aps) -> {
                pm.put(k, new MultiProvider(k.ty, aps));
            });
        }
        return pm;
    }

    public static final Key injectorKey = new Key(Injector.class, false, null);
}