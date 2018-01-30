package fr.unice.polytech.qgl.tests.mock.internal;

import java.util.Arrays;
import java.util.Objects;

public class MockedInvocation {

    private ConfigurableObject proxy;
    private String method;
    private Object[] args;


    public MockedInvocation(ConfigurableObject obj, String method, Object[] args) {
        this.proxy = obj;
        this.method = method;
        this.args = args;
    }

    MockedInvocation(String method, Object[] args) { this(null, method, args); }

    public String getMethod() { return method; }

    public void returns(Object value) {
        this.proxy.registerInvocation(this, value);
    }

    public void defaultsTo(Object defaultValue) {
        this.proxy.registerDefaultValue(method, defaultValue);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockedInvocation that = (MockedInvocation) o;
        return Objects.equals(method, that.method) &&
                Arrays.equals(args, that.args);
    }

    @Override public int hashCode() {
        int result = Objects.hash(method);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }
}