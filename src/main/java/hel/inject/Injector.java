package hel.inject;

import javax.annotation.Nullable;

public final class Injector {
    private final Bindings bs;

    public Injector(
            @Nullable Bindings bs
    ) {
        this.bs = bs;
    }
}
