/*
TODO:
 - records
*/
package hel.inject;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public final class Inject {
    private Inject() {
    }

    public static final class Key {
        private final Type ty;
        private final boolean arr;
        private final @Nullable Object tag;

        public Key(
                Type ty,
                boolean arr,
                @Nullable Object tag
        ) {
            this.ty = ty;
            this.arr = arr;
            this.tag = tag;
        }
    }

    @FunctionalInterface
    public interface ProviderFn extends Function<Object, Object> {
    }

    public interface Provider {
        Type providedTy(Function<Key, Type> rec);

        ProviderFn providerFn();
    }

    public static final class Binding {
        private final Key key;
        private final Provider provider;

        public Binding(
                Key key,
                Provider provider
        ) {
            this.key = requireNonNull(key);
            this.provider = requireNonNull(provider);
        }
    }

    public interface Bindings extends Iterable<Binding> {
    }

    public static final class Injector {
        private final Bindings bs;

        public Injector(
                @Nullable Bindings bs
        ) {
            this.bs = bs;
        }
    }
}
