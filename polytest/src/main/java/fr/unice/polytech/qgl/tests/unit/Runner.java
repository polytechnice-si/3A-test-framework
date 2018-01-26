package fr.unice.polytech.qgl.tests.unit;

import fr.unice.polytech.qgl.tests.unit.annotations.*;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class Runner {

    private Set<Class<?>> suites;

    public Runner(String packageName) {
        Reflections mirror = new Reflections(packageName);
        suites = mirror.getTypesAnnotatedWith(TestSuite.class);
    }

    public void execute() { suites.forEach( this::processSuite ); }

    private void processSuite(Class<?> clazz) {

        Reflections mirror  = new Reflections(clazz,new MethodAnnotationsScanner());
        Set<Method> tests   = mirror.getMethodsAnnotatedWith(Test.class);
        Set<Method> setup   = mirror.getMethodsAnnotatedWith(Before.class);
        Set<Method> cleanup = mirror.getMethodsAnnotatedWith(After.class);

        System.out.println("Executing tests in ["+clazz.getName()+"]");
        Set<TestResult> results = executeTests(clazz, tests, setup, cleanup);
        results.forEach(System.out::println);
    }

    private Set<TestResult> executeTests(Class<?> clazz, Set<Method> tests, Set<Method> setup, Set<Method> cleanup) {
        Set<TestResult> results = null;
        try {
            Object instance = clazz.newInstance();
            results = tests.stream()
                    .map(method -> processTest(instance, method, setup, cleanup))
                    .collect(Collectors.toSet());
        } catch (InstantiationException | IllegalAccessException e) { throw new RuntimeException(e); }
        return results;
    }

    private TestResult processTest(Object instance, Method test, Set<Method> setup, Set<Method> cleanup) {
        if(test.getDeclaredAnnotation(Ignore.class) != null)
            return new TestResult(test, true, "IGNORED");

        try {
            execute(instance, setup);
            test.invoke(instance);
            execute(instance, cleanup);
        }
        catch(IllegalAccessException e) { throw new RuntimeException(e); }
        catch(InvocationTargetException e) {
            if(! shouldFail(test)) { return new TestResult(test, false, e.getCause().getMessage()); }
        }
        return new TestResult(test, true);
    }

    private void execute(Object instance, Set<Method> methods)
            throws IllegalAccessException,InvocationTargetException {
        for(Method m: methods)
            m.invoke(instance);
    }

    private boolean shouldFail(Method test) {
        boolean shouldFail = false;
        if(test.getDeclaredAnnotation(Test.class).expected() != Test.DEFAULT_EXPECTATION.class)
            shouldFail = true;
        return shouldFail;
    }
}
