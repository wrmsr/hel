package com.wrmsr.hel.inject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static java.util.Objects.requireNonNull;

public class TypeLit<T> {
    final Type ty;
    final int hashCode;

    public TypeLit() {
        ty = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        hashCode = ty.hashCode();
    }

    public TypeLit(Type ty) {
        this.ty = requireNonNull(ty);
        hashCode = this.ty.hashCode();
    }

    public static <T> TypeLit<T> of() {
        return new TypeLit<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeLit<?> typeLit = (TypeLit<?>) o;

        if (hashCode != typeLit.hashCode) return false;
        return ty.equals(typeLit.ty);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
}
