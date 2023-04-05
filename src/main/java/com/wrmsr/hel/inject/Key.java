package com.wrmsr.hel.inject;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;
import java.util.StringJoiner;

@Immutable
public final class Key<T> {
    final TypeLit<T> ty;
    final boolean multi;
    final @Nullable Object tag;

    public Key(TypeLit<T> ty, boolean multi, @Nullable Object tag) {
        this.ty = ty;
        this.multi = multi;
        this.tag = tag;
    }

    public static <T> Key<T> of(Class<? extends T> cls) {
        return new Key<>(new TypeLit<>(cls), false, null);
    }

    public static <T> Key<T> of(Class<? extends T> cls, Object tag) {
        return new Key<>(new TypeLit<>(cls), false, tag);
    }

    public static <T> Key<T> ofMulti(Class<? extends T> cls) {
        return new Key<>(new TypeLit<>(cls), true, null);
    }

    public static <T> Key<T> ofMulti(Class<? extends T> cls, Object tag) {
        return new Key<>(new TypeLit<>(cls), true, tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key<?> key = (Key<?>) o;
        return multi == key.multi && Objects.equals(ty, key.ty) && Objects.equals(tag, key.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ty, multi, tag);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Key.class.getSimpleName() + "[", "]")
                .add("ty=" + ty)
                .add("multi=" + multi)
                .add("tag=" + tag)
                .toString();
    }
}
