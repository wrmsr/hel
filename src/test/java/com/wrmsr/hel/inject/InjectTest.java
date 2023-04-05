package com.wrmsr.hel.inject;

import com.google.common.collect.ImmutableList;
import junit.framework.TestCase;

import static com.wrmsr.hel.inject.Inject.Binding;
import static com.wrmsr.hel.inject.Inject.Bindings;
import static com.wrmsr.hel.inject.Inject.ConstProvider;
import static com.wrmsr.hel.inject.Inject.Injector;
import static com.wrmsr.hel.inject.Inject.Key;
import static com.wrmsr.hel.inject.Inject.injectorKey;

public class InjectTest extends TestCase {

    public void testApp() {
        Bindings bs = new Bindings(ImmutableList.of(
                new Binding(new Key(Integer.class, false, null), new ConstProvider(420))
        ));
        Injector inj = new Injector(bs, null);
        System.out.println(inj.maybeProvide(injectorKey));
        System.out.println(inj.maybeProvide(new Inject.Key(Integer.class, false, null)));
    }
}
