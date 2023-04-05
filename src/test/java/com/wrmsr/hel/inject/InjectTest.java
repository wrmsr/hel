package com.wrmsr.hel.inject;

import junit.framework.TestCase;

import static com.wrmsr.hel.inject.Inject.Bindings;
import static com.wrmsr.hel.inject.Inject.Injector;

public class InjectTest extends TestCase {

    public void testApp() {
        Bindings bs = new Bindings();
        Injector inj = new Injector(bs, null);
    }
}
