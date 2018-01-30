package fr.unice.polytech.qgl.tests.mock.internal.spies;

import java.lang.reflect.InvocationHandler;

public interface Spied extends InvocationHandler {

    int howManyTimes(String method);


}
