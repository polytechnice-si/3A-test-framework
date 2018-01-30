package fr.unice.polytech.qgl.tests.mock;

import fr.unice.polytech.qgl.tests.mock.internal.MockHandler;
import fr.unice.polytech.qgl.tests.mock.internal.MockedInvocation;
import fr.unice.polytech.qgl.tests.mock.internal.ConfigurableObject;

import java.lang.reflect.Proxy;

public class Mock {

    public static<T> T mock(Class<T> clazz) {
        ClassLoader current = Mock.class.getClassLoader();
        Class<?>[] interfaces = new Class<?>[]{ clazz, ConfigurableObject.class } ;
        return (T) Proxy.newProxyInstance(current, interfaces, new MockHandler());
    }


    public static MockedInvocation calling(Object obj, String method, Object... params) {
        if(! (obj instanceof ConfigurableObject)){
            throw new RuntimeException("Cannot configure a regular object!");
        }
        return new MockedInvocation((ConfigurableObject) obj, method, params);
    }

}
