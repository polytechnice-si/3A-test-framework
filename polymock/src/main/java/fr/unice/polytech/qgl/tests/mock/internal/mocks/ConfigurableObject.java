package fr.unice.polytech.qgl.tests.mock.internal.mocks;


public interface ConfigurableObject {

    void registerInvocation(Invocation invocation, Object value);

    void registerDefaultValue(String methodName, Object value);

    Object handle (Invocation invocation);

}
