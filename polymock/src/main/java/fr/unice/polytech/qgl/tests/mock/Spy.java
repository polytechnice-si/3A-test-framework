package fr.unice.polytech.qgl.tests.mock;

import fr.unice.polytech.qgl.tests.mock.internal.spies.Spied;
import fr.unice.polytech.qgl.tests.mock.internal.spies.SpiedObject;
import org.reflections.Reflections;

import java.lang.reflect.Proxy;
import java.util.Set;

public class Spy {
    
    public static<T> T spy(Class<T> theInterface, Class<? extends T> theConcreteClass) {
        ClassLoader current = Spy.class.getClassLoader();
        Class<?>[] interfaces = new Class<?>[]{ theInterface, Spied.class } ;
        return (T) Proxy.newProxyInstance(current, interfaces, new SpiedObject(theConcreteClass));
    }

    public static<T> T spy(Class<T> theInterface) {
        Reflections mirror = new Reflections();
        Set<Class<? extends T>> candidates = mirror.getSubTypesOf(theInterface);

        if(candidates.size() > 1)
            throw new RuntimeException("Too many candidates for " + theInterface);

        return spy(theInterface, candidates.iterator().next());
    }

    public static int howManyTimesWasCalled(Object spiedObject, String method) {
        if(! (spiedObject instanceof Spied))
            throw new RuntimeException("Cannot answer with an non-instrumented object!");
        return ((Spied) spiedObject).howManyTimes(method);
    }

}
