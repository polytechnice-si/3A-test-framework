package fr.unice.polytech.qgl.tests.mock.internal.mocks;

import java.util.HashMap;
import java.util.Map;

public class MockedObject implements ConfigurableObject {

    @Override
    public void registerInvocation(Invocation invocation, Object value) { expectations.put(invocation, value); }

    @Override
    public void registerDefaultValue(String methodName, Object value) { defaults.put(methodName, value); }

    private Map<Invocation, Object> expectations = new HashMap<>();
    private Map<String, Object> defaults= new HashMap<>();

    @Override
    public Object handle(Invocation invocation) {
        if (expectations.containsKey(invocation))
            return expectations.get(invocation);
        else if (defaults.containsKey(invocation.getMethod()))
            return defaults.get(invocation.getMethod());
        throw new RuntimeException("No mocked value defined !");
    }

}
