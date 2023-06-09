package com.wrmsr.hel.inject;

import com.google.common.collect.ImmutableList;
import com.wrmsr.hel.inject.providers.ConstProvider;
import junit.framework.TestCase;

import static com.wrmsr.hel.inject.Inject.injectorKey;

public class InjectTest extends TestCase {

    public void testApp() {
        TypeLit<Integer> tl = new TypeLit<>() {};
        System.out.println(tl);

        Bindings bs = new Bindings(ImmutableList.of(
                new Binding(Key.of(Integer.class), new ConstProvider<>(420))
        ));
        Injector inj = new Injector(bs, null);
        System.out.println(inj.maybeProvide(injectorKey));
        System.out.println(inj.maybeProvide(Key.of(Integer.class)));
    }
}
