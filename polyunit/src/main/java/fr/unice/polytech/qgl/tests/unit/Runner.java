package fr.unice.polytech.qgl.tests.unit;

import fr.unice.polytech.qgl.tests.unit.annotations.*;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Runner {

    private Set<Class<?>> suites;

    public Runner(String packageName) {
        Reflections mirror = new Reflections(packageName);
        suites = mirror.getTypesAnnotatedWith(TestSuite.class);
    }

    public void execute() { suites.forEach( this::runATestSuite); }

    private void runATestSuite(Class<?> clazz) {

        System.out.println("Executing tests in ["+clazz.getName()+"]");
        Set<Method> tests = extract(clazz, Test.class);
        if(tests.size() == 0) { System.out.println("  No tests found!\n"); return; }

        Set<Method> setup   = extract(clazz, Before.class);
        Set<Method> cleanup = extract(clazz, After.class);

        Set<TestResult> results = runTestsInsideASuite(clazz, tests, setup, cleanup);
        results.forEach(System.out::println);
        System.out.println();
    }

    private Set<TestResult> runTestsInsideASuite(Class<?> clazz, Set<Method> tests, Set<Method> setup, Set<Method> cleanup) {
        Set<TestResult> results = null;
        try {
            Object instance = clazz.newInstance();
            results = tests.stream()
                    .map(method -> runAGivenTest(instance, method, setup, cleanup))
                    .collect(Collectors.toSet());
        } catch (InstantiationException | IllegalAccessException e) { throw new RuntimeException(e); }
        return results;
    }

    private TestResult runAGivenTest(Object instance, Method test, Set<Method> setup, Set<Method> cleanup) {
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

    private Set<Method> extract(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }

}
