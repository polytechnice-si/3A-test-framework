package fr.unice.polytech.qgl.tests.mock.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MockedObject implements InvocationHandler {

    @Override public  Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch(method.getName()) {
            case "registerInvocation":
                this.memorize((Invocation) args[0], args[1]);
                return null;
            case "registerDefaultValue":
                this.memorize((String) args[0], args[1]);
                return null;
            default:
                return this.handle(new Invocation(method.getName(), args));
        }
    }

    private void memorize(Invocation invocation, Object value) { expectations.put(invocation, value); }

    private void memorize(String methodName, Object value) { defaults.put(methodName, value); }

    private Map<Invocation, Object> expectations = new HashMap<>();
    private Map<String, Object> defaults= new HashMap<>();

    private Object handle(Invocation invocation) {
        if (expectations.containsKey(invocation))
            return expectations.get(invocation);
        else if (defaults.containsKey(invocation.getMethod()))
            return defaults.get(invocation.getMethod());
        throw new RuntimeException("No mocked value defined !");
    }

}
