package com.wrmsr.hel.inject;

import java.lang.reflect.ParameterizedType;

import static java.util.Objects.requireNonNull;

public class TypeLit<T> {
    final Class<? extends T> ty;
    final int hashCode;

    @SuppressWarnings({"unchecked"})
    private static <T> Class<? extends T> getParam(Class<?> cls) {
        Class<?> pCls = (Class<?>) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0];
        return (Class<? extends T>) pCls;
    }

    public TypeLit() {
        ty = getParam(getClass());
        hashCode = ty.hashCode();
    }

    public TypeLit(Class<? extends T> ty) {
        this.ty = requireNonNull(ty);
        hashCode = this.ty.hashCode();
    }

    public static <T> TypeLit<T> of() {
        return new TypeLit<T>() {
        };
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
