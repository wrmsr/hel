package com.wrmsr.hel.inject;

import com.google.common.collect.ImmutableList;
import junit.framework.TestCase;

import static com.wrmsr.hel.inject.Inject.Bindings;
import static com.wrmsr.hel.inject.Inject.Injector;
import static com.wrmsr.hel.inject.Inject.injectorKey;

public class InjectTest extends TestCase {

    public void testApp() {
        Bindings bs = new Bindings(ImmutableList.of());
        Injector inj = new Injector(bs, null);
        System.out.println(inj.maybeProvide(injectorKey));
    }
}
