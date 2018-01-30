package fr.unice.polytech.qgl.tests.mock.internal.spies;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpiedObject implements Spied {

    private Object theObject;
    private Map<String, Integer> counters;

    public SpiedObject(Class<?> theConcreteClass) {
        this.counters = new HashMap<>();
        try {
            theObject = theConcreteClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(isSpyMethod(method))
            return method.invoke(this, args);
        else {
            String name = method.getName();
            counters.put(name, howManyTimes(name) + 1);
            return method.invoke(theObject, args);
        }
    }

    @Override public int howManyTimes(String method) {
        return  counters.getOrDefault(method, 0);
    }

    private boolean isSpyMethod(Method method) {
        return Arrays.stream(Spied.class.getMethods()).anyMatch(method::equals);
    }

}
