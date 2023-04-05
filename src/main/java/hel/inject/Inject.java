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

    private static Map<Key, ProviderFn> makeProviderMap(Bindings bs) {
        Map<Key, ProviderFn> pm = new HashMap<>();
        Map<Key, List<Provider>> am = null;
        for (Binding b : bs) {
            if (b.key.arr) {
                if (am == null) {
                    am = new HashMap<>();
                }
                am.computeIfAbsent(b.key, (k) -> {
                    return new ArrayList<Provider>();
                });
//                am.put(b.key, )
            }
        }
        /*
                    am[b.key] = append(am[b.key], b.provider)
                } else {
                    if _, ok := pm[b.key]; ok {
                        panic(DuplicateBindingError{KeyError{Key: b.key}})
                    }
                    pm[b.key] = b.provider
                }
                return true
            })
            if am != nil {
                for k, aps := range am {
                    pm[k] = newArrayProvider(k.ty, aps)
                }
            }
            return pm
        */
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
