package com.wrmsr.hel.inject;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public final class Bindings implements Iterable<Binding> {
    private final Iterable<Binding> it;

    public Bindings(Iterable<Binding> it) {
        this.it = requireNonNull(it);
    }

    @Override
    public Iterator<Binding> iterator() {
        return it.iterator();
    }

    @Override
    public void forEach(Consumer<? super Binding> action) {
        it.forEach(action);
    }

    @Override
    public Spliterator<Binding> spliterator() {
        return it.spliterator();
    }
}
