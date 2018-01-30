package fr.unice.polytech.qgl.tests.mock.internal;


public interface ConfigurableObject {

    void registerInvocation(Invocation invocation, Object value);

    void registerDefaultValue(String methodName, Object value);

}
