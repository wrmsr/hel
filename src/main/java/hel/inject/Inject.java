/*
TODO:
 - records
*/
package hel.inject;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public final class Inject {
    private Inject() {
    }

    @Immutable
    public static final class Key {
        private final Type ty;
        private final boolean arr;
        private final @Nullable Object tag;

        public Key(Type ty, boolean arr, @Nullable Object tag) {
            this.ty = ty;
            this.arr = arr;
            this.tag = tag;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return arr == key.arr && Objects.equals(ty, key.ty) && Objects.equals(tag, key.tag);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ty, arr, tag);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Key.class.getSimpleName() + "[", "]").add("ty=" + ty).add("arr=" + arr).add("tag=" + tag).toString();
        }
    }

    @FunctionalInterface
    public interface ProviderFn extends Function<Object, Object> {
    }

    public interface Provider {
        Type providedTy(Function<Key, Type> rec);

        ProviderFn providerFn();
    }

    public static abstract class KeyException extends RuntimeException {
        protected final Key key;

        protected KeyException(Key key) {
            this.key = requireNonNull(key);
        }
    }

    public static final class DuplicateBindingError extends KeyException {
        public DuplicateBindingError(Key key) {
            super(key);
        }
    }

    private static final class ArrayProvider implements Provider {
        private final Type ty;
        private final List<Provider> ps;

        private ArrayProvider(
                Type ty,
                List<Provider> ps
        ) {
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

    private static Map<Key, Provider> makeProviderMap(Bindings bs) {
        Map<Key, Provider> pm = new HashMap<>();
        Map<Key, List<Provider>> am = null;
        for (Binding b : bs) {
            if (b.key.arr) {
                if (am == null) {
                    am = new HashMap<>();
                }
                am.computeIfAbsent(b.key, (k) -> new ArrayList<>()).add(b.provider);
            } else {
                if (pm.containsKey(b.key)) {
                    throw new DuplicateBindingError(b.key);
                }
                pm.put(b.key, b.provider);
            }
        }
        if (am != null) {
            am.forEach((k, aps) -> {
                pm.put(k, new ArrayProvider(k.ty, aps));
            });
        }
        return pm;
    }

    @Immutable
    public static final class Binding {
        private final Key key;
        private final Provider provider;

        private final Map<Key, ProviderFn> pfm;

        public Binding(Key key, Provider provider) {
            this.key = requireNonNull(key);
            this.provider = requireNonNull(provider);

            this.pfm = new HashMap<>();
        }
    }

    public interface Bindings extends Iterable<Binding> {
    }

    public static final class Injector {
        private final Bindings bs;
        private final @Nullable Injector parent;

        public Injector(Bindings bs, @Nullable Injector parent) {
            this.bs = requireNonNull(bs);
            this.parent = parent;
        }
    }
}
