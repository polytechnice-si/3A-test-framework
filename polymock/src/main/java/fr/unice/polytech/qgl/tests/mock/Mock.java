package fr.unice.polytech.qgl.tests.mock;

import fr.unice.polytech.qgl.tests.mock.internal.MockedObject;
import fr.unice.polytech.qgl.tests.mock.internal.Invocation;
import fr.unice.polytech.qgl.tests.mock.internal.ConfigurableObject;

import java.lang.reflect.Proxy;

public class Mock {

    public static<T> T mock(Class<T> clazz) {
        ClassLoader current = Mock.class.getClassLoader();
        Class<?>[] interfaces = new Class<?>[]{ clazz, ConfigurableObject.class } ;
        return (T) Proxy.newProxyInstance(current, interfaces, new MockedObject());
    }


    public static Invocation calling(Object obj, String method, Object... params) {
        if(! (obj instanceof ConfigurableObject)){
            throw new RuntimeException("Cannot configure a regular object!");
        }
        return new Invocation((ConfigurableObject) obj, method, params);
    }

}
