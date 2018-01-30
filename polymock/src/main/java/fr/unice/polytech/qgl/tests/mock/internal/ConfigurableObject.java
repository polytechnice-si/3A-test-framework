package fr.unice.polytech.qgl.tests.mock.internal;

import java.lang.reflect.InvocationHandler;

public interface ConfigurableObject extends InvocationHandler {

    void registerInvocation(MockedInvocation invocation, Object value);

    void registerDefaultValue(String methodName, Object value);

}
