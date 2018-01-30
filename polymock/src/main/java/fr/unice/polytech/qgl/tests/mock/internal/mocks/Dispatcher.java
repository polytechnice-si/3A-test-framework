package fr.unice.polytech.qgl.tests.mock.internal.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Dispatcher implements InvocationHandler {

    private MockedObject theMock = new MockedObject();

    @Override
    public  Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(isConfigurationMethod(method)) {
            return method.invoke(theMock, args);
        } else {
            return theMock.handle(new Invocation(method.getName(), args));
        }
    }

    private boolean isConfigurationMethod(Method method) {
        return Arrays.stream(ConfigurableObject.class.getMethods()).anyMatch(method::equals);
    }

}
