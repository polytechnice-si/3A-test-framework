package fr.unice.polytech.qgl.tests.mock.internal;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MockHandler implements ConfigurableObject {


    @Override public  Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        switch(method.getName()) {
            case "registerInvocation":
                //this.getClass().getMethod(method.getName()).invoke(this, args);
                this.registerInvocation((MockedInvocation) args[0], args[1]);
                return null;
            case "registerDefaultValue":
                this.registerDefaultValue((String) args[0], args[1]);
                return null;
            default:
                return handle(new MockedInvocation(method.getName(), args));

        }
    }

    @Override
    public void registerInvocation(MockedInvocation invocation, Object value) {
        expectations.put(invocation, value);
    }

    @Override
    public void registerDefaultValue(String methodName, Object value) {
        defaults.put(methodName, value);
    }

    private Map<MockedInvocation, Object> expectations = new HashMap<>();
    private Map<String, Object> defaults= new HashMap<>();

    private Object handle(MockedInvocation invocation) {
        if (expectations.containsKey(invocation))
            return expectations.get(invocation);
        else if (defaults.containsKey(invocation.getMethod()))
            return defaults.get(invocation.getMethod());
        throw new RuntimeException("No mock defined !");
    }

}
